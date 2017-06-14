package com.vv.zvv;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.vv.zvv.JavaBean.Repo;
import com.vv.zvv.Utils.ToastUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RetrofitActivity extends AppCompatActivity {
    @ViewInject(R.id.container)
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        x.view().inject(this);
    }

    @Event(value = {R.id.btn_requestData, R.id.btn_snackbar, R.id.btn_btn_toastUtil}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_requestData://请求数据
                Log.d("vv", "点击按钮");
                //请求数据
                testRetrofitHttpGet();
            case R.id.btn_snackbar:
//                Snackbar.make(getWindow().getDecorView(), "这是massage", Snackbar.LENGTH_LONG).setAction("这是action", new View.OnClickListener() {
                /**/
                Snackbar.make(mCoordinatorLayout, "这是massage", Snackbar.LENGTH_LONG).setAction("这是action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(RetrofitActivity.this, "你点击了action", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.btn_btn_toastUtil:
                ToastUtils.showMessage(RetrofitActivity.this,"点击了！");
                break;
            default:
                break;
        }
    }

    //http://www.maizjr.com/api/v2/products/more/4.json
    //https://api.github.com/users/basil2style
    //https://api.github.com/users/zvv001/repos
    public interface DemoService {
        //        @GET("users/basil2style")
//        Call<Repo> testHttpGet();
        @GET("users/{user}/repos")
        Call<List<Repo>> testHttpGet(@Path("user") String user);
    }

    private void testRetrofitHttpGet() {
        // step1
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())//增加返回值为Gson的支持(以实体类返回)
                .build();
        // step2
        DemoService service = retrofit.create(DemoService.class);
        // step3
        Call<List<Repo>> call = service.testHttpGet("zvv001");
        // step4
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                //raw:Response{protocol=http/1.1, code=200, message=OK, url=https://api.github.com/users/basil2style}
                Log.d("ww", "===>>> raw:" + response.raw());
                //返回的数据 response.body()
                Toast.makeText(RetrofitActivity.this, response.body().get(0).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d("ww", "请求失败" + t.getMessage());
            }
        });
    }
}
