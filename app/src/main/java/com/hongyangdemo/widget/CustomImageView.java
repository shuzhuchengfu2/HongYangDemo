package com.hongyangdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.hongyangdemo.R;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:Android 自定义View (二)
 */

public class CustomImageView extends View {
    public static final String TAG = "CustomImageView";
    private static final int IMAGE_SCALE_FITXY = 0 ;
    private Bitmap mImage;
    private int mImageScale;
    private String mTitle;
    private int mTextColor;
    private int mTextSize;
    private Rect rect;
    private Paint mPaint;
    private Rect mTextBound;
    private int mWidth;
    private int mHeight;

    public CustomImageView(Context context) {
        this(context,null);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomImageView);
        mImage = BitmapFactory.decodeResource(getResources(),
                a.getResourceId(R.styleable.CustomImageView_image, 0));
        mImageScale = a.getInt(R.styleable.CustomImageView_imageScaleType, 0);
        mTitle = a.getString(R.styleable.CustomImageView_ititleText);
        mTextColor = a.getColor(R.styleable.CustomImageView_ititleTextColor, Color.BLACK);
        mTextSize = a.getDimensionPixelSize(R.styleable.CustomImageView_ititleTextSize,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        a.recycle();
        rect = new Rect();
        mPaint = new Paint();
        mTextBound = new Rect();
        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds(mTitle, 0, mTitle.length(), mTextBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //设置宽度
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            mWidth = specSize;
            Log.d(TAG, "EXACTLY mWidth:" + mWidth);
        }
        else {
            //由图片决定宽
            int desireByImg = getPaddingLeft() + mImage.getWidth() + getPaddingRight();
            //由文字决定宽
            int desireByTitle = getPaddingLeft() + mTextBound.width() + getPaddingRight();

            if (specMode == MeasureSpec.AT_MOST) {
                int desire = Math.max(desireByImg, desireByTitle);
                mWidth = Math.min(desire, specSize);
                Log.d(TAG, "AT_MOST mWidth:" + mWidth);
            }
        }

        //设置高度
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            mHeight = specSize;
            Log.d(TAG, "EXACTLY mHeight:" + mHeight);
        }
        else {
            int desire = getPaddingTop() + getPaddingBottom() + mTextBound.height() + mImage.getHeight();
            if (specMode == MeasureSpec.AT_MOST) {
                mHeight = Math.min(desire, specSize);
                Log.d(TAG, "AT_MOST mHeight:" + mHeight);
            }
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        //绘制边框
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        rect.left = getPaddingLeft();
        rect.right = mWidth - getPaddingRight();
        rect.top = getPaddingTop();
        rect.bottom = mHeight - getPaddingBottom();

        mPaint.setColor(mTextColor);
        mPaint.setStyle(Paint.Style.FILL);

        //当前设置的宽度小于字体需要的宽度，将字体改为xxx...
        if(mTextBound.width() > mWidth){
            TextPaint textPaint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitle,textPaint,(float)mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg,getPaddingLeft(),mHeight-getPaddingBottom(),mPaint);
        }else{
            //正常情况，将字体居中
            canvas.drawText(mTitle,mWidth/2-mTextBound.width()/2*1.0f,mHeight-getPaddingBottom(),mPaint);
        }

        //取消使用掉的模块
        rect.bottom -= mTextBound.height();

        if(mImageScale == IMAGE_SCALE_FITXY){
            canvas.drawBitmap(mImage,null,rect,mPaint);
        }else{
            //计算居中的矩形范围
            rect.left = mWidth / 2 - mImage.getWidth() / 2;
            rect.right = mWidth / 2 + mImage.getWidth() / 2;
            rect.top = (mHeight - mTextBound.height()) / 2 - mImage.getHeight() / 2;
            rect.bottom = (mHeight - mTextBound.height()) / 2 + mImage.getHeight() / 2;

            canvas.drawBitmap(mImage, null, rect, mPaint);
        }


    }
}
