package com.chuxin.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.chuxin.F;
import com.chuxin.utils.AbDisplayUtil;
import com.hongyangdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author： xiongdejin
 * date: 2017/9/18
 * describe: 折线图点实心
 */

public class CxSolidLineChart extends View {
    private Paint mPaint;
    private float endApr,startApr,addApr;
    private int mWidth,mHeight,viewWidth,viewHeight;
    private List<String> leftStrings = new ArrayList<>();
    private List<String> bottomStrings = new ArrayList<>();
    private List<Float> bottomX = new ArrayList<>();
    private int currentMonth =1; //当前月份
    private Bitmap currentMonthBitmap;
    private Paint mLinePaint;
    private Bitmap currentMonthAprBg;

    public CxSolidLineChart(Context context) {
        this(context,null);
    }

    public CxSolidLineChart(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CxSolidLineChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CxSolidLineChart);
        startApr = a.getFloat(R.styleable.CxSolidLineChart_start,6.6f);
        endApr = a.getFloat(R.styleable.CxSolidLineChart_end,10.0f);
        addApr = a.getFloat(R.styleable.CxSolidLineChart_perAdd,0.2f);
        a.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);

        currentMonthBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.detail_profit_point);
        currentMonthAprBg = BitmapFactory.decodeResource(getResources(),R.drawable.detail_profit_bg);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w - getPaddingLeft() - getPaddingRight();
        mHeight = h - getPaddingTop() - getPaddingBottom();
        viewWidth = w;
        viewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initData();
        calcTextPosition(canvas);

    }

    private void initData(){
        float leftAdd = (endApr - startApr)/4;
        leftStrings.clear();
        for(int i =0;i<4;i++){
            if(i == 0){
                leftStrings.add(F.formatMoneyForCxLine(startApr+"")+"%");
            }else if(i ==3){
                leftStrings.add(F.formatMoneyForCxLine(endApr+"")+"%");
            }else{
                leftStrings.add(F.formatMoneyForCxLine((startApr + leftAdd * i)+"")+"%");
            }
        }


        bottomStrings.clear();
        int count =(int) ((endApr - startApr)/addApr);
        if(count != addApr * count){
            count = count +1;
        }

        for(int i=0;i<count;i++){
            if(i == count-1){
                bottomStrings.add((i+1)+"期以后");
            }else{
                bottomStrings.add((i+1)+"");
            }
        }
    }


    private void calcTextPosition(Canvas canvas){
        if(leftStrings == null || leftStrings.size() ==0|| bottomStrings == null || bottomStrings.size() ==0) return;
        float lineLeftX=0,lineLeftY=0,lineCenterX=0,lineCenterY=0,lineRightX=0,lineRightY=0;
        int maxLeftWidth = 0;
        for(String content:leftStrings){
            maxLeftWidth = Math.max(maxLeftWidth,getTextWidth(content,10));
        }

        int maxBottomHeight = getTextHeight(bottomStrings.get(bottomStrings.size()-1),10);
        //32+18
        float chartHeight = viewHeight - maxBottomHeight - AbDisplayUtil.dip2px(getContext(),50);
        int maxLeftHeight = getTextHeight(leftStrings.get(leftStrings.size()-1),10);
        float leftGap = chartHeight/(leftStrings.size()-1);
        //绘制左边的文字
        for(int i=0;i<leftStrings.size();i++){
            float y = AbDisplayUtil.dip2px(getContext(),32)+maxLeftHeight/2+i*leftGap;
            drawLeftAndBottomText(canvas,leftStrings.get(leftStrings.size()-i-1),0,y);
        }
        //斜线的
        lineLeftY  = AbDisplayUtil.dip2px(getContext(),32) + (leftStrings.size()-1)*leftGap;
        lineCenterY  = AbDisplayUtil.dip2px(getContext(),32);
        lineRightY  = AbDisplayUtil.dip2px(getContext(),32);

        lineCenterY  = AbDisplayUtil.dip2px(getContext(),32);
        //绘制下面的文字
        float totalBottomTextWidth = viewWidth-AbDisplayUtil.dip2px(getContext(),18)-maxLeftWidth-getTextWidth("期以后",10);
        float bottomGap = totalBottomTextWidth/(bottomStrings.size()-1);

        bottomX.clear();

        for(int i=0;i<bottomStrings.size();i++){
            float x;
            int textWidth = getTextWidth(bottomStrings.get(i),10);
            float xCenter;
            if(i == bottomStrings.size()-1){
                x = viewWidth - textWidth;
                xCenter = x+getTextWidth("00",10);
            }else{
                x = AbDisplayUtil.dip2px(getContext(),18)+maxLeftWidth+bottomGap*i - textWidth;
                xCenter = x+textWidth/2;
            }

            //各点的Y轴
            bottomX.add(xCenter);

            drawLeftAndBottomText(canvas,bottomStrings.get(i),x,viewHeight-AbDisplayUtil.dip2px(getContext(),1));

            if(i == 0){
                lineLeftX = AbDisplayUtil.dip2px(getContext(),18)+maxLeftWidth+bottomGap*i - textWidth/2;
            }

            if(i ==bottomStrings.size()-1){
                lineCenterX = x + getTextWidth("00",10);
            }
        }

        lineRightX = viewWidth;
        //绘制线
        mPaint.setStrokeWidth(AbDisplayUtil.dip2px(getContext(),1.5f));
        mPaint.setColor(Color.parseColor("#ffbb07"));
        canvas.drawLine(lineLeftX,lineLeftY,lineCenterX,lineCenterY,mPaint);
        canvas.drawLine(lineCenterX,lineCenterY,lineRightX,lineRightY,mPaint);


        if(currentMonth<1) return;

        float bitmapWidth = currentMonthBitmap.getWidth();
        float bitmapHeight = currentMonthBitmap.getHeight();
        float bitmapLeft;
        float bitmapTop;
        float pointX;
        float pointY;

        if(currentMonth > bottomStrings.size()){ //超过最高点
            pointX = (lineRightX-lineCenterX)/2+lineCenterX;
            pointY = lineRightY;
        }else{
            float inclination = (lineCenterY-lineLeftY)/(lineCenterX-lineLeftX);
            float xCenter = bottomX.get(currentMonth-1);
            float xLeft = bottomX.get(0);
            pointX = xCenter;
            pointY = (xCenter-xLeft)*inclination + lineLeftY;
            //绘制点旁边的两个线
            //0.5dp f4f4f4
            mLinePaint.setStrokeWidth(AbDisplayUtil.dip2px(getContext(),0.5f));
            mLinePaint.setColor(Color.parseColor("#f4f4f4"));
            canvas.drawLine(maxLeftWidth,pointY, pointX,pointY, mLinePaint);
            canvas.drawLine(pointX,pointY, pointX,viewHeight-maxBottomHeight, mLinePaint);
        }
        bitmapLeft = pointX-bitmapWidth/2;
        bitmapTop = pointY-bitmapHeight/2;
        canvas.drawBitmap(currentMonthBitmap,bitmapLeft,bitmapTop,mPaint);

        //绘制利率的展示 点上面25dp
        bitmapWidth = currentMonthAprBg.getWidth();
        bitmapHeight = currentMonthAprBg.getHeight();
        mPaint.setTextSize(AbDisplayUtil.sp2px(getContext(),9));
        bitmapLeft = pointX- bitmapWidth/2;
        bitmapTop = pointY - bitmapHeight/2-AbDisplayUtil.dip2px(getContext(),20);
        canvas.drawBitmap(currentMonthAprBg,bitmapLeft,bitmapTop,mPaint);
        //绘制文字
        float currentApr;
        if(currentMonth-1<bottomStrings.size()-1){
            currentApr = startApr + (currentMonth-1)*addApr;
        }else{
            currentApr = endApr;
        }

        String content = F.formatMoneyForCxLine(currentApr+"")+"%";
        float textWidth = getTextWidth(content,9);
        float textHeight = getTextHeight(content,9);
        canvas.drawText(content,pointX-textWidth/2,pointY+textHeight/3-AbDisplayUtil.dip2px(getContext(),20),mPaint);
    }




    /**
     * 绘制文字 并返回文字的宽高
     * @param content
     * @param x
     * @param y
     * @return
     */
    public void drawLeftAndBottomText(Canvas canvas,String content, float x, float y){
        //10sp #999
        mPaint.setTextSize(AbDisplayUtil.sp2px(getContext(),10f));
        mPaint.setColor(Color.parseColor("#999999"));
        canvas.drawText(content, x, y, mPaint);
    }

    /**
     * 获取文字的宽度
     * @param content
     * @param textSize 文字的大小 单位sp
     * @return
     */
    private int getTextWidth(String content,int textSize){
        mPaint.setTextSize(AbDisplayUtil.sp2px(getContext(),textSize));
        Rect rect = new Rect();
        mPaint.getTextBounds(content, 0, content.length(), rect);
        return rect.width();
    }

    private int getTextHeight(String content,int textSize){
        mPaint.setTextSize(AbDisplayUtil.sp2px(getContext(),textSize));
        Rect rect = new Rect();
        mPaint.getTextBounds(content, 0, content.length(), rect);
        return rect.height();
    }


    /**
     * 设置当前月份
     * @param currentMonth
     */
    public void setCurrentMonth(int currentMonth){
        if(currentMonth<1) return;
        this.currentMonth = currentMonth;
        invalidate();
    }

    /**
     * 设置数据
     * @param startApr
     * @param endApr
     * @param addApr
     */
    public void setData(float startApr,float endApr,float addApr){
        this.startApr = startApr;
        this.endApr = endApr;
        this.addApr = addApr;
        invalidate();
    }


}
