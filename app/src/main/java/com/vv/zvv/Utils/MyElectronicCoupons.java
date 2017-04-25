package com.vv.zvv.Utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by zvv on 2017/4/24 10:13.
 * <p/>
 * 自定义电子券
 */
public class MyElectronicCoupons extends LinearLayout {
    private Paint mPaint;
    //圆的半径
    private int radius = 8;
    //间隔
    private int gap = 8;

    public MyElectronicCoupons(Context context) {
        super(context);
        init();
    }


    public MyElectronicCoupons(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyElectronicCoupons(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyElectronicCoupons(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int CircleCount = getWidth() / (radius * 2 + gap * 2)+1;//要不要加1看实际效果
        for (int i = 0; i < CircleCount; i++) {
            canvas.drawCircle((radius + gap) * (2 * i - 1), 0, radius, mPaint);
            canvas.drawCircle((radius + gap) * (2 * i - 1), getHeight(), radius, mPaint);
        }
    }
}
