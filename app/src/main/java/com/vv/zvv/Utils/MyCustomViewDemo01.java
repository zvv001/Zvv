package com.vv.zvv.Utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by zvv on 2017/4/24 11:30.
 */
public class MyCustomViewDemo01 extends LinearLayout {
    private Paint mPaint;
    private Path mPath;

    public MyCustomViewDemo01(Context context) {
        super(context);
        init();
    }

    public MyCustomViewDemo01(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCustomViewDemo01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyCustomViewDemo01(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setDither(true);
//        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStrokeWidth(5);
        // 设置抗锯齿
        mPaint.setAntiAlias(true);


        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(100, 100, 300, 300);
        mPath.addRect(rectF, Path.Direction.CW);//矩形
        mPath.addCircle(300,300,100, Path.Direction.CW);//圆形
//        mPath.setFillType(Path.FillType.WINDING);//默认值，当两个图形相交，正常相交情况显示
//        mPath.setFillType(Path.FillType.EVEN_ODD);//取path所在并不相交的区域
//        mPath.setFillType(Path.FillType.INVERSE_WINDING);//取path的外部区域
        mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);//取path外部和相交区域

        canvas.drawPath(mPath, mPaint);
    }
}
