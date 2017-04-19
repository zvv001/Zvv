package com.vv.zvv.Utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vv.zvv.R;

/**
 * Created by zvv on 2017/4/19 09:47.
 * 自定义ViewPager并实现滑动监听
 */
public class MyViewPager extends ViewGroup {
    private static final String TAG = "MyViewPager";
    private GestureDetector mGestureDetector;
    //图片的ID
    int[] imageId = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3, R.drawable.guide4};

    public MyViewPager(Context context) {
        super(context);
        initView();
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }


    private void initView() {
        for (int i = 0; i < imageId.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(imageId[i]);
            this.addView(imageView);
        }

//        mGestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
//            @Override
//            public boolean onDown(MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onShowPress(MotionEvent e) {
//
//            }
//
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                //
//                scrollBy((int) distanceX, 0);
//                return false;
//            }
//
//            @Override
//            public void onLongPress(MotionEvent e) {
//
//            }
//
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                return false;
//            }
//        });
    }

    /**
     * 设置ViewPager中图片的的位置
     *
     * @param changed
     * @param l       左
     * @param t       上
     * @param r       右
     * @param b       下
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < imageId.length; i++) {
            //将图片依次铺开
            this.getChildAt(i).layout(i * getWidth(), t, (i + 1) * getWidth(), b);
        }
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //将Touch事件委托给mGestureDetector进行处理，消耗Touch事件
//        mGestureDetector.onTouchEvent(event);
////        return super.onTouchEvent(event);
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//
//                break;
//            case MotionEvent.ACTION_HOVER_MOVE:
//
//                break;
//            case MotionEvent.ACTION_UP://离开
//                int scrollX = getScrollX();
//                Log.d(TAG, "onTouchEvent: " + scrollX);
//
//                break;
//        }
//        return true;
//    }
}
