package com.vv.zvv;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.vv.zvv.Adapters.MyViewPageAdapter;
import com.vv.zvv.Interface.ScrollViewListener;
import com.vv.zvv.Utils.MyScrollView;

import java.util.ArrayList;
import java.util.List;

//public class ScrollViewActivity extends AppCompatActivity implements View.OnTouchListener {
public class ScrollViewActivity extends AppCompatActivity {
    MyScrollView mMyScrollView;
    ViewPager mViewPage;
    View page01, page02, page03, page04;
    List<View> mViewList;

    Button btn_product, btn_safe, btn_supportBank, btn_files;
    int lastX = 0;
    int lastY = 0;

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
        page04 = mInflater.inflate(R.layout.viewpage_page_04, null);

        mViewList = new ArrayList<>();
        mViewList.add(page01);
        mViewList.add(page02);
        mViewList.add(page03);
        mViewList.add(page04);

        initAdapter();
    }

    private void initAdapter() {
        MyViewPageAdapter myViewPageAdapter = new MyViewPageAdapter(mViewList);
        mViewPage.setAdapter(myViewPageAdapter);
    }

    private void initView() {
        btn_product = (Button) findViewById(R.id.btn_product);
        btn_safe = (Button) findViewById(R.id.btn_safe);
        btn_supportBank = (Button) findViewById(R.id.btn_supportBank);
        btn_files = (Button) findViewById(R.id.btn_files);
//        mMyScrollView = new MyScrollView(this);
        mMyScrollView = (MyScrollView) findViewById(R.id.msv);

        mMyScrollView.setOnScrollChangeListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, final int oldy) {
//             mMyScrollView.scrollTo(0,1000);

            }
        });
        mViewPage = (ViewPager) findViewById(R.id.viewPage);
        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initButton(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initButton(0);
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

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_product:
                mViewPage.setCurrentItem(0);
                initButton(0);
                break;
            case R.id.btn_safe:
                mViewPage.setCurrentItem(1);
                initButton(1);
                break;
            case R.id.btn_supportBank:
                mViewPage.setCurrentItem(2);
                initButton(2);
                break;
            case R.id.btn_files:
                mViewPage.setCurrentItem(3);
                initButton(3);
                break;
        }
    }


    public void initButton(int btn) {
        int btnOffColor = getResources().getColor(R.color.z000000);
        int btnOnColor = getResources().getColor(R.color.colorAccent);
        btn_product.setTextColor(btnOffColor);
        btn_safe.setTextColor(btnOffColor);
        btn_supportBank.setTextColor(btnOffColor);
        btn_files.setTextColor(btnOffColor);

        switch (btn) {
            case 0:
                btn_product.setTextColor(btnOnColor);
                break;
            case 1:
                btn_safe.setTextColor(btnOnColor);
                break;
            case 2:
                btn_supportBank.setTextColor(btnOnColor);
                break;
            case 3:
                btn_files.setTextColor(btnOnColor);
                break;
        }
    }
}
