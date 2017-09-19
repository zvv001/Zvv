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

    @ViewInject(R.id.tv_title)
    private TextView mTextView;//title

    View page0;
    View page1;
    View page2;
    View page3;

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
        mViewPager.setOffscreenPageLimit(3);//默认预加载为1 这里设置成3
        mViewPager.setPageMargin(2);// 设置ViewPager中两页之间的距离

        mViewPager.setPageTransformer(false, new ScaleTransformer());//


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

        page3 = layoutInflater.inflate(R.layout.item_pager, null);
        TextView view3 = (TextView) page3.findViewById(R.id.tv_title);
        view3.setText("Page3");
        ImageView imageView3 = (ImageView) page3.findViewById(R.id.iv_card);
        imageView3.setImageResource(R.drawable.page0);

        mViewList = new ArrayList<>();
        mViewList.add(page0);
        mViewList.add(page1);
        mViewList.add(page2);
        mViewList.add(page3);

        mMyPagerAdapter = new MyPagerAdapter(mViewList);
        mViewPager.setAdapter(mMyPagerAdapter);

        //ViewPager 滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mTextView.setText("注册会员");
                        break;
                    case 1:
                        mTextView.setText("银麦卡");
                        break;
                    case 2:
                        mTextView.setText("金麦卡");
                        break;
                    case 3:
                        mTextView.setText("铂金麦卡");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public class ScaleTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.80f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View page, float position) {
            Log.d("ds", "transformPage: " + position);

//            final float normalizedposition = Math.abs(Math.abs(position) - 1);
//            page.setAlpha(normalizedposition);

            //模糊效果
            if (position < 0 || position > 1) {
                page.setAlpha(MIN_ALPHA);
            } else { // [0,1]
                page.setAlpha(1.0f);
            }


            // 缩放效果
            if (position < -1 || position > 1) {
//                page.setAlpha(MIN_ALPHA);
                page.setScaleX(MIN_SCALE);
                page.setScaleY(MIN_SCALE);
            } else if (position <= 1) { // [-1,1]
//                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
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
//                page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            }
        }
    }
}


























