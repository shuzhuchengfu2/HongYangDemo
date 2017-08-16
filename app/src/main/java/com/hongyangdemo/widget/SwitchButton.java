package com.hongyangdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

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
     * 默认位置
     */
    private int currentPostion = 1;
    private Drawable viewBackground;
    private Drawable itemBackground;
    private Paint mPaint;
    private List<String> texts = new ArrayList<>();

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


        a.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
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

        Log.d(TAG,"viewWidth:"+viewWidth+",viewHight:"+viewHight);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(viewWidth,viewHight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘画背景
        if (viewBackground != null) {
            canvas.drawBitmap(drawableToBitmap(viewBackground,true), 0, 0, mPaint);
        }

        if(itemBackground != null){
            Bitmap bitmap = drawableToBitmap(itemBackground,false);
            if(currentPostion != itemCount -1){
                canvas.drawBitmap(bitmap,
                        currentPostion*itemWidth + (itemWidth - bitmap.getWidth())/2,
                        (viewHight-bitmap.getHeight())/2,mPaint);
            }else{
                canvas.drawBitmap(bitmap,
                        viewWidth - itemWidth + (itemWidth - bitmap.getWidth())/2,
                        (viewHight-bitmap.getHeight())/2,mPaint);
            }
        }

        //绘制字体


    }


    public void setTexts(List<String> list){
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
        }else{
            bitmap = Bitmap.createBitmap(itemWidth-(int)dp2px(2f), viewHight-(int)dp2px(2f), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    //////////////////////////////////////工具类//////////////////////////////////////////////////

    public float dp2px(float dipValue) {
        return getResources().getDisplayMetrics().density * dipValue + 0.5f;
    }

    public float px2dp(float pxValue) {
        return pxValue / (getResources().getDisplayMetrics().density) + 0.5f;
    }
}
