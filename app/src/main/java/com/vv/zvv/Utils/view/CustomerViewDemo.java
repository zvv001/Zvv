package com.vv.zvv.Utils.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zvv on 2017/8/14 16:43.
 */
public class CustomerViewDemo extends View {
    //画笔
    Paint mPaint;

    //画布
    Canvas mCanvas;

    public CustomerViewDemo(Context context) {
        super(context);
        init();
    }

    public CustomerViewDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomerViewDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPain();
        initCanvas();
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
        mPaint.setColor(Color.GREEN);

        //线条宽
        mPaint.setStrokeWidth(20);// 线条宽度为 20 像素

        //文字大小
        mPaint.setTextSize(100);

        /**
         * 抗锯齿
         * mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
         */
        mPaint.setAntiAlias(true);

    }

    private void initCanvas() {
        mCanvas = new Canvas();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画布颜色
        canvas.drawColor(Color.BLUE);
//        canvas.drawColor(Color.parseColor("#883400ff"));
//        canvas.drawRGB(100, 200, 100);
//        canvas.drawARGB(100, 100, 200, 100);

        canvas.drawCircle(300, 300, 200, mPaint);
        canvas.drawText("Hello", 200, 300, mPaint);


    }
}



























