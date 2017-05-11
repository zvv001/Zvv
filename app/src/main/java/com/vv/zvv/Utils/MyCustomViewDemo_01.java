package com.vv.zvv.Utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zvv on 2017/4/26 13:30.
 * <p/>
 * 自定义一些ViewDemo
 */
public class MyCustomViewDemo_01 extends View {

    private Paint mPaint;
    private int color = 0xFF000000;

    public MyCustomViewDemo_01(Context context) {
        super(context);
    }

    public MyCustomViewDemo_01(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomViewDemo_01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initPaint(canvas);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initPaint(Canvas canvas) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);

        canvas.drawArc(0,0,200,100,0,270,false,mPaint);
    }
}





























