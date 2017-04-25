package com.vv.zvv.Utils;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.vv.zvv.R;

/**
 * Created by zvv on 2017/4/24 14:57.
 * 待验证 - todo
 * 预期效果五角星
 */
public class MyLine extends View {
    private Paint mPaint;
    private Path mPath;
    private PathMeasure pathMeasure;
    //path路径的总长度
    private int mLenght;
    //当前路径的长度
    private int mCurrentPath;
    private float[] currentPosition;
    private Bitmap bitmap;
    private float pointX[] = new float[5];
    private float pointY[] = new float[5];

    public MyLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPath = new Path();
        currentPosition = new float[2];
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo);
        mPaint.setColor(Color.parseColor("#ff0000"));
        mPaint.setStyle(Paint.Style.STROKE);
        //初始化我们的五角星五个点坐标
        pointX[0] = 113.5f;
        pointY[0] = 150f;
        pointX[1] = 286.5f;
        pointY[1] = 150f;
        pointX[2] = 150f;
        pointY[2] = 286.5f;
        pointX[3] = 200f;
        pointY[3] = 100f;
        pointX[4] = 250f;
        pointY[4] = 286.5f;
        //将mPath移动到第一个点作为起点
        mPath.moveTo(pointX[0], pointY[0]);
        for (int i = 1; i < 5; i++) {
            mPath.lineTo(pointX[i], pointY[i]);
        }
        mPath.lineTo(pointX[0], pointY[0]);
        pathMeasure = new PathMeasure();
        pathMeasure.setPath(mPath, true);
        mLenght = (int) pathMeasure.getLength();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
        if (mCurrentPath == 0 && currentPosition[0] == 0 && currentPosition[1] == 0) {
            startAnimator();
        } else {
            canvas.drawBitmap(bitmap, currentPosition[0] - bitmap.getWidth() / 2, currentPosition[1] - bitmap.getHeight() / 2, mPaint);
        }
    }

    private void startAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mLenght);
        valueAnimator.setDuration(5000);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentPath = (int) animation.getAnimatedValue();
                pathMeasure.getPosTan(mCurrentPath, currentPosition, null);
                invalidate();
            }
        });
        valueAnimator.start();
    }
}
