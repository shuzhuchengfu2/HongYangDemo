package com.hongyangdemo.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * author： xiongdejin
 * date: 2017/8/9
 * describe: 支持上下反弹的ScrollView
 */

public class BounceScrollView extends ScrollView {
    private boolean isCalled;

    private Callback mCallback;

    /**
     * 包含的View
     */
    private View mView;
    /**
     * 存储正常时的位置
     */
    private Rect mRect = new Rect();

    /**
     * y坐标
     */
    private int y;

    private boolean isFirst = true;

    public BounceScrollView(Context context) {
        this(context, null);
    }

    public BounceScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BounceScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BounceScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {

    }

    //根据xml生成视图完成，该方法在生成视图的最后调用，在所有子视图添加完成之后，
    // 即使子视图覆盖了该方法也应该调用父类的该方法
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            mView = getChildAt(0);
        }
        super.onFinishInflate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(mView != null){
            commonOnTouch(ev);
        }
        return super.onTouchEvent(ev);
    }

    private void commonOnTouch(MotionEvent ev) {
        int action = ev.getAction();
        int cy = (int) ev.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = cy - y;
                if(isFirst){
                    dy = 0;
                    isFirst = false;
                }
                y = cy;
                if(isNeedMove()){
                    if(mRect.isEmpty()){
                        mRect.set(mView.getLeft(),mView.getTop(),mView.getRight(),mView.getBottom());
                    }
                    //类似阻尼系数
                    mView.layout(mView.getLeft(),mView.getTop()+2*dy/3,mView.getRight(),mView.getBottom()+2*dy/3);
                    if(isShouldCallBack(dy)){
                        //当连续滑动+放手
                        if(mCallback != null){
                            if(!isCalled){
                                isCalled = true;
                                resetPosition();
                                mCallback.callback();
                            }
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if(!mRect.isEmpty()){
                    resetPosition();
                }
                break;
        }
    }

    private void resetPosition(){
        Animation animation = new TranslateAnimation(0,0,mView.getTop(),mRect.top);
        animation.setDuration(200);
        animation.setFillAfter(true);
        mView.startAnimation(animation);
        mView.layout(mRect.left,mRect.top,mRect.right,mRect.bottom);
        mRect.setEmpty();
        isFirst = true;
        isCalled = false;
    }


    private boolean isShouldCallBack(int dy){
        //当滑动位置超过控件的高度的一半
        return dy > 0 && mView.getTop() > getHeight() /2;
    }


    private boolean isNeedMove(){
        int offset = mView.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        return scrollY == 0 || scrollY == offset;
    }

    public void setCallBack(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void callback();
    }
}
