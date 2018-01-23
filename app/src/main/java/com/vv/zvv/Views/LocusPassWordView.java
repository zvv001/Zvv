package com.vv.zvv.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.vv.zvv.R;
import com.vv.zvv.Utils.LocusPassWordManagerUtil;
import com.vv.zvv.Utils.LogUtil;
import com.vv.zvv.Utils.MathUtil;
import com.vv.zvv.Utils.RoundUtil;
import com.vv.zvv.Utils.SharkBitmapUtil;
import com.vv.zvv.Utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * description:手势密码
 * author: zvv
 * date: 2017/11/30 11:33
 * update: 2017/11/30
 * version: 0.0
 */
public class LocusPassWordView extends View {
    Context con;
    boolean movingNoPoint = false;
    float moveingX, moveingY;
    private float w = 0;
    private float h = 0;
    private boolean isCache = false;
    private boolean isDestroy = false;
    //
    private Paint mPaint = new Paint();
    //
    private Point[][] mPoints = new Point[3][3];
    // 圆的半径
    private float r = 0;
    // 选中的点
    private List<Point> sPoints = new ArrayList<Point>();
    private boolean checking = false;
    private Bitmap locus_round_original;//圆点初始状态时的图片
    private Bitmap locus_round_click;//圆点点击时的图片
    private Bitmap locus_round_click_error;//出错时圆点的图片
    private Bitmap locus_line;//正常状态下线的图片
    private Bitmap locus_arrow;//线的移动方向
    private Bitmap locus_line_error;//错误状态下的线的图片
    private long CLEAR_TIME = 2000;//清除痕迹的时间
    private boolean isTouch = true; // 是否可操作
    private Matrix mMatrix = new Matrix();
    private int lineAlpha = 100;//连线的透明度
    private boolean show_arrow = false;//展示箭头
    private float r_padding = 4;//不穿过圆时，连接线和原的距离值。用来修正连接线和圆连接不准的问题
    private String TooShort = "最少连接4个点，请重新输入";//密码过短时文本
    private Timer timer = new Timer();
    private TimerTask task = null;
    //
    private OnCompleteListener mCompleteListener;

    public LocusPassWordView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        System.out.println("init()");
        init(context);
    }

    public LocusPassWordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        System.out.println("init()");
        init(context);
    }

    public LocusPassWordView(Context context) {
        super(context);
        System.out.println("init()");
        init(context);
    }

    /*---------------------------------
         * 绘制图片
         * @param       x屏幕上的x坐标
         * @param       y屏幕上的y坐标
         * @param       w要绘制的图片的宽度
         * @param       h要绘制的图片的高度
         * @param       bx图片上的x坐标
         * @param       by图片上的y坐标
         *
         * @return      null
         ------------------------------------*/
    public static void drawImage(Canvas canvas, Bitmap blt, int x, int y,
                                 int w, int h, int bx, int by) {
        Rect src = new Rect();// 图片 >>原矩形
        Rect dst = new Rect();// 屏幕 >>目标矩形

        src.left = bx;
        src.top = by;
        src.right = bx + w;
        src.bottom = by + h;

        dst.left = x;
        dst.top = y;
        dst.right = x + w;
        dst.bottom = y + h;
        // 画出指定的位图，位图将自动--》缩放/自动转换，以填补目标矩形
        // 这个方法的意思就像 将一个位图按照需求重画一遍，画后的位图就是我们需要的了
        canvas.drawBitmap(blt, null, dst, null);
        src = null;
        dst = null;
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (isDestroy) {
            LogUtil.e(LocusPassWordView.class, "destroy is called,can't draw canvas");
            return;
        }
        if (!isCache) {
            initCache();
        }
        drawToCanvas(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        w = widthMeasureSpec;
        h = heightMeasureSpec;
        super.setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        this.postInvalidate();
    }


    //---------------------------------------
    public void init(Context con) {
        this.con = con.getApplicationContext();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        r_padding = getResources().getDimension(R.dimen.locus_r_padding);
    }


    public float getR_padding() {
        return r_padding;
    }

    /**
     * @param r_padding
     * @不穿过圆时连接线和原的距离值 用来修正连接线和圆连接不准的问题
     */
    public void setR_padding(float r_padding) {
        this.r_padding = r_padding;
    }

    private String getLinks() {


        String s = "";
        try {
            for (int i = 0; i < mPoints.length; i++) {
                for (int j = 0; j < mPoints[i].length; j++) {
                    Point p = mPoints[i][j];
                    if (p.state != Point.STATE_NORMAL) {
                        s += getIndex(i, j);
                    }
                }
            }
        } catch (Exception e) {
        }


        return s;

    }

    public String getTooShort() {
        return TooShort;
    }

    public void setTooShort(String tooShort) {
        TooShort = tooShort;
    }

    private String getIndex(int i, int j) {
        /**
         mPoints[0][0] ;0
         mPoints[0][1] ;1
         mPoints[0][2];2
         mPoints[1][0];3
         mPoints[1][1] ;4
         mPoints[1][2];5
         mPoints[2][0] ;6
         mPoints[2][1] ;7
         mPoints[2][2] ;8
         **/
        String s = "";
        if (i == 0 && j == 0) {
            s = "0";
        } else if (i == 0 && j == 1) {
            s = "1";
        } else if (i == 0 && j == 2) {
            s = "2";
        } else if (i == 1 && j == 0) {
            s = "3";
        } else if (i == 1 && j == 1) {
            s = "4";
        } else if (i == 1 && j == 2) {
            s = "5";
        } else if (i == 2 && j == 0) {
            s = "6";
        } else if (i == 2 && j == 1) {
            s = "7";
        } else if (i == 2 && j == 2) {
            s = "8";
        } else {

        }
        return s;
    }

    private void drawToCanvas(Canvas canvas) {
        /**
         mPaint.setColor(Color.RED);
         Point p1 = mPoints[1][1];
         Rect r1 = new Rect((int)(p1.x - r),(int)(p1.y - r),(int)(p1.x +
         locus_round_click.getWidth() - r),(int)(p1.y+locus_round_click.getHeight()-
         r));
         canvas.drawRect(r1, mPaint);
         **/


        /**
         mPaint.setColor(Color.BLUE);
         canvas.drawLine(r1.left+r1.width()/2, r1.top, r1.left+r1.width()/2,
         r1.bottom, mPaint);
         canvas.drawLine(r1.left, r1.top+r1.height()/2, r1.right,
         r1.bottom-r1.height()/2, mPaint);
         **/
        // 画连线
        if (sPoints.size() > 0) {
            int tmpAlpha = mPaint.getAlpha();
            mPaint.setAlpha(lineAlpha);
            Point tp = sPoints.get(0);
            for (int i = 1; i < sPoints.size(); i++) {
                Point p = sPoints.get(i);
                drawLine(canvas, tp, p);
                tp = p;
            }
            if (this.movingNoPoint) {
                drawLine(canvas, tp, new Point((int) moveingX, (int) moveingY));
            }
            mPaint.setAlpha(tmpAlpha);
            lineAlpha = mPaint.getAlpha();
        }


        // 画所有点
        for (int i = 0; i < mPoints.length; i++) {
            for (int j = 0; j < mPoints[i].length; j++) {
                Point p = mPoints[i][j];
                if (p.state == Point.STATE_CHECK) {
                    canvas.drawBitmap(locus_round_click, p.x - r, p.y - r, mPaint);
                    //drawImage(canvas,locus_round_click,(int)(p.x - r), (int)(p.y - r),(int)(point_width*2),(int)(point_width*2),0,0);
                } else if (p.state == Point.STATE_CHECK_ERROR) {
                    canvas.drawBitmap(locus_round_click_error, p.x - r, p.y - r, mPaint);
                    //drawImage(canvas,locus_line_error,(int)(p.x - r), (int)(p.y - r),(int)(point_width*2),(int)(point_width*2),0,0);
                } else {
                    canvas.drawBitmap(locus_round_original, p.x - r, p.y - r, mPaint);
                    //drawImage(canvas,locus_round_original,(int)(p.x - r), (int)(p.y - r),(int)(point_width*2),(int)(point_width*2),0,0);
                }
            }
        }


    }

    /**
     * 初始化Cache信息
     *
     * @paramcanvas
     */
    private void initCache() {

        w = this.getWidth();
        h = this.getHeight();
        float x = 0;
        float y = 0;

        // 以最小的为准
        // 纵屏
        if (w > h) {
            x = (w - h) / 2;
            w = h;
        }
        // 横屏
        else {
            y = (h - w) / 2;
            h = w;
        }

        //手势背景图片 v
        locus_round_original = BitmapFactory.decodeResource(this.getResources(), R.drawable.locus_round_original);
        locus_round_click = BitmapFactory.decodeResource(this.getResources(), R.drawable.locus_round_click);
        locus_round_click_error = BitmapFactory.decodeResource(this.getResources(), R.drawable.locus_round_click_error);

        locus_line = BitmapFactory.decodeResource(this.getResources(), R.drawable.locus_line);


        locus_line_error = BitmapFactory.decodeResource(this.getResources(), R.drawable.locus_line_error);


        locus_arrow = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.locus_arrow);
        // Log.d("Canvas w h :", "w:" + w + " h:" + h);

        // 计算圆圈图片的大小
        float canvasMinW = w;
        if (w > h) {
            canvasMinW = h;
        }
        float roundMinW = canvasMinW / 8.0f * 2;
        float roundW = roundMinW / 2.f;
        //
        float deviation = canvasMinW % (8 * 2) / 2;
        x += deviation;
        x += deviation;


        if (locus_round_original.getWidth() > roundMinW) {
            float sf = roundMinW * 1.0f / locus_round_original.getWidth(); // 取得缩放比例，将所有的图片进行缩放
            locus_round_original = SharkBitmapUtil.zoom(locus_round_original, sf);
            locus_round_click = SharkBitmapUtil.zoom(locus_round_click, sf);
            locus_round_click_error = SharkBitmapUtil.zoom(locus_round_click_error, sf);

            locus_line = SharkBitmapUtil.zoom(locus_line, sf);

            locus_line_error = SharkBitmapUtil.zoom(locus_line_error, sf);
            locus_arrow = SharkBitmapUtil.zoom(locus_arrow, sf);
            roundW = locus_round_original.getWidth() / 2;


        }


        mPoints[0][0] = new Point(x + 0 + roundW, y + 0 + roundW);
        mPoints[0][1] = new Point(x + w / 2, y + 0 + roundW);
        mPoints[0][2] = new Point(x + w - roundW, y + 0 + roundW);
        mPoints[1][0] = new Point(x + 0 + roundW, y + h / 2);
        mPoints[1][1] = new Point(x + w / 2, y + h / 2);
        mPoints[1][2] = new Point(x + w - roundW, y + h / 2);
        mPoints[2][0] = new Point(x + 0 + roundW, y + h - roundW);
        mPoints[2][1] = new Point(x + w / 2, y + h - roundW);
        mPoints[2][2] = new Point(x + w - roundW, y + h - roundW);
        int k = 0;
        for (Point[] ps : mPoints) {
            for (Point p : ps) {
                p.index = k;
                k++;
            }
        }
        r = locus_round_original.getHeight() / 2;// roundW;
        isCache = true;
    }

    /**
     * 画两点的连接
     *
     * @param canvas
     * @param a
     * @param b
     */
    private void drawLine(Canvas canvas, Point a, Point b) {
        float ah = (float) MathUtil.distance(a.x, a.y, b.x, b.y);

        float r_width = r - r_padding;

        if (ah < r_width * 2) {
            return;
        }//如果没有超出，则不画线

        float degrees = getDegrees(a, b);
        // Log.d("=============x===========", "rotate:" + degrees);
        canvas.rotate(degrees, a.x, a.y);
        if (a.state == Point.STATE_CHECK_ERROR) {
            mMatrix.setScale((ah - 2 * r_width)
                    / locus_line_error.getWidth(), 1);
            mMatrix.postTranslate(a.x + r_width, a.y - locus_line_error.getHeight()
                    / 2.0f);
            canvas.drawBitmap(locus_line_error, mMatrix, mPaint);
        } else {
            mMatrix.setScale((ah - 2 * r_width) / locus_line.getWidth(), 1);
            mMatrix.postTranslate(a.x + r_width, a.y - locus_line.getHeight() / 2.0f);
            canvas.drawBitmap(locus_line, mMatrix, mPaint);
        }
        if (show_arrow)
            canvas.drawBitmap(locus_arrow, a.x, a.y - locus_arrow.getHeight() / 2.0f, mPaint);

        canvas.rotate(-degrees, a.x, a.y);

    }

    public float getDegrees(Point a, Point b) {
        float ax = a.x;// a.index % 3;
        float ay = a.y;// a.index / 3;
        float bx = b.x;// b.index % 3;
        float by = b.y;// b.index / 3;
        float degrees = 0;
        if (bx == ax) // y轴相等 90度或270
        {
            if (by > ay) // 在y轴的下边 90
            {
                degrees = 90;
            } else if (by < ay) // 在y轴的上边 270
            {
                degrees = 270;
            }
        } else if (by == ay) // y轴相等 0度或180
        {
            if (bx > ax) // 在y轴的下边 90
            {
                degrees = 0;
            } else if (bx < ax) // 在y轴的上边 270
            {
                degrees = 180;
            }
        } else {
            if (bx > ax) // 在y轴的右边 270~90
            {
                if (by > ay) // 在y轴的下边 0 - 90
                {
                    degrees = 0;
                    degrees = degrees
                            + switchDegrees(Math.abs(by - ay),
                            Math.abs(bx - ax));
                } else if (by < ay) // 在y轴的上边 270~0
                {
                    degrees = 360;
                    degrees = degrees
                            - switchDegrees(Math.abs(by - ay),
                            Math.abs(bx - ax));
                }

            } else if (bx < ax) // 在y轴的左边 90~270
            {
                if (by > ay) // 在y轴的下边 180 ~ 270
                {
                    degrees = 90;
                    degrees = degrees
                            + switchDegrees(Math.abs(bx - ax),
                            Math.abs(by - ay));
                } else if (by < ay) // 在y轴的上边 90 ~ 180
                {
                    degrees = 270;
                    degrees = degrees
                            - switchDegrees(Math.abs(bx - ax),
                            Math.abs(by - ay));
                }

            }

        }
        return degrees;
    }

    /**
     * 1=30度 2=45度 4=60度
     *
     * @param
     * @return
     */
    private float switchDegrees(float x, float y) {
        return (float) MathUtil.pointTotoDegrees(x, y);
    }

    /**
     * 取得数组下标
     *
     * @param index
     * @return
     */
    public int[] getArrayIndex(int index) {
        int[] ai = new int[2];
        ai[0] = index / 3;
        ai[1] = index % 3;
        return ai;
    }

    /**
     * 检查
     *
     * @param x
     * @param y
     * @return
     */
    private Point checkSelectPoint(float x, float y) {
        for (int i = 0; i < mPoints.length; i++) {
            for (int j = 0; j < mPoints[i].length; j++) {
                Point p = mPoints[i][j];
                if (RoundUtil.checkInRound(p.x, p.y, r, (int) x, (int) y)) {
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * 重置
     */
    private void reset() {
        for (Point p : sPoints) {
            p.state = Point.STATE_NORMAL;
        }
        sPoints.clear();
        this.enableTouch();
        this.postInvalidate();
    }

    /**
     * 判断点是否有交叉 返回 0,新点 ,1 与上一点重叠 2,与非最后一点重叠
     *
     * @param p
     * @return
     */
    private int crossPoint(Point p) {
        // 重叠的不最后一个则 reset
        if (sPoints.contains(p)) {
            if (sPoints.size() > 2) {
                // 与非最后一点重叠
                if (sPoints.get(sPoints.size() - 1).index != p.index) {
                    return 2;
                }
            }
            return 1; // 与最后一点重叠
        } else {
            return 0; // 新点
        }
    }

    /**
     * 添加一个点
     *
     * @param point
     */
    private void addPoint(Point point) {
        this.sPoints.add(point);
    }

    /**
     * 转换为String
     *
     * @return
     * @parampoints
     */
    private String toPointString() {
        StringBuffer sf = new StringBuffer();
        for (Point p : sPoints) {
            sf.append(",");
            sf.append(p.index);
        }
        return sf.deleteCharAt(0).toString();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            // 不可操作
            if (!isTouch) {
                return false;
            }

            movingNoPoint = false;

            float ex = event.getX();
            float ey = event.getY();
            boolean isFinish = false;
            boolean redraw = false;
            Point p = null;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: // 点下
                    // 如果正在清除密码,则取消
                    if (task != null) {
                        task.cancel();
                        task = null;
                        Log.d("task", "touch cancel()");
                    }
                    // 删除之前的点
                    reset();
                    p = checkSelectPoint(ex, ey);
                    if (p != null) {
                        checking = true;
                    }
                    break;
                case MotionEvent.ACTION_MOVE: // 移动
                    if (checking) {
                        p = checkSelectPoint(ex, ey);
                        if (p == null) {
                            movingNoPoint = true;
                            moveingX = ex;
                            moveingY = ey;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP: // 提起
                    p = checkSelectPoint(ex, ey);
                    checking = false;
                    isFinish = true;
                    break;
            }
            if (!isFinish && checking && p != null) {

                int rk = crossPoint(p);
                if (rk == 2) // 与非最后一重叠
                {
                    // reset();
                    // checking = false;

                    movingNoPoint = true;
                    moveingX = ex;
                    moveingY = ey;

                    redraw = true;
                } else if (rk == 0) // 一个新点
                {
                    p.state = Point.STATE_CHECK;
                    addPoint(p);
                    redraw = true;
                }
                // rk == 1 不处理

            }


            if (isFinish) {
                /*
                if (this.sPoints.size() == 1) {
                    this.reset();
                } else
                */
                if (mCompleteListener != null) {
                    mCompleteListener.onComplete(toPointString(), getLinks());
                } else {

                }
            }
        } catch (Exception e) {
        }
        this.postInvalidate();
        return true;
    }

    /**
     * 设置已经选中的为错误
     */
    private void error() {
        for (Point p : sPoints) {
            p.state = Point.STATE_CHECK_ERROR;
        }
    }

    /**
     * 设置为输入错误
     */
    public void markError() {
        markError(CLEAR_TIME);
    }

    /**
     * 设置为输入错误
     */
    public void markError(final long time) {
        for (Point p : sPoints) {
            p.state = Point.STATE_CHECK_ERROR;
        }
        this.clearPassword(time);
    }

    /**
     * 设置为可操作
     */
    public void enableTouch() {
        isTouch = true;
    }

    /**
     * 设置为不可操作
     */
    public void disableTouch() {
        isTouch = false;
        Log.i("ds", "disibletouch");
    }

    /**
     * 清除密码
     */
    public void clearPassword() {
        clearPassword(CLEAR_TIME);
    }

    /*
    *展示箭头
     */

    /**
     * 清除密码
     */
    public void clearPassword(final long time) {
        if (time > 1) {
            if (task != null) {
                task.cancel();
//                Log.d("task", "清除 cancel()");
            }
            lineAlpha = 130;
            this.postInvalidate();
            task = new TimerTask() {
                public void run() {
                    reset();

                }
            };
//            Log.d("task", "清除 schedule(" + time + ")");
            timer.schedule(task, time);
        } else {
            reset();

        }

    }

    public void setShow_arrow(boolean show_arrow) {
        this.show_arrow = show_arrow;
    }

    /**
     * @param mCompleteListener
     */
    public void setOnCompleteListener(OnCompleteListener mCompleteListener) {
        this.mCompleteListener = mCompleteListener;
    }


    /**
     * 取得密码
     *
     * @return
     */
    private String getPassword() {

        return new LocusPassWordManagerUtil(con).getPassword(); // , "0,1,2,3,4,5,6,7,8"
    }

    /**
     * 密码是否为空
     *
     * @return
     */
    public boolean isPasswordEmpty() {
        return StringUtil.isEmpty(getPassword());
    }

    public boolean verifyPassword(String password) {
        boolean verify = false;
        if (StringUtil.isNotEmpty(password)) {
            // 或者是超级密码
            if (password.equals(getPassword())) {
                verify = true;
            }
        }
        return verify;
    }

    /**
     * 设置密码
     *
     * @param password
     */
    public void resetPassWord(String password) {
        new LocusPassWordManagerUtil(con).resetPassWord(password);
    }


    /**
     * 轨迹球画完成事件
     *
     * @author way
     */
    public interface OnCompleteListener {
        /**
         * 画完了
         *
         * @paramstr
         */
        public void onComplete(String password, String link);
    }

    /*控件销毁时调用释放图片*/
    public void onDestroy() throws Exception {
        locus_round_original.recycle();//圆点初始状态时的图片
        locus_round_click.recycle();//圆点点击时的图片
        locus_round_click_error.recycle();//出错时圆点的图片
        locus_line.recycle();//正常状态下线的图片
        locus_arrow.recycle();//线的移动方向
        locus_line_error.recycle();//错误状态下的线的图片

        locus_round_original = null;//圆点初始状态时的图片
        locus_round_click = null;//圆点点击时的图片
        locus_round_click_error = null;//出错时圆点的图片
        locus_line = null;//正常状态下线的图片
        locus_arrow = null;//线的移动方向
        locus_line_error = null;//错误状态下的线的图片
        con = null;
        timer.cancel();
        isDestroy = true;
    }
}