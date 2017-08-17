package com.hongyangdemo.widget;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.hongyangdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author： xiongdejin
 * date: 2017/8/16
 * describe:
 */

public class SwitchButton extends View {
    public static final String TAG = "SwitchButton";
    /**
     * 是否可以被点击
     */
    private boolean isCanClick = true;
    /**
     * 相邻两个item间隔为100ms
     */
    private long intervalTime = 100;
    /**
     * 片段的总个数
     */
    private int itemCount;
    /**
     * 片段的宽度
     */
    private int itemWidth;
    /**
     * 控件的高度
     */
    private int viewHight;
    /**
     * 控件的高度
     */
    private int viewWidth;
    /**
     * 移动的位置 0 ~ itemCount-1
     */
    private int currentOffeset = 0;
    /**
     * 之前位置
     */
    private int currentPosition = 0;
    private Drawable viewBackground;
    private Drawable itemBackground;
    private Paint mPaint;
    private List<String> texts = new ArrayList<>();

    private int viewTextSize;
    private int itemTextSize;
    private int viewTextColor;
    private int itemTextColor;
    private Rect mBound;
    private OnPositionChangeListener onPositionChangeListener;

    public SwitchButton(Context context) {
        this(context, null);
    }

    public SwitchButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    public void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwitchButton);
        itemCount = a.getInt(R.styleable.SwitchButton_item_count, 1);

        int idView = a.getResourceId(R.styleable.SwitchButton_view_bg, 0);
        if (idView != 0) {
            viewBackground = getResources().getDrawable(idView);
        }

        int idItem = a.getResourceId(R.styleable.SwitchButton_item_bg, 0);
        if (idItem != 0) {
            itemBackground = getResources().getDrawable(idItem);
        }

        viewTextColor = a.getColor(R.styleable.SwitchButton_view_text_color, Color.WHITE);
        itemTextColor = a.getColor(R.styleable.SwitchButton_item_text_color,Color.YELLOW);

        viewTextSize = a.getDimensionPixelSize(R.styleable.SwitchButton_view_text_size,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

        itemTextSize = a.getDimensionPixelSize(R.styleable.SwitchButton_item_text_size,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

        a.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBound = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            viewWidth = widthSize;
            itemWidth = widthSize / itemCount;
        }
        else {
            itemWidth = (int) dp2px(75f);
            viewWidth = itemCount * itemWidth;
        }
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (heightMode == MeasureSpec.EXACTLY) {
            viewHight = MeasureSpec.getSize(heightMeasureSpec);
        }
        else {
            viewHight = (int) dp2px(28f);
        }

        Log.d(TAG, "viewWidth:" + viewWidth + ",viewHight:" + viewHight);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(viewWidth, viewHight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘画背景
        if (viewBackground != null) {
            canvas.drawBitmap(drawableToBitmap(viewBackground, true), 0, 0, mPaint);
        }

        if (itemBackground != null) {
            Bitmap bitmap = drawableToBitmap(itemBackground, false);
            if (currentOffeset != itemCount - 1) {
                canvas.drawBitmap(bitmap,
                        currentOffeset * itemWidth + (itemWidth - bitmap.getWidth()) / 2,
                        (viewHight - bitmap.getHeight()) / 2, mPaint);
            }
            else {
                canvas.drawBitmap(bitmap,
                        viewWidth - itemWidth + (itemWidth - bitmap.getWidth()) / 2,
                        (viewHight - bitmap.getHeight()) / 2, mPaint);
            }
        }

        //绘制字体
        if (texts.size() <= itemCount) {
            for(int i = 0;i<texts.size();i++){
                String text = texts.get(i);
                float halfWidth = itemWidth * (i+0.5f);
                float halfHeight = viewHight * 0.5f;
                if(i == currentOffeset){
                    mPaint.setColor(itemTextColor);
                    mPaint.setTextSize(itemTextSize);
                    mPaint.getTextBounds(text, 0, text.length(), mBound);
                }else{
                    mPaint.setColor(viewTextColor);
                    mPaint.setTextSize(viewTextSize);
                    mPaint.getTextBounds(text, 0, text.length(), mBound);
                }
                halfWidth = halfWidth - mBound.width() /2;
                halfHeight = halfHeight + mBound.height() /2;

                canvas.drawText(text,halfWidth,halfHeight,mPaint);
            }
        }


    }


    public void setTexts(List<String> list) {
        texts.clear();
        texts.addAll(list);
        invalidate();
    }

    /**
     * @param drawable
     * @param isView   是view
     * @return
     */
    public Bitmap drawableToBitmap(Drawable drawable, boolean isView) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap;
        if (isView) {
            bitmap = Bitmap.createBitmap(viewWidth, viewHight, Bitmap.Config.ARGB_8888);
        }
        else {
            bitmap = Bitmap.createBitmap(itemWidth - (int) dp2px(2f), viewHight - (int) dp2px(2f), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() ==  MotionEvent.ACTION_DOWN){
            if(isCanClick){
                float x = event.getX();
                int downPosition  = (int)x/itemWidth;
                setCurrentPosition(downPosition);
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 设置当前位置
     * @param downPosition
     */
    public void setCurrentPosition(int downPosition){
        if(downPosition != currentPosition){
            //计算动画
            int interval = Math.abs(downPosition - currentPosition);
            long totalIntervalTime = interval * intervalTime;
            initAnimation(totalIntervalTime,currentPosition* itemWidth,downPosition*itemWidth);
            currentPosition = downPosition;
            if(onPositionChangeListener!= null){
                onPositionChangeListener.currentPosition(downPosition);
            }
        }
    }


    /**
     * 初始化动画
     */
    private void initAnimation(long totalIntervalTime,float start,float end){
        PropertyValuesHolder angleValues = PropertyValuesHolder.ofFloat("translate", start, end);
        ValueAnimator valueAnimator = ValueAnimator.ofPropertyValuesHolder(angleValues);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentOffeset =  ((Number) valueAnimator.getAnimatedValue()).intValue()/itemWidth;
                invalidate();
            }

        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isCanClick = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isCanClick = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isCanClick = true;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.setDuration(totalIntervalTime);
        valueAnimator.setRepeatCount(0);
        valueAnimator.setInterpolator(new LinearInterpolator()); //速率为匀速
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.start();
    }

    public int getCurrentPosition(){
        return currentPosition;
    }

    //////////////////////////////////////工具类//////////////////////////////////////////////////

    public float dp2px(float dipValue) {
        return getResources().getDisplayMetrics().density * dipValue + 0.5f;
    }

    public float px2dp(float pxValue) {
        return pxValue / (getResources().getDisplayMetrics().density) + 0.5f;
    }

    public int px2sp(float pxValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public interface OnPositionChangeListener{
        void currentPosition(int position);
    }

    public void setOnPositionChangeListener(OnPositionChangeListener onPositionChangeListener){
        this.onPositionChangeListener = onPositionChangeListener;
    }
}
