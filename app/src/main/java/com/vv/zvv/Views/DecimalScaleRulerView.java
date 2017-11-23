package com.vv.zvv.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.vv.zvv.R;
import com.vv.zvv.Utils.DrawUtil;
import com.vv.zvv.Utils.TextUtil;

/**
 * description:小数点横向滚动器(可整数倍数放大)
 * author: zvv
 * date: 2017/11/14 09:52
 * update: 2017/11/14
 * version: 0.0
 */
public class DecimalScaleRulerView extends View {

    private static final String TAG = "DecimalScaleRulerView";
    private VelocityTracker mVelocityTracker;//速率跟踪
    private int mMinVelocity;// 最小速率
    private Scroller mScroller;
    private int mWidth;
    private int mHeight;

    private float mValue = 50;//默认值镇指向的值
    private float mMaxValue = 100;//最大刻度值
    private float mMinValue = 0;//最小刻度值
    private int mItemSpacing;// 刻度之间的间距(UI)
    private int mPerSpanValue = 1;//最大刻度（大刻度之间的差值）
    private int mMaxLineHeight;// 大刻度高度（UI）
    private int mMiddleLineHeight;// 中刻度高度（UI）
    private int mMinLineHeight;// 小刻度高度（UI）
    private int mLineWidth;//刻度线的宽（UI）
    private int mTextMarginBottom;//刻度数值下边距
    private float mTextHeight;// 文字高度

    private Paint mTextPaint; // 绘制文本的画笔
    private Paint mLinePaint;// 刻度线画笔

    private int mTotalLine;// 最小刻度线的个数
    private int mMaxOffset;// 最大偏移量
    private float mOffset; // 默认尺起始点在屏幕中心, offset是指尺起始点的偏移值
    private int mLastX, mMove;
    private OnValueChangeListener mListener;


    public DecimalScaleRulerView(Context context) {
        this(context, null);
    }

    public DecimalScaleRulerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public DecimalScaleRulerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context context) {
        mScroller = new Scroller(context);
        mMinVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();

        /*设置刻度线-刻度值高度*/
        mItemSpacing = DrawUtil.dip2px(14);
        mLineWidth = DrawUtil.dip2px(1);
        mMaxLineHeight = DrawUtil.dip2px(16);
        mMiddleLineHeight = DrawUtil.dip2px(12);
        mMinLineHeight = DrawUtil.dip2px(8);
        mTextMarginBottom = DrawUtil.dip2px(14);

        /*文字画笔*/
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(DrawUtil.sp2px(12));
        mTextPaint.setColor(0X80222222);
        mTextHeight = TextUtil.getFontHeight(mTextPaint);

        /*刻度线画笔*/
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStrokeWidth(mLineWidth);
//        mLinePaint.setColor(0X80222222);
        mLinePaint.setColor(getResources().getColor(R.color.bfbfbf));
    }

    /*设置参数值*/
    public void setParam(int itemSpacing, int maxLineHeight, int middleLineHeight, int minLineHeight, int textMarginTop, int textSize) {
        mItemSpacing = itemSpacing;
        mMaxLineHeight = maxLineHeight;
        mMiddleLineHeight = middleLineHeight;
        mMinLineHeight = minLineHeight;
        mTextMarginBottom = textMarginTop;
        mTextPaint.setTextSize(textSize);
    }

    /*初始化刻度线相关参数*/
    public void initViewParam(float defaultValue, float minValue, float maxValue, int spanValue) {
        this.mValue = defaultValue;
        this.mMaxValue = maxValue;
        this.mMinValue = minValue;
        this.mPerSpanValue = spanValue;

        this.mTotalLine = (int) (maxValue * 10 - minValue * 10) / spanValue + 1;// 含有小刻度的个数（之所以*10原因是，10个刻度）
        mMaxOffset = -(mTotalLine - 1) * mItemSpacing;

        mOffset = (minValue - defaultValue) / spanValue * mItemSpacing * 10;// 起始刻度值相对默认值的偏移量

        /**
         *  一般引起invalidate()操作的函数如下：
         1、setVisibility()方法：
         当View的可视状态在INVISIBLE/ VISIBLE 转换为GONE状态时，会间接调用requestLayout() 和invalidate方法。
         同时，由于整个个View树大小发生了变化，会请求measure()过程以及draw()过程，同样地，只绘制需要“重新绘制”的视图。
         */
        invalidate();// 系统会在你画之前用背景色将所选区域覆盖一次，默认背景色为白色,无论语句放在那里都是最后执行
        setVisibility(VISIBLE);
    }

    /**
     * 设置用于接收结果的监听器
     *
     * @param listener
     */
    public void setValueChangeListener(OnValueChangeListener listener) {
        mListener = listener;
    }

    /**
     * API
     * This is called during layout when the size of this view has changed.
     * If you were just added to the view hierarchy, you're called with the old values of 0.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            mWidth = w;
            mHeight = h;
            Log.d(TAG, "onSizeChanged: 控件宽度：" + mWidth);
        }
    }


    //绘制图片
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float left, height;
        String value;
        int alpha;
        float scale;
        int srcPointX = mWidth / 2; // 默认表尺起始点在屏幕中心

        /*循环绘制*/
        for (int i = 0; i < mTotalLine; i++) {
            left = srcPointX + mOffset + i * mItemSpacing;

            if (left < 0 || left > mWidth) {
                continue;//跳出本次循环
            }

            /*区分不同长度的刻度线*/
            if (i % 10 == 0) {
                height = mMaxLineHeight;
            } else if (i % 5 == 0) {
                height = mMiddleLineHeight;
            } else {
                height = mMinLineHeight;
            }

            /*设置透明度*/
            //暂时隐藏
            /*scale = 1 - Math.abs(left - srcPointX) / srcPointX;
            alpha = (int) (255 * scale * scale);
            mLinePaint.setAlpha(alpha);*/

            // drawLine(float startX, float startY, float stopX, float stopY,

            /*刻度线*/
//            canvas.drawLine(left, 0, left, height, mLinePaint);
            canvas.drawLine(left, mMaxLineHeight + DrawUtil.dip2px(20), left, mMaxLineHeight + DrawUtil.dip2px(20) - height, mLinePaint);

            if (i % 10 == 0) { // 大指标,要标注文字
//                value = String.valueOf((int) (mMinValue + i * mPerSpanValue / 10));
                value = String.valueOf((int) (mMinValue + i * mPerSpanValue / 10) * 10000);

                // mTextPaint.setAlpha(alpha);
                // drawText(@NonNull String text, float x, float y, @NonNull Paint paint) {
//                canvas.drawText(value,
//                        left - mTextPaint.measureText(value) / 2,
//                        height + mTextMarginBottom + mTextHeight - DrawUtil.dip2px(3),
//                        mTextPaint);

                canvas.drawText(value,
                        left - mTextPaint.measureText(value) / 2,
                        mTextMarginBottom,
                        mTextPaint);
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int xPosition = (int) event.getX();

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScroller.forceFinished(true);
                mLastX = xPosition;
                mMove = 0;
                Log.d(TAG, "ACTION_DOWN - mLastX：\t" + mLastX);
                break;
            case MotionEvent.ACTION_MOVE:
                mMove = (mLastX - xPosition);
                changeMoveAndValue();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                countMoveEnd();
                countVelocityTracker();
                return false;
            // break;
            default:
                break;
        }

        mLastX = xPosition;
        return true;
    }

    private void countVelocityTracker() {
        mVelocityTracker.computeCurrentVelocity(1000);
        float xVelocity = mVelocityTracker.getXVelocity();
        if (Math.abs(xVelocity) > mMinVelocity) {
            mScroller.fling(0, 0, (int) xVelocity, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
        }
    }

    private void countMoveEnd() {
        mOffset -= mMove;
        if (mOffset <= mMaxOffset) {
            mOffset = mMaxOffset;
        } else if (mOffset >= 0) {
            mOffset = 0;
        }

        mLastX = 0;
        mMove = 0;

        mValue = mMinValue + Math.round(Math.abs(mOffset) * 1.0f / mItemSpacing) * mPerSpanValue / 10.0f;
        mOffset = (mMinValue - mValue) * 10.0f / mPerSpanValue * mItemSpacing; // 矫正位置,保证不会停留在两个相邻刻度之间

        notifyValueChange();
        postInvalidate();
    }

    private void changeMoveAndValue() {
        mOffset -= mMove;
        if (mOffset <= mMaxOffset) {//滑到最右端
            mOffset = mMaxOffset;
            mMove = 0;
            mScroller.forceFinished(true);
            Log.d(TAG, "changeMoveAndValue: 最右！！！");
        } else if (mOffset >= 0) {
            mOffset = 0;
            mMove = 0;
            mScroller.forceFinished(true);
            Log.d(TAG, "changeMoveAndValue: 最左！！！");
        }
        mValue = mMinValue + Math.round(Math.abs(mOffset) * 1.0f / mItemSpacing) * mPerSpanValue / 10.0f;

        notifyValueChange();

        /**
         * API
         * Cause an invalidate to happen on a subsequent cycle through the event loop. Use this to invalidate the View from a non-UI thread.
         *This method can be invoked from outside of the UI thread only when this View is attached to a window.
         */
        postInvalidate();
    }

    private void notifyValueChange() {
        if (null != mListener) {
            mListener.onValueChange(mValue);
        }
    }

    public interface OnValueChangeListener {
        void onValueChange(float value);
    }

    /**
     * Called by a parent to request that a child update its values for mScrollX and mScrollY if necessary.
     * This will typically be done if the child is animating a scroll using a Scroller object.
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            if (mScroller.getCurrX() == mScroller.getFinalX()) { // over
                countMoveEnd();
            } else {
                int xPosition = mScroller.getCurrX();
                mMove = (mLastX - xPosition);
                changeMoveAndValue();
                mLastX = xPosition;
            }
        }
    }
}





























