package com.IMRUIGOD.weatherdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;




public class TemperatureView extends View {
    private static final String TAG = "TemperatureView";
    //最小值
    private int minValue;
    //最大值
    private int maxValue;
    //点值
    private int currentValue;
    private int currentValue1;
    //上一个温度
    private int lastValue_zg;
    private int lastValue_zd;
    //下一个温度
    private int nextValue_zg;
    private int nextValue_zd;
    //画笔
    private Paint mPaint;
    private Paint mPaint1;
    //视图高和宽
    private int viewHeight;
    private int viewWidth;
    //X轴
    private int pointX;
    //Y轴
    private int pointY;
    private int pointY1;
    //左边线
    private boolean isDrawLeftLine;
    //右边线
    private boolean isDrawRightLine;
    //视
    private int pointTopY = (int) (40 * Util.getDensity(getContext()));
    private int pointBottomY = (int) (150 * Util.getDensity(getContext()));
    private int mMiddleValue;

    public TemperatureView(Context context) {
        super(context);
    }

    public TemperatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TemperatureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //设置最小值
    public void setMinValue(int minValue){
        this.minValue = minValue;
    }

    //设置最大值
    public void setMaxValue(int maxValue){
        this.maxValue = maxValue;
    }

    //设置目前的值
    public void setZGCurrentValue(int currentValue){
        this.currentValue = currentValue;
    }
    public void setZDCurrentValue(int currentValue1){
        this.currentValue1 = currentValue1;
    }

    //设置是否画左边线段(只有第一个View是false)
    public void setDrawLeftLine(boolean isDrawLeftLine){
        this.isDrawLeftLine = isDrawLeftLine;
    }

    //设置是否画右边线段(只有最后一个View是false)
    public void setDrawRightLine(boolean isDrawRightLine){
        this.isDrawRightLine = isDrawRightLine;
    }

    //设置之前温度点的值
    public void setZGLastValue(int lastValue_zg){
        this.lastValue_zg = lastValue_zg;
    }
    public void setZDLastValue(int lastValue_zd){
        this.lastValue_zd = lastValue_zd;
    }

    //设置下一个温度点的值
    public void setZGNextValue(int nextValue_zg){
        this.nextValue_zg = nextValue_zg;
    }
    public void setZDNextValue(int nextValue_zd){
        this.nextValue_zd = nextValue_zd;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //给一个初始长、宽
        int mDefaultWidth = 200;
        int mDefaultHeight = (int) (170 * Util.getDensity(getContext()));
        setMeasuredDimension(resolveSize(mDefaultWidth,widthMeasureSpec),resolveSize(mDefaultHeight,heightMeasureSpec));
        viewHeight = getMeasuredHeight();
        viewWidth = getMeasuredWidth();
        pointX = viewWidth / 2;
        Log.d(TAG, "onMeasure: " + viewWidth);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mMiddleValue = (pointTopY + pointBottomY) / 2;
        pointY = mMiddleValue + (int) ((pointBottomY-pointTopY) * 1f / (maxValue - minValue) * ((maxValue + minValue) / 2 - currentValue));
        pointY1 = mMiddleValue + (int) ((pointBottomY-pointTopY) * 1f / (maxValue - minValue) * ((maxValue + minValue) / 2 - currentValue1));

        Log.d(TAG, "onDraw: " + pointY);
        mPaint = new Paint();
        mPaint1 = new Paint();

        drawGraph(canvas);
        drawValue(canvas);
        drawPoint(canvas);
    }



    //绘制数值
    private void drawValue(Canvas canvas){
        mPaint.setTextSize(40);
        setTextColor();
        mPaint.setStrokeWidth(0);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(currentValue+"℃",pointX , pointY - 20, mPaint);

        mPaint1.setTextSize(40);
        setTextColor();
        mPaint1.setStrokeWidth(0);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(currentValue+"℃",pointX , pointY1 - 20, mPaint1);
    }

    //设置字体颜色
    public void setTextColor(){
            mPaint.setColor(getResources().getColor(R.color.hei));
    }

    //绘制温度点
    public void drawPoint(Canvas canvas){

        mPaint.setColor(getResources().getColor(R.color.wd_zg));
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(pointX, pointY, 5, mPaint);


        mPaint1.setColor(getResources().getColor(R.color.wd_zd));
        mPaint1.setStrokeWidth(2);
        mPaint1.setStyle(Paint.Style.FILL);
        canvas.drawCircle(pointX, pointY1, 5, mPaint1);
    }

    //绘制线段（线段组成折线）
    public void drawGraph(Canvas canvas){
        mPaint.setColor(getResources().getColor(R.color.wd_zg));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);    //设置抗锯齿

        mPaint1.setColor(getResources().getColor(R.color.wd_zd));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setStrokeWidth(3);
        mPaint1.setAntiAlias(true);    //设置抗锯齿

        //判断是否画左线段（第一个View不用，其他全要）
        if(isDrawLeftLine){
            float middleValue = currentValue - (currentValue - lastValue_zg) / 2f;
            float middleValue1 = currentValue1 - (currentValue1 - lastValue_zd) / 2f;

            float middleY = mMiddleValue + (int) ((pointBottomY-pointTopY) * 1f / (maxValue - minValue) * ((maxValue + minValue) / 2 - middleValue));
            float middleY1 = mMiddleValue + (int) ((pointBottomY-pointTopY) * 1f / (maxValue - minValue) * ((maxValue + minValue) / 2 - middleValue1));

            canvas.drawLine(0, middleY, pointX, pointY, mPaint);
            canvas.drawLine(0, middleY1, pointX, pointY1, mPaint1);
        }

        //判断是否画右线段（最后View不用，其他全要）
        if(isDrawRightLine){
            float middleValue = currentValue - (currentValue - nextValue_zg) / 2f;
            float middleY = mMiddleValue + (int) ((pointBottomY-pointTopY) * 1f / (maxValue - minValue) * ((maxValue + minValue) / 2 - middleValue));
            canvas.drawLine(pointX, pointY, viewWidth, middleY, mPaint);

            float middleValue1 = currentValue1 - (currentValue1 - nextValue_zd) / 2f;
            float middleY1 = mMiddleValue + (int) ((pointBottomY-pointTopY) * 1f / (maxValue - minValue) * ((maxValue + minValue) / 2 - middleValue1));
            canvas.drawLine(pointX, pointY1, viewWidth, middleY1, mPaint1);
        }
    }
}
