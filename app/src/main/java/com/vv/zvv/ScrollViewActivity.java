package com.vv.zvv;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.vv.zvv.Adapters.MyViewPageAdapter;
import com.vv.zvv.Interface.ScrollViewListener;
import com.vv.zvv.Utils.MyScrollView;

import java.util.ArrayList;
import java.util.List;

public class ScrollViewActivity extends AppCompatActivity implements View.OnTouchListener {
    MyScrollView mMyScrollView;
    ViewPager mViewPage;
    View page01, page02, page03;
    List<View> mViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        initView();
        initPage();
    }

    private void initPage() {
        LayoutInflater mInflater = getLayoutInflater();
        page01 = mInflater.inflate(R.layout.viewpage_page_01, null);
        page02 = mInflater.inflate(R.layout.viewpage_page_02, null);
        page03 = mInflater.inflate(R.layout.viewpage_page_03, null);

        mViewList = new ArrayList<>();
        mViewList.add(page01);
        mViewList.add(page02);
        mViewList.add(page03);

        initAdapter();
    }

    private void initAdapter() {
        MyViewPageAdapter myViewPageAdapter = new MyViewPageAdapter(mViewList);
        mViewPage.setAdapter(myViewPageAdapter);
    }

    private void initView() {
        mMyScrollView = (MyScrollView) findViewById(R.id.msv);

        mMyScrollView.setOnScrollChangeListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
//             mMyScrollView.scrollTo(0,1000);


            }
        });

        mViewPage = (ViewPager) findViewById(R.id.viewPage);
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(float pxValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕尺寸
     *
     * @return
     */
    public int getScreenHeight() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）

        return height;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_product:

                break;
            case R.id.btn_safe:

                break;
            case R.id.btn_supportBank:

                break;
            case R.id.btn_files:

                break;
        }
    }
}
