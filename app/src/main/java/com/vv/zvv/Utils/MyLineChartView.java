package com.vv.zvv.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zvv on 2017/4/25 11:26.
 * 自定义折线图
 */
public class MyLineChartView extends View {

    //-----View相关-----
    private float height;
    private float width;

    //-----统计图相关-----
    //x轴间隔
    private int xGap = 100;
    //y轴间隔
    private int yGap = 80;
    //x轴条目
    private int xAxisNumber = 7;
    //y轴条目
    private int yAxisNumber = 11;

    //-----模拟数据-----
    private String[] xText = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"};
    private String[] yText = new String[]{"0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
    private Double[] percent = new Double[]{0.0, 13.4, 45.7, 32.8, 51.0, 40.6, 90.8};
    private String title = "项目完成的进度（单位%）";

    //-----画笔-----
    //文字
    private Paint mTextPaint;
    //折线
    private Paint mLineChartPaint;
    //边框
    private Paint mLinePaint;
    //点
    private Paint mPointPaint;


    //-----颜色相关-----
    //文字
    int textColor = 0xFF888888;
    //折线
    int lineChartColor = 0xFF000000;
    //边框
    int lineColor = 0xFF888888;
    //点
    int pointColor = 0xFFFF0000;


    public MyLineChartView(Context context) {
        super(context);
    }

    public MyLineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画布移到左下角，留出100的空间给予文字填充
        canvas.translate(100, height - 100);
        //初始化画笔
        initPaint();

        //边框
        initLine(canvas);

        //黑点
        initPoint(canvas);

        //折线和折点
        initLineChart(canvas);

        //文字
        initText(canvas);
    }

    //初始化画笔
    private void initPaint() {
        //文字
        mTextPaint = new Paint();
        mTextPaint.setTextSize(30);
        mTextPaint.setColor(textColor);
        mTextPaint.setAntiAlias(true);

        //折线
        mLineChartPaint = new Paint();
        mLineChartPaint.setColor(lineChartColor);
        mLineChartPaint.setAntiAlias(true);
        mLineChartPaint.setStyle(Paint.Style.STROKE);
        mLineChartPaint.setStrokeWidth(3);

        //边框
        mLinePaint = new Paint();
        mLinePaint.setColor(lineColor);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(3);

        //点
        mPointPaint = new Paint();
        mPointPaint.setColor(pointColor);
        mPointPaint.setStyle(Paint.Style.FILL);
        mPointPaint.setAntiAlias(true);
    }

    //边框
    private void initLine(Canvas canvas) {
        //水平线
        Path mPath = new Path();
        for (int i = 0; i < yAxisNumber; i++) {

            //y轴
            if (i == 0) {
                mPath.moveTo(0, 0);
                mPath.lineTo(0, -(yAxisNumber - 1) * yGap);
            }

            //x轴
            mPath.moveTo(0, -i * yGap);
            mPath.rLineTo((xAxisNumber - 1) * xGap, 0);
        }
        canvas.drawPath(mPath, mLinePaint);
    }

    //圆点
    private void initPoint(Canvas canvas) {
        //x轴上圆点
        for (int i = 0; i < xAxisNumber; i++) {
            canvas.drawCircle(i * xGap, 0, 5, mPointPaint);
        }

    }

    //折线和圆点
    private void initLineChart(Canvas canvas) {
        Path mPath = new Path();
        float xPoint = 0;
        float yPoint = 0;
        //折线的点
        for (int i = 0; i < xAxisNumber; i++) {
            xPoint = i * xGap;
            if (percent[i] > 0) {
                yPoint = -(float) (percent[i] / 10 * yGap);
            }
            //线段
            mPath.lineTo(xPoint, yPoint);
            canvas.drawPath(mPath, mLineChartPaint);
            //折线的点
            canvas.drawCircle(xPoint, yPoint, 5, mPointPaint);
        }
    }

    private void initText(Canvas canvas) {

        //y轴上的文字
        for (int i = 0; i < yText.length; i++) {
            Rect mRect = new Rect();
            mTextPaint.getTextBounds(yText[i], 0, yText[i].length(), mRect);
            canvas.drawText(yText[i], -mRect.width() - 20, -yGap * i + mRect.height() / 2, mTextPaint);
        }

        //x轴上的文字
        for (int i = 0; i < xText.length; i++) {
            Rect mRec = new Rect();
//            mTextPaint.getTextBounds(xText[i], xText[i].length(), 0, mRec);//好好理解这个函数
            mTextPaint.getTextBounds(xText[i], 0, xText[i].length(), mRec);
            canvas.drawText(xText[i], xGap * i - mRec.width() / 2, mRec.height() * 2, mTextPaint);
        }

        //顶部文字
        Rect mRect = new Rect();
        mTextPaint.getTextBounds(title, 0, title.length(), mRect);
        canvas.drawText(title, 0, -((yAxisNumber - 1) * yGap + mRect.height()), mTextPaint);
    }
}
























