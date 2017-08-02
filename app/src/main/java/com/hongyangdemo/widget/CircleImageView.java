package com.hongyangdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.hongyangdemo.R;

/**
 * author： xiongdejin
 * date: 2017/8/2
 * describe: 完美实现图片圆角和圆形
 */

public class CircleImageView extends View {
    /**
     * TYPE_CIRCLE / TYPE_ROUND
     */
    private int type;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;

    /**
     * 图片
     */
    private Bitmap mSrc;

    /**
     * 圆角的大小
     */
    private int mRadius;

    /**
     * 控件的宽度
     */
    private int mWidth;
    /**
     * 控件的高度
     */
    private int mHeight;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        mSrc = BitmapFactory.decodeResource(getResources(), a.getResourceId(R.styleable.CircleImageView_src, 0));
        type = a.getInt(R.styleable.CircleImageView_type, 0);
        // 默认为10DP
        mRadius = a.getDimensionPixelSize(R.styleable.CircleImageView_borderRadius,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, getResources().getDisplayMetrics()));
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //设置宽度
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        // match_parent , accurate
        if (specMode == MeasureSpec.EXACTLY) {
            mWidth = specSize;
        }
        else {
            // 由图片决定的宽
            int desireByImg = getPaddingLeft() + getPaddingRight() + mSrc.getWidth();
            if (specMode == MeasureSpec.AT_MOST) {
                mWidth = Math.min(specSize, desireByImg);
            }
            else {
                mWidth = desireByImg;
            }
        }
        //设置高度
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mHeight = specSize;
        }
        else {
            int desire = getPaddingTop() + getPaddingBottom()
                    + mSrc.getHeight();
            // wrap_content
            if (specMode == MeasureSpec.AT_MOST) {
                mHeight = Math.min(desire, specSize);
            }
            else
                mHeight = desire;
        }

        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (type){
            case TYPE_CIRCLE:
                int min =  Math.min(mWidth,mHeight);
                mSrc = Bitmap.createScaledBitmap(mSrc,min,min,false);
                canvas.drawBitmap(createCircleImage(mSrc,min),0,0,null);
                break;
            case TYPE_ROUND:
                canvas.drawBitmap(createRoundConerImage(mSrc), 0, 0, null);
                break;
        }

    }

    /**
     * 根据原图和变长绘制圆形图片
     * @param source
     * @param min
     * @return
     */
    private Bitmap createCircleImage(Bitmap source,int min){
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min,min, Bitmap.Config.ARGB_8888);
        //产生一个同样大小的画布
        Canvas canvas = new Canvas(target);
        //首先绘制圆形
        canvas.drawCircle(min/2,min/2,min/2,paint);
        //使用SRC_IN，参考上面的说明
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    /**
     * 根据原图添加圆角
     * @param source
     * @return
     */
    private Bitmap createRoundConerImage(Bitmap source){
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        RectF rect = new RectF(0, 0, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rect, mRadius, mRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }
}
