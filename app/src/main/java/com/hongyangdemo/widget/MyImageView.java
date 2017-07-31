package com.hongyangdemo.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * author： xiongdejin
 * date: 2017/7/31
 * describe: Android 仿Win8的metro的UI界面
 */

public class MyImageView extends AppCompatImageView {
    private static final String TAG = "MyImageView";

    private static final int SCALE_REDUCE_INIT = 0;
    private static final int SCALING = 1;
    private static final int SCALE_ADD_INIT = 6;

    /**
     * 控件的宽
     */
    private int mWidth;
    /**
     * 控件的高
     */
    private int mHeight;
    /**
     * 控件的宽1/2
     */
    private int mCenterWidth;
    /**
     * 控件的高 1/2
     */
    private int mCenterHeight;

    /**
     * 设置一个缩放的常量
     */
    private float mMinScale = 0.85f;
    /**
     * 缩放是否结束
     */
    private boolean isFinish = true;

    public MyImageView(Context context) {
        this(context,null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed){
            mWidth = getWidth() - getPaddingLeft() - getPaddingRight();
            mHeight = getHeight() - getPaddingTop() - getPaddingBottom();

            mCenterWidth = mWidth /2;
            mCenterHeight = mHeight /2;

            Drawable drawable = getDrawable();
            BitmapDrawable bd = (BitmapDrawable) drawable;
            bd.setAntiAlias(true);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"onTouchEvent : ACTION_DOWN");
                mScaleHandler.sendEmptyMessage(SCALE_REDUCE_INIT);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,"onTouchEvent : ACTION_UP");
                mScaleHandler.sendEmptyMessage(SCALE_ADD_INIT);
                break;
        }
        return true;
    }


    private Handler mScaleHandler = new Handler(){
        private Matrix matrix = new Matrix();
        private int count = 0;
        private float s;
        /**
         * 是否已经调用了点击事件
         */
        private boolean isClicked;

        @Override
        public void handleMessage(Message msg) {
            matrix.set(getImageMatrix());
            switch (msg.what){
                case SCALE_REDUCE_INIT:
                    Log.d(TAG,"SCALE_REDUCE_INIT");
                    if(!isFinish){
                        mScaleHandler.sendEmptyMessage(SCALE_REDUCE_INIT);
                    }else{
                        isFinish = false;
                        count = 0;
                        s = (float) Math.sqrt(Math.sqrt(mMinScale));
                        beginScale(matrix,s);
                        mScaleHandler.sendEmptyMessage(SCALING);
                    }
                    Log.d(TAG,"s:"+s);
                    break;
                case SCALING:
                    Log.d(TAG,"SCALING");
                    beginScale(matrix,s);
                    if(count<4){
                        mScaleHandler.sendEmptyMessage(SCALING);
                    }else{
                        isFinish = true;
                        if(MyImageView.this.mOnViewClickListener != null && !isClicked){
                            isClicked = true;
                            MyImageView.this.mOnViewClickListener.onViewClick(MyImageView.this);
                        }else{
                            isClicked = false;
                        }
                    }
                    count ++;
                    Log.d(TAG,"s:"+s);
                    break;
                case SCALE_ADD_INIT:
                    Log.d(TAG,"SCALE_ADD_INIT");
                    if(!isFinish){
                        mScaleHandler.sendEmptyMessage(SCALE_ADD_INIT);
                    }else{
                        isFinish = false;
                        count = 0;
                        s = (float) Math.sqrt(Math.sqrt(1.0f / mMinScale));
                        beginScale(matrix,s);
                        mScaleHandler.sendEmptyMessage(SCALING);
                    }
                    Log.d(TAG,"s:"+s);
                    break;
            }
            super.handleMessage(msg);
        }
    };


    /**
     * 缩放
     *
     * @param matrix
     * @param scale
     */
    private synchronized void beginScale(Matrix matrix, float scale){
        matrix.postScale(scale, scale, mCenterWidth, mCenterHeight);
        setImageMatrix(matrix);
    }

    /**
     * 回调接口
     */
    private OnViewClickListener mOnViewClickListener;

    public void setOnClickIntent(OnViewClickListener onViewClickListener){
        this.mOnViewClickListener = onViewClickListener;
    }

    public interface OnViewClickListener {
        void onViewClick(MyImageView view);
    }
}
