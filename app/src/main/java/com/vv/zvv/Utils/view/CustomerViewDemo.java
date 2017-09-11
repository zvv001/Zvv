package com.vv.zvv.Utils.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zvv on 2017/8/14 16:43.
 */
public class CustomerViewDemo extends View {

    //View
    private float height, width;

    //画笔
    Paint mPaint;

    public CustomerViewDemo(Context context) {
        super(context);
    }

    public CustomerViewDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerViewDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initPain() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿的画笔
        /**
         * 绘制模式:默认值是 FILL，填充模式
         *
         * Style 具体来说有三种： FILL, STROKE 和 FILL_AND_STROKE 。
         * FILL 是填充模式
         * STROKE 是画线模式（即勾边模式）
         * FILL_AND_STROKE 是两种模式一并使用：既画线又填充。
         */
        mPaint.setStyle(Paint.Style.STROKE);

        // 颜色
        mPaint.setColor(Color.BLACK);

        //线条宽
        mPaint.setStrokeWidth(2);// 线条宽度为 20 像素

        //文字大小
        mPaint.setTextSize(100);

        /**
         * 抗锯齿
         * mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
         */
        mPaint.setAntiAlias(true);

    }

    private void initCanvas(Canvas canvas) {
        canvas = new Canvas();
        //画布颜色
        /**
         * canvas.drawRGB(100, 200, 100);
         * canvas.drawARGB(100, 100, 200, 100);
         * canvas.drawColor(Color.parseColor("#883400ff"));
         */
        canvas.drawColor(Color.WHITE);
    }

    //点
    private void drawPoint(Canvas mCanvas) {
        mPaint.setStrokeCap(Paint.Cap.ROUND);// 端点有圆头 (ROUND)、平头 (BUTT) 和方头 (SQUARE) 三种
        /**
         * 点 - 01
         * drawPoint(float x, float y, Paint paint)
         * x坐标， y坐标，Paint画笔
         */
        mCanvas.drawPoint(50, 50, mPaint);
        /**
         * 点 - 02
         * drawPoints(float[] pts, Paint paint)/drawPoints(float[] pts, int offset, int count, Paint paint)
         * pts 这个数组是点的坐标，每两个成一对；
         * offset 表示跳过数组的前几个数再开始记坐标；
         * count 表示一共要绘制几个点（2个数算一点）。
         */
        final float[] pts = {20, 20, 120, 120, 220, 220};
        mCanvas.drawPoints(pts, mPaint);
        mCanvas.drawPoints(pts, 2, 2, mPaint);//跳过
    }

    //圆形
    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(600, 600, 200, mPaint);
    }

    //矩形
    private void drawRec(Canvas mCanvas) {
        /**
         * 左上，右下的坐标，画笔
         */
        mCanvas.drawRect(200, 200, 100, 120, mPaint);
        /**
         * Rect
         * Paint
         */
        Rect rect = new Rect(200, 200, 260, 250);
        mCanvas.drawRect(rect, mPaint);
        /**
         * RectF 与Rect类似略有区别： 精度表示等方面
         * Paint
         */
        RectF rectF = new RectF(100, 100, 260, 250);
        mCanvas.drawRect(rectF, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;
    }

    //椭圆
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void drawOvl(Canvas mCanvas) {
        /**
         * 椭圆 - 01
         * RectF
         */
        RectF rectF = new RectF(200, 200, 600, 400);
        mCanvas.drawRect(rectF,mPaint);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(6);
        mCanvas.drawOval(rectF, mPaint);

        /**
         * 椭圆 - 02
         * 左上 右下坐标点
         */
        mCanvas.drawRect(100,100,200,400,mPaint);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(15);
        mCanvas.drawOval(100, 100, 200, 400, mPaint);


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initCanvas(canvas);
        initPain();

        drawOvl(canvas);

    }
}



























