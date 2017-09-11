package com.vv.zvv.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vv.zvv.Adapters.MyPagerAdapter;
import com.vv.zvv.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 卡片式的滑动效果
 * autour: zvv
 * date: 2017/9/7 16:13
 * update: 2017/9/7
 * version:
 */
public class CardViewPagerActivity extends AppCompatActivity {
    @ViewInject(R.id.viewPager)
    private ViewPager mViewPager;

    View page0;
    View page1;
    View page2;

    List<View> mViewList;
    MyPagerAdapter mMyPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_pager);

        x.view().inject(this);

        initView();
    }

    private void initView() {
        mViewPager.setPageMargin(2);// 设置 item之间的举例
        mViewPager.setOffscreenPageLimit(3);//默认预加载为1 这里设置成3
        mViewPager.setPageTransformer(false, new ScaleTransformer());


        LayoutInflater layoutInflater = LayoutInflater.from(this);
        page0 = layoutInflater.inflate(R.layout.item_pager, null);
        TextView view0 = (TextView) page0.findViewById(R.id.tv_title);
        view0.setText("Page0");
        ImageView imageView0 = (ImageView) page0.findViewById(R.id.iv_card);
        imageView0.setImageResource(R.drawable.page0);

        page1 = layoutInflater.inflate(R.layout.item_pager, null);
        TextView view1 = (TextView) page1.findViewById(R.id.tv_title);
        view1.setText("Page1");
        ImageView imageView1 = (ImageView) page1.findViewById(R.id.iv_card);
        imageView1.setImageResource(R.drawable.page1);

        page2 = layoutInflater.inflate(R.layout.item_pager, null);
        TextView view2 = (TextView) page2.findViewById(R.id.tv_title);
        view2.setText("Page2");
        ImageView imageView2 = (ImageView) page2.findViewById(R.id.iv_card);
        imageView2.setImageResource(R.drawable.page2);

        mViewList = new ArrayList<>();
        mViewList.add(page0);
        mViewList.add(page1);
        mViewList.add(page2);

        mMyPagerAdapter = new MyPagerAdapter(mViewList);
        mViewPager.setAdapter(mMyPagerAdapter);

    }

    public class ScaleTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.80f;//0.70f 与下文代码中
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View page, float position) {
            if (position < -1 || position > 1) {
                page.setAlpha(MIN_ALPHA);
                page.setScaleX(MIN_SCALE);
                page.setScaleY(MIN_SCALE);
            } else if (position <= 1) { // [-1,1]
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                if (position < 0) {
                    float scaleX = 1 + 0.2f * position;
                    Log.d("google_lenve_fb", "transformPage: scaleX:" + scaleX);
                    page.setScaleX(scaleX);
                    page.setScaleY(scaleX);
                } else {
                    float scaleX = 1 - 0.2f * position;
                    page.setScaleX(scaleX);
                    page.setScaleY(scaleX);
                }
                page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            }
        }
    }
}


























