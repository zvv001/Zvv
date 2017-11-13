package com.vv.zvv.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zvv on 2017/4/26 14:41.
 */
public class MyTimeView extends View {

    //-----钟表相关-----


    //画笔
    private Paint mDialPaint;


    //颜色
    private int color = 0xFF000000;

    public MyTimeView(Context context) {
        super(context);
    }

    public MyTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth() / 2, getHeight() / 2);
        initPaint();

        //表盘
        initDial(canvas);

        //刻度线
        initScale(canvas);


    }

    private void initPaint() {
        mDialPaint = new Paint();
        mDialPaint.setColor(color);
        mDialPaint.setAntiAlias(true);
        mDialPaint.setStyle(Paint.Style.STROKE);
        mDialPaint.setStrokeWidth(3);
    }

    private void initDial(Canvas canvas) {
        canvas.drawCircle(0, 0, getWidth() / 2 - 10, mDialPaint);
        canvas.drawCircle(0, 0, 5, mDialPaint);
    }

    //刻度线
    private void initScale(Canvas canvas) {
        Path mPath = new Path();
        float r = getWidth() / 2 - 10;
        double degrees = Math.PI * 2 / 12;

        for (int i = 0; i < 12; i++) {
            mPath.reset();

            mPath.moveTo((float) (r * Math.cos(degrees * i)), (float) (r * Math.sin(degrees * i)));
            if ((i + 1) % 6 != 1) {
                mDialPaint.setStrokeWidth(3);
                mPath.lineTo((float) ((r - 15) * Math.cos(degrees * i)), (float) ((r - 15) * Math.sin(degrees * i)));
            } else {
                mDialPaint.setStrokeWidth(6);
                mPath.lineTo((float) ((r - 20) * Math.cos(degrees * i)), (float) ((r - 20) * Math.sin(degrees * i)));
            }
            canvas.drawPath(mPath, mDialPaint);
        }

        for (int i = 0; i < 12; i++) {
            canvas.save();
            /**
             * rotate()这个函数要注意，参数为正则顺时针，否则逆时针。
             * 这个参数要求必须是度数，如旋转90°，就填90，不能填pi/2. 就这块，纠结了大半天我。可以用Math.toDegrees() 和 Math.toRadians()互相转化 ° 和 弧度。
             */
            canvas.rotate(30 * i, 0, 0);
//            canvas.rotate(30, 0, 0);
            //刻度
            mDialPaint.setTextSize(30);
            if (i == 0) {
                canvas.drawText("12", -15, -(r - 50), mDialPaint);
            } else {
                canvas.drawText(String.valueOf(i), -15, -(r - 50), mDialPaint);
            }
            canvas.restore();
        }
    }
}





















