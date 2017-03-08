package com.vv.zvv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.vv.zvv.Adress.MethodGet;
import com.vv.zvv.JavaBean.ProductList;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class XUtilsActivity extends AppCompatActivity {
    MainActivity mMainActivity;
    private static final String TAG = "XUtilsActivity";
    @ViewInject(R.id.iv_xUtilsImage_XUtilsActivity)
    private ImageView iv_xUtilsImage_XUtilsActivity;

    @ViewInject(R.id.btn_xUtilsHttpREquest_XUtilsActivity)
    private Button btn_xUtilsHttpREquest;
    ImageOptions imageOptions = new ImageOptions.Builder()
            .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))//图片大小
//            .setRadius(DensityUtil.dip2px(5))//ImageView圆角半径
            .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            .setLoadingDrawableId(R.mipmap.ic_logo)//加载中默认显示图片
            .setFailureDrawableId(R.mipmap.ic_logo)//加载失败后默认显示图片
            .setCircular(true)//圆形图片
            .build();

    private String ivUrl = "http://www.onegreen.net/maps/Upload_maps/201412/2014122122364306.jpg";

    ProductList mProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xutils);

        mMainActivity = new MainActivity();
        x.view().inject(this);

    }

    @Event(value = {R.id.iv_xUtilsImage_XUtilsActivity, R.id.btn_xUtilsHttpREquest_XUtilsActivity, R.id.btn_modify_XUtilsActivity}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_xUtilsImage_XUtilsActivity:
                loadImageView();
                break;
            case R.id.btn_xUtilsHttpREquest_XUtilsActivity:
                xUtilsHttpRequest();
                break;
            case R.id.btn_modify_XUtilsActivity:
                //使用
                MainActivity.mOnModifyCallBack.Modify("Zvv");
                break;
        }
    }

    //加载图片
    private void loadImageView() {
        x.image().bind(iv_xUtilsImage_XUtilsActivity, ivUrl, imageOptions);
    }

    //请求数据
    private void xUtilsHttpRequest() {
        mProductList = new ProductList();
        Log.d(TAG, "xUtilsHttpRequest: " + new MethodGet().getProductList());
        RequestParams params = new RequestParams(new MethodGet().getProductList());//地址
//        params.setSslSocketFactory(...); // 设置ssl
        params.addBodyParameter("", "");

        /*GET请求*/
//        x.http().get(params, new Callback.CommonCallback<String>() {
//            public void onSuccess(String result) {
//                Log.d(TAG, "onSuccess: 原始: " + result);
//                mProductList = JSON.parseObject(result, ProductList.class);
//                Log.d(TAG, "onSuccess: JSON: " + JSON.toJSONString(mProductList));
//                Log.d(TAG, "onSuccess: 个数: " + mProductList.getPager().getList().size());
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Log.d(TAG, "onError: ");
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                Log.d(TAG, "onCancelled: ");
//            }
//
//            @Override
//            public void onFinished() {
//                Log.d(TAG, "onFinished: ");
//            }
//        });

        /*POST请求*/
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });

        /*通用方式*/
        x.http().request(HttpMethod.GET, params, new Callback.CommonCallback<String>() {
            public void onSuccess(String result) {
                Log.d(TAG, "onSuccess: 原始: " + result);

//              mProductList = JSON.parseObject(result, ProductList.class);//fastJson
                mProductList = new Gson().fromJson(result, ProductList.class);//Gson

                Log.d(TAG, "onSuccess: JSON: " + JSON.toJSONString(mProductList));
                Log.d(TAG, "onSuccess: 个数: " + mProductList.getPager().getList().size());
                Toast.makeText(XUtilsActivity.this, mProductList.getPager().getList().get(1).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d(TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.d(TAG, "onFinished: ");
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageOptions = null;
    }


}
