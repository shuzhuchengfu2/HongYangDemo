package com.hongyangdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;

/**
 * author： xiongdejin
 * date: 2017/9/1
 * describe:
 */

public class SingleItemScrollView extends ScrollView implements View.OnClickListener {
    /**
     * Item点击的回调
     */
    private OnItemClickListener mListener;

    private Adapter mAdapter;
    /**
     * 屏幕的高度
     */
    private int mScreenHeight;
    /**
     * 每个条目的高度
     */
    private int mItemHeight;
    private ViewGroup mContainer;

    /**
     * 条目总数
     */
    private int mItemCount;

    private boolean flag;

    /**
     * 适配器
     *
     * @author zhy
     */
    public static abstract class Adapter {
        public abstract View getView(SingleItemScrollView parent, int pos);

        public abstract int getCount();
    }

    /**
     * 点击的回调
     */
    public interface OnItemClickListener {
        void onItemClick(int pos, View view);
    }


    public SingleItemScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 计算屏幕的高度
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
        mScreenHeight -= getStatusHeight(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //防止多次调用
        if (!flag) {
            mContainer = (ViewGroup) getChildAt(0);

            //根据Adapter的方法，为容器添加Item
            if (mAdapter != null) {
                mItemCount = mAdapter.getCount();
                mItemHeight = mScreenHeight / mItemCount;
                mContainer.removeAllViews();
                for (int i = 0; i < mAdapter.getCount(); i++) {
                    addChildView(i);
                }
            }
            addChildView(0);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 在容器末尾添加一个Item
     *
     * @param i
     */
    private void addChildView(int i) {
        View item = mAdapter.getView(this, i);
        //设置参数
        android.view.ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
        item.setLayoutParams(lp);
        //设置Tag
        item.setTag(i);
        //添加事件
        item.setOnClickListener(this);
        mContainer.addView(item);
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    @Override
    public void onClick(View v) {
        int pos = (Integer) v.getTag();
        if (mListener != null) {
            mListener.onItemClick(pos, v);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        flag = true;
        int action = ev.getAction();
        int scrollY = getScrollY();
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                Log.e("TAG", "scrollY = " + scrollY);
                // 表示此时ScrollView的顶部已经达到屏幕顶部
                if (scrollY == 0) {
                    addChildToFirst();
                }
                // ScrollView的顶部已经到达屏幕底部
                if (Math.abs(scrollY - mItemHeight) <= mItemCount) {
                    addChildToLast();
                }

                break;
            case MotionEvent.ACTION_UP:
                checkForReset();
                return true;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 在底部添加一个View，并移除第一个View
     */
    private void addChildToLast() {
        Log.e("TAG", "addChildToLast");
        int pos = (Integer) mContainer.getChildAt(1).getTag();
        addChildView(pos);
        mContainer.removeViewAt(0);
        this.scrollTo(0, 0);
    }

    /**
     * 在顶部添加一个View，并移除最后一个View
     */
    protected void addChildToFirst() {
        Log.e("TAG", "addChildToFirst");
        int pos = (Integer) mContainer.getChildAt(mItemCount - 1).getTag();
        addChildView(pos, 0);
        mContainer.removeViewAt(mContainer.getChildCount() - 1);
        this.scrollTo(0, mItemHeight);
    }

    /**
     * 检查当前getScrollY,显示完成Item，或者收缩此Item
     */
    private void checkForReset() {
        int val = getScrollY() % mItemHeight;
        if (val >= mItemHeight / 2) {
            smoothScrollTo(0, mItemHeight);
        }
        else {
            smoothScrollTo(0, 0);
        }

    }

    /**
     * 在容器指定位置添加一个Item
     *
     * @param i
     */
    private void addChildView(int i, int index) {
        View item = mAdapter.getView(this, i);
        android.view.ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
        item.setLayoutParams(lp);
        item.setTag(i);
        item.setOnClickListener(this);
        mContainer.addView(item, index);
    }

    public void setAdapter(Adapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }
}
