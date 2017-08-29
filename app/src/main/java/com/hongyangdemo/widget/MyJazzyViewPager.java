package com.hongyangdemo.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * author： xiongdejin
 * date: 2017/8/29
 * describe:
 */

public class MyJazzyViewPager extends ViewPager {
    private float mTrans;
    private float mScale;
    /**
     * 最大的缩小比例
     */
    private static final float SCALE_MAX = 0.5f;
    private static final String TAG = "MyJazzyViewPager";
    /**
     * 保存position与对于的View
     */
    private HashMap<Integer, View> mChildrenViews = new LinkedHashMap<Integer, View>();
    /**
     * 滑动时左边的元素
     */
    private View mLeft;
    /**
     * 滑动时右边的元素
     */
    private View mRight;

    public MyJazzyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onPageScrolled(int position, float positionOffset, int offsetPixels) {
        Log.d(TAG,"position:"+position);
        //滑动特别小的距离时，我们认为没有动，可有可无的判断
        float effectOffset = isSmall(positionOffset) ? 0 : positionOffset;
        //获取左边的View
        mLeft = findViewFromObject(position);
        //获取右边的View
        mRight = findViewFromObject(position + 1);
        animateStack(mLeft, mRight, effectOffset, offsetPixels);

        super.onPageScrolled(position, positionOffset, offsetPixels);
    }

    public void setObjectForPosition(View view, int position) {
        mChildrenViews.put(position, view);
    }

    /**
     * 动画
     * @param left 左边view
     * @param right 右边view
     * @param effectOffset 偏移的比例
     * @param positionOffsetPixels 偏移的像素值
     */
    protected void animateStack(View left, View right, float effectOffset, int positionOffsetPixels) {
        Log.d(TAG, "effectOffset:" + effectOffset + ",positionOffsetPixels:" + positionOffsetPixels);
        if (right != null) {
            /**
             * 缩小比例 如果手指从右到左的滑动（切换到后一个）：0.0~1.0，即从一半到最大
             * 如果手指从左到右的滑动（切换到前一个）：1.0~0，即从最大到一半
             */
            mScale = (1 - SCALE_MAX) * effectOffset + SCALE_MAX; //缩放的比例
            /**
             * x偏移量： 如果手指从右到左的滑动（切换到后一个）：0-720 如果手指从左到右的滑动（切换到前一个）：720-0
             */
            mTrans = -getWidth() - getPageMargin() + positionOffsetPixels;
            ViewHelper.setScaleX(right, mScale);
            ViewHelper.setScaleY(right, mScale);
            ViewHelper.setTranslationX(right, mTrans);
        }
        if (left != null) {
            //将view从父控件中移除并加到子控件列表末端
            left.bringToFront();
        }
    }

    /**
     * 通过过位置获得对应的View
     *
     * @param position
     * @return
     */
    public View findViewFromObject(int position) {
        if (position < mChildrenViews.size()) {
            return mChildrenViews.get(position);
        }
        else {
            return null;
        }
    }


    /**
     * 滑动是否特别小
     *
     * @param positionOffset
     * @return
     */
    private boolean isSmall(float positionOffset) {
        return Math.abs(positionOffset) < 0.0001;
    }
}
