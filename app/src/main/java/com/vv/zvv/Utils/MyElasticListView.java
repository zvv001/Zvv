package com.vv.zvv.Utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * Created by zvv on 2017/4/17 14:29.
 * 自定义弹性的ListView
 */
public class MyElasticListView extends ListView {
    //设置弹性滑动距离
    private int mMaxOverScrollY = 200;
    private Context mContext;
    public MyElasticListView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public MyElasticListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public MyElasticListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyElasticListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
    }

    private void initView() {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        float density = metrics.density;
        mMaxOverScrollY = (int) (density*mMaxOverScrollY);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY,
                                   int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY,
                scrollX, scrollY,
                scrollRangeX, scrollRangeY,
                maxOverScrollX, mMaxOverScrollY, isTouchEvent);
    }
}
