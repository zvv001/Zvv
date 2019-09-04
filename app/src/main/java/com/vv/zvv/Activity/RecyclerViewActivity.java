package com.vv.zvv.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.vv.zvv.Adapters.MyRecycleViewAdapter;
import com.vv.zvv.Adress.MethodGet;
import com.vv.zvv.JavaBean.ProductList;
import com.vv.zvv.JavaBean.Repo;
import com.vv.zvv.R;
import com.vv.zvv.Views.DividerGridViewItemDecoration;


import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/* RecycleView 的使用*/
public class RecyclerViewActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerViewActivity";
    RecyclerView mRecyclerView;
    ProductList mProductList;

    ArrayList<ProductList.PagerBean.ListBean> mList;
    MyRecycleViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initView();
        initData();

        initAdapter();

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
    }

    private void initAdapter() {
        // LinearLayoutManager manager = new LinearLayoutManager(this);
        // manager.setOrientation(LinearLayoutManager.VERTICAL);
        // mRecyclerView.setLayoutManager(manager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new MyRecycleViewAdapter(this, mList);

        // 设置增加/删除 Item 的动画，设置默认动画
        // TODO: 2019/3/5
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //  添加自定义的分割线
        mRecyclerView.addItemDecoration(new DividerGridViewItemDecoration(this));

        //初始化适配器
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mList = new ArrayList<>();
        xUtilsHttpRequest();
    }


    private void testRetrofitHttpGet() {
        // step1
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())//增加返回值为Gson的支持(以实体类返回)
                .build();
        // step2
        RetrofitActivity.DemoService service = retrofit.create(RetrofitActivity.DemoService.class);
        // step3
        Call<List<Repo>> call = service.testHttpGet("zvv001");
        // step4
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                //raw:Response{protocol=http/1.1, code=200, message=OK, url=https://api.github.com/users/basil2style}
                Log.d("ww", "===>>> raw:" + response.raw());
                //返回的数据 response.body()
                Toast.makeText(RecyclerViewActivity.this, response.body().get(0).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d("ww", "请求失败" + t.getMessage());
            }
        });
    }


    //请求数据
    private void xUtilsHttpRequest() {
        mProductList = new ProductList();
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
        x.http().request(HttpMethod.GET, params, new org.xutils.common.Callback.CommonCallback<String>() {
            public void onSuccess(String result) {
                mProductList = new Gson().fromJson(result, ProductList.class);//Gson
                Log.d(TAG, "onSuccess: JSON: " + JSON.toJSONString(mProductList));
                Log.d(TAG, "onSuccess: 个数: " + mProductList.getPager().getList().size());
                mList.addAll(mProductList.getPager().getList());

                mAdapter.upDate(mList);
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

}
