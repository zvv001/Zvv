package com.vv.zvv.Utils.view.LoopViewMaizi;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zvv on 2017/08/14
 */
public class LoopView extends RelativeLayout {
    final static int LoopR = 200;

    Context con;
    ValueAnimator restAnimator = null;//回位动画
    ValueAnimator rAnimation = null;//半径动画
    GestureDetector mGestureDetector = null;//手势类

    private int selectItem = 0;//当前选择项
    private int size = 0;//个数
    private float r = LoopR;//半径
    private float distance = 3 * r;//camera和观察的旋转物体距离， 距离越长,最大物体和最小物体比例越不明显
    private float angle = 0;//角度
    private float last_angle = 0;
    private boolean autoRotation = false;//自动旋转
    private boolean touching = false;//正在触摸

    List<View> views = new ArrayList<>();//子view引用列表

    LoopHandler loopHandler = new LoopHandler(3000) {
        @Override
        public void du() {
            try {
                if (size != 0) AnimRotationTo(angle - 360 / size, null);
            } catch (Exception e) {
            }
        }
    };

    private OnItemSelectedListenerMaizi onItemSelectedListener = null;//选择事件接口

    public LoopView(Context context) {
        super(context);
        init(context);
    }

    public LoopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.con = context;
        mGestureDetector = new GestureDetector(context, getGeomeryController());
    }

    private void sortList(List<View> list) {
        Comparator comp = new SortComparator();
        Collections.sort(list, comp);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).bringToFront();
            list.get(i).setEnabled(i == (list.size() - 1) && angle % (360 / size) == 0 ? true : false);
        }
    }

    private class SortComparator implements Comparator<View> {
        @Override
        public int compare(View lhs, View rhs) {
            int result = 0;
            try {
                result = (int) (1000 * lhs.getScaleX() - 1000 * rhs.getScaleX());
            } catch (Exception e) {
            }
            return result;
        }
    }

    private GestureDetector.SimpleOnGestureListener getGeomeryController() {
        return new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.i("ds","distance:"+distanceX);
                angle += distanceX / 4;
                invate();
                return true;
            }
        };
    }

    public void invate() {
        int width = getWidth();
        for (int i = 0; i < views.size(); i++) {
            float x0 = (float) Math.sin(Math.toRadians(angle + 180 - i * 360 / size)) * r;
            float y0 = (float) Math.cos(Math.toRadians(angle + 180 - i * 360 / size)) * r;
            float scale0 = (distance - y0) / (distance + r);
            views.get(i).setScaleX(scale0);
            views.get(i).setScaleY(scale0);
            views.get(i).setX(width / 2 + x0 - views.get(i).getWidth() / 2);
        }
        List<View> arr = new ArrayList<>();
        for (int i = 0; i < views.size(); i++) {
            arr.add(views.get(i));
            views.get(i).setTag(i);
        }
        sortList(arr);
        postInvalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        invate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            InitData();
            RAnimation();
        }
    }

    public void RAnimation() {
        RAnimation(1f, r);
    }

    public void RAnimation(boolean fromZeroToLoopR) {
        if (fromZeroToLoopR) {
            RAnimation(1f, LoopR);
        } else {
            RAnimation(LoopR, 1f);
        }
    }

    public void RAnimation(float from, float to) {
        rAnimation = ValueAnimator.ofFloat(from, to);
        rAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                r = (Float) valueAnimator.getAnimatedValue();
                invate();
            }
        });
        rAnimation.setInterpolator(new DecelerateInterpolator());
        rAnimation.setDuration(2000);
        rAnimation.start();
    }


    public void InitData() {
        initView();
        if (onItemSelectedListener != null) {
            onItemSelectedListener.selected(selectItem, views.get(selectItem));
        }
    }

    //获取子布局的个数，添加到数组中
    private void initView() {
        for (int i = 0; i < views.size(); i++) {
            views.remove(i);
        }
        final int count = getChildCount();
        size = count;
        for (int i = 0; i < count; i++) {
            views.add(getChildAt(i));
        }
    }

    private void restPosition() {
        if (size == 0) {
            return;
        }
        float finall = 0;
        float part = 360 / size;//一份的角度
        if (angle < 0) {
            part = -part;
        }
        float minvalue = (int) (angle / part) * part;//最小角度
        float maxvalue = (int) (angle / part) * part + part;//最大角度
        if (angle >= 0) {//分为是否小于0的情况
            if (angle - last_angle > 0) {
                finall = maxvalue;
            } else {
                finall = minvalue;
            }
        } else {
            if (angle - last_angle < 0) {
                finall = maxvalue;
            } else {
                finall = minvalue;
            }
        }
        AnimRotationTo(finall, null);
    }


    private void AnimRotationTo(float finall, final Runnable complete) {
        if (angle == finall) {
            return;
        }
        restAnimator = ValueAnimator.ofFloat(angle, finall);
        restAnimator.setInterpolator(new DecelerateInterpolator());
        restAnimator.setDuration(300);

        restAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (touching == false) {
                    angle = (Float) animation.getAnimatedValue();
                    invate();
                }
            }
        });
        restAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (touching == false) {
                    selectItem = calculateItem();
                    if (selectItem < 0) {
                        selectItem = size + selectItem;
                    }
                    if (onItemSelectedListener != null) {
                        onItemSelectedListener.selected(selectItem, views.get(selectItem));
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        if (complete != null) {
            restAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    complete.run();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
        restAnimator.start();
    }

    private int calculateItem() {
        return (int) (angle / (360 / size)) % size;
    }

    private boolean onTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            last_angle = angle;
            touching = true;
        }
        boolean sc = mGestureDetector.onTouchEvent(event);
        if (sc) {
            this.getParent().requestDisallowInterceptTouchEvent(true);//通知父控件勿拦截本控件
        }
        if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            touching = false;
            restPosition();
            return true;
        }
        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //onTouch(event);
        return true;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        onTouch(ev);
        return super.dispatchTouchEvent(ev);
    }

    public List<View> getViews() {
        return views;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getR() {
        return r;
    }

    public int getSelectItem() {
        return selectItem;
    }

    /*selecItem must > 0*/
    public void setSelectItem(int selectItem) {
        if (selectItem > 0) {
            this.selectItem = selectItem;
            if (size > 0) AnimRotationTo(selectItem * (360 / size), null);
        }
    }

    public void setR(float r) {
        this.r = r;
        distance = 3 * r;
    }

    public void setOnItemSelectedListener(OnItemSelectedListenerMaizi onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public void setAutoRotation(boolean autoRotation) {
        loopHandler.setLoop(autoRotation);
    }

    public long getAutoRotationTime() {
        return loopHandler.loopTime;
    }

    public void setAutoRotationTime(long autoRotationTime) {
        loopHandler.setLoopTime(autoRotationTime);
    }

    public boolean isAutoRotation() {
        return autoRotation;
    }
}

