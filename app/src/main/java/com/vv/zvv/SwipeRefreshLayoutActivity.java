package com.vv.zvv;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.vv.zvv.Adress.FinalAddress;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * From the Google Material Design documentation
 * <p/>
 * http://www.materialdoc.cn/swipe-to-refresh/
 * <p/>
 * Swipe to refresh is a swipe gesture available at the beginning of lists, grid lists, and card collections where the most recent content appears.
 */
public class SwipeRefreshLayoutActivity extends AppCompatActivity {
    private static final String TAG = "SwipeRefreshLayoutActivity";
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @ViewInject(R.id.iv)
    private ImageView mImageView;

    ImageOptions imageOptions = new ImageOptions.Builder()
            .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))//图片大小
//            .setRadius(DensityUtil.dip2px(5))//ImageView圆角半径
            .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            .setLoadingDrawableId(R.mipmap.ic_logo)//加载中默认显示图片
            .setFailureDrawableId(R.mipmap.ic_logo)//加载失败后默认显示图片
            .setCircular(false)//圆形图片
            .build();

    private String ivUrl = new FinalAddress().getPICTUREURL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);

        init();
    }

    private void init() {
        //Assign the colors to the view with the setColorSchemeResources method.
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.SwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.z70a9ff, R.color.ffd300, R.color.f24a3a);
        initListener();

    }

    @SuppressLint("LongLogTag")
    private void initListener() {
        Log.d(TAG, "initListener: " + ivUrl);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                //加载数据
                x.image().bind(mImageView, ivUrl, imageOptions);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }


}
