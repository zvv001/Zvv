package com.vv.zvv;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vv.zvv.JavaBean.Repo;

import org.xutils.view.annotation.Event;
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
    private int messageShowCount = 0;
    private int gcCount = 5;

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
                ToastUtil3.showToast(RetrofitActivity.this, "请求数据！");
                //请求数据
                testRetrofitHttpGet();
                break;
            case R.id.btn_snackbar://Snackbar
                /* 第一个参数需要传入一个View，可以是界面当中的任意一个View控件，Snackbar会自动根据这个控件找到最外层的布局来显示
                 * 第二个参数就是我们需要显示的内容，注意这里的内容最多显示两行哦，超出两行后的内容会变成“…”
                 * 第三个参数为Snackbar显示的时长，有三种模式供选择
                 * LENGTH_SHORT：短时间显示
                 * LENGTH_LONG：长时间显示
                 *  LENGTH_INDEFINITE：一直显示，只有当用户触发Action点击事件或手动删除时才会消失
                 * Snackbar可以通过setAction方法设置一个点击事件，和用户进行交互
                 */
//                final Snackbar snackbar = Snackbar.make(findViewById(R.id.btn_snackbar), "这是massage", Snackbar.LENGTH_SHORT);//LENGTH_SHORT: 瞬间展示
                final Snackbar snackbar = Snackbar.make(findViewById(R.id.container), "这是massage", Snackbar.LENGTH_INDEFINITE);//LENGTH_INDEFINITE: 永久展示
                snackbar.setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.setActionTextColor(Color.GREEN);
                snackbar.show();

//                /**
//                 * view: snackbar的容器或者界面中任意一个View控件（Snackbar根据控件自动找到最外层的布局来显示）
//                 * text: 展示的内容
//                 * duration： 展示的时间 LENGTH_SHORT：短时间 LENGTH_LONG: 长时间 LENGTH_INDEFINITE：永久展示
//                 final Snackbar snackbar1 = Snackbar.make(findViewById(R.id.container), "Message!", Snackbar.LENGTH_SHORT);
//                 snackbar1.setText("Snackbar's Message!");
//                 snackbar1.setAction("确定", new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                snackbar1.dismiss();
//                }
//                });
//
//                 snackbar1.setCallback(new Snackbar.Callback() {
//                @Override public void onDismissed(Snackbar snackbar, int event) {
//                super.onDismissed(snackbar, event);
//                if (event == DISMISS_EVENT_SWIPE) {//滑动消失的回调
//                //                            Toast.makeText(RetrofitActivity.this, "滑动消失！", Toast.LENGTH_SHORT).show();
//                } else if (event == DISMISS_EVENT_ACTION) {//点击Action按钮的回调
//                //                            Toast.makeText(RetrofitActivity.this, "点击按钮消失！", Toast.LENGTH_SHORT).show();
//                } else if (event == DISMISS_EVENT_CONSECUTIVE) {//新的Snackbar出现时回调
//                //                            Toast.makeText(RetrofitActivity.this, "新Snackbar出现", Toast.LENGTH_SHORT).show();
//                } else if (event == DISMISS_EVENT_MANUAL) {//手动调用dismiss()方法时 的回调
//                //                            Toast.makeText(RetrofitActivity.this, "dismiss()", Toast.LENGTH_SHORT).show();
//                } else if (event == DISMISS_EVENT_TIMEOUT) {//超时的回调
//                //                            Toast.makeText(RetrofitActivity.this, "超时", Toast.LENGTH_SHORT).show();
//                }
//                }
//
//                @Override public void onShown(Snackbar snackbar) {
//                super.onShown(snackbar);
//                //                        Toast.makeText(RetrofitActivity.this, "Show的回调！", Toast.LENGTH_SHORT).show();
//                }
//                });
//                 setSnackbarColor(snackbar1, Color.BLUE, Color.GRAY);
//                 addViewInSnackbar(snackbar1,R.layout.headview_logo,0);
//                 addViewInSnackbar(snackbar1,R.layout.headview_logo,2);
//                 snackbar1.show();*/

                count();
                break;
            case R.id.btn_btn_toastUtil:
//                ToastUtils.showMessage(RetrofitActivity.this, "点击了！");
//                ToastUtil_01 t = new ToastUtil_01();
                ToastUtil3.showToast(RetrofitActivity.this, "没有更多了！");
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
//                Toast.makeText(RetrofitActivity.this, response.body().get(0).getName(), Toast.LENGTH_SHORT).show();
                ToastUtil3.showToast(RetrofitActivity.this, response.body().get(0).getName());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d("ww", "请求失败" + t.getMessage());
            }
        });
    }

    //每点一次，就会有0.01MB的内存增长,当点击次数超过5次，调用垃圾回收
    private void count() {
        messageShowCount++;
        if (messageShowCount >= gcCount) {
            System.gc();
            messageShowCount = 0;
        }
    }

    //自定义设置Snackbar的字体颜色和背景颜色
    public static void setSnackbarColor(Snackbar snackbar, int messageColor, int backgroundColor) {
        View view = snackbar.getView();//获取Snackbar的view
        if (view != null) {
            view.setBackgroundColor(backgroundColor);//修改view的背景色
            ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(messageColor);//获取Snackbar的message控件，修改字体颜色
        }
    }

    //向Snackbar中添加一个View
    public static void addViewInSnackbar(Snackbar snackbar, int layoutId, int index) {
        View snackbarview = snackbar.getView();//获取snackbar的View(其实就是SnackbarLayout)

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarview;//将获取的View转换成SnackbarLayout

        View add_view = LayoutInflater.from(snackbarview.getContext()).inflate(layoutId, null);//加载布局文件新建View

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);//设置新建布局参数

        p.gravity = Gravity.CENTER_VERTICAL;//设置新建布局在Snackbar内垂直居中显示

        snackbarLayout.addView(add_view, index, p);//将新建布局添加进snackbarLayout相应位置
    }

    public static class ToastUtil3 {

        private static String oldMsg;
        protected static Toast toast = null;
        private static long oneTime = 0;
        private static long twoTime = 0;

        public static void showToast(Context context, String s) {
            if (toast == null) {
                toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
                toast.show();
                oneTime = System.currentTimeMillis();
            } else {
                twoTime = System.currentTimeMillis();
                if (s.equals(oldMsg)) {
                    if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                        toast.show();
                    }
                } else {
                    oldMsg = s;
                    toast.setText(s);
                    toast.show();
                }
            }
            oneTime = twoTime;
        }


        public static void showToast(Context context, int resId) {
            showToast(context, context.getString(resId));
        }

    }
}
