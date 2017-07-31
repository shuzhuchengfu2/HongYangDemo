package com.hongyangdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * author： xiongdejin
 * date: 2017/7/31
 * describe: Andoird 自定义ViewGroup实现竖向引导界面
 */

public class VerticalLinearLayout extends ViewGroup{
    public static final String TAG = "VerticalLinearLayout";
    /**
     * 屏幕的高度
     */
    private int mScreenHeight;
    /**
     * 手指按下时的getScrollY
     */
    private int mScrollStart;
    /**
     * 手指抬起时的getScrollY
     */
    private int mScrollEnd;
    /**
     * 记录移动时的Y
     */
    private int mLastY;
    /**
     * 滚动的辅助类
     */
    private Scroller mScroller;
    /**
     * 是否正在滚动
     */
    private boolean isScrolling;
    /**
     * 加速度检测
     */
    private VelocityTracker mVelocityTracker;
    /**
     * 记录当前页
     */
    private int currentPage = 0;


    public VerticalLinearLayout(Context context) {
        this(context,null);
    }

    public VerticalLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VerticalLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for(int i =0 ;i<count;i++){
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,mScreenHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int childCount = getChildCount();
            // 设置主布局的高度
            MarginLayoutParams lp = (MarginLayoutParams)getLayoutParams();
            lp.height = childCount * mScreenHeight;
            setLayoutParams(lp);
            for(int i =0;i<childCount;i++){
                View child = getChildAt(i);
                if(child.getVisibility() != View.GONE){
                    child.layout(l,i*mScreenHeight,r,(i+1)*mScreenHeight);
                }
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isScrolling) {
            // 如果当前正在滚动，调用父类的onTouchEvent
            return super.onTouchEvent(event);
        }

        int action = event.getAction();
        int y = (int) event.getY();
        obtainVelocity(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScrollStart = getScrollY();
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //没有滑动结束
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = mLastY -y;

                int scrollY = getScrollY();
                //已经到达顶端，下拉多少，就往上滚动多少
                if (dy < 0 && scrollY + dy < 0) {
                    dy = -scrollY;
                }
                // 已经到达底部，上拉多少，就往下滚动多少
                if (dy > 0 && scrollY + dy > getHeight() - mScreenHeight) {
                    dy = getHeight() - mScreenHeight - scrollY;
                }

                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                mScrollEnd = getScrollY();
                int dScrollY = mScrollEnd - mScrollStart;
                if(wantScrollToNext()){ //TODO 向上滑
                    if(!shouldScrollToNext()){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,mScreenHeight - dScrollY);
                    }
                }

                if(wantScrollToPre()){ //TODO 向下滑
                    if(shouldScrollToPre()){
                        mScroller.startScroll(0, getScrollY(), 0, -mScreenHeight - dScrollY);
                    }else{
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    }
                }

                isScrolling = true;
                postInvalidate();
                recycleVelocity();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }else{
            //设置回调
            int position = getScrollY()/mScreenHeight;
            Log.e("xxx", position + "," + currentPage);
            if(position != currentPage){
                currentPage = position;
                if(mOnPageChangeListener != null){
                    mOnPageChangeListener.onPageChange(currentPage);
                }
            }
            isScrolling  = false;
        }
    }

    /**
     * 根据滚动距离判断是否能够滚动到下一页
     *
     * @return
     */
    private boolean shouldScrollToNext(){
        return mScrollEnd - mScrollStart > mScreenHeight / 2 || Math.abs(getVelocity()) > 600;
    }


    /**
     * 根据滚动距离判断是否能够滚动到上一页
     *
     * @return
     */
    private boolean shouldScrollToPre(){
        return -mScrollEnd + mScrollStart > mScreenHeight / 2 || Math.abs(getVelocity()) > 600;
    }


    /**
     * 根据用户滑动，判断用户的意图是否是滚动到下一页
     *
     * @return
     */
    private boolean wantScrollToNext(){
        return mScrollEnd > mScrollStart;
    }


    /**
     * 根据用户滑动，判断用户的意图是否是滚动到上一页
     *
     * @return
     */
    private boolean wantScrollToPre(){
        return mScrollEnd < mScrollStart;
    }

    /**
     * 获取y方向的加速度
     *
     * @return
     */
    private int getVelocity(){
        mVelocityTracker.computeCurrentVelocity(1000);
        return (int) mVelocityTracker.getYVelocity();
    }


    /**
     * 初始化加速器
     * @param event
     */
    private void obtainVelocity(MotionEvent event){
        if(mVelocityTracker == null){
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }


    /**
     * 释放资源
     */
    private void recycleVelocity(){
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private OnPageChangeListener mOnPageChangeListener;
    /**
     * 设置回调接口
     *
     * @param onPageChangeListener
     */
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener){
        mOnPageChangeListener = onPageChangeListener;
    }

    /**
     * 回调接口
     *
     * @author zhy
     *
     */
    public interface OnPageChangeListener{
        void onPageChange(int currentPage);
    }
}
