package com.vv.zvv;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskActivity extends AppCompatActivity {
    private static final String TAG = "AsyncTaskActivity";
    // 网络图片地址
    private final String IMG_URL = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
    // 进度条对话框
    private ProgressDialog progressDialog;



    @ViewInject(R.id.btn_loadImage_AsyncTaskActivity)
    private Button btn_loadImage;

    @ViewInject(R.id.iv)
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        // 初始化进度条对话框
        initProgressDialog();
        x.view().inject(this);
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("下载");
        progressDialog.setMessage("正在下载图片");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    @Event(value = {R.id.btn_loadImage_AsyncTaskActivity}, type = View.OnClickListener.class)
    private void Onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_loadImage_AsyncTaskActivity:
                // 创建异步任务工具，并执行下载的操作
//                new ImageTask().execute(IMG_URL);

                loadImage();
                break;
        }
    }

    // 异步任务工具类，用于下载网络数据
    class ImageTask extends AsyncTask<String, Integer, byte[]> {

        // 下载前的准备操作，初始化UI控件
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected byte[] doInBackground(String... params) {
            byte[] data = null;
            // 请求访问的url地址
            String requestUrl = params[0];
            try {
                URL url = new URL(requestUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                if (conn.getResponseCode() == 200) {
                    InputStream is = conn.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    // 获取网络数据的总长度
                    int file_length = conn.getContentLength();
                    // 当前获取到的数据大小
                    int cur_length = 0;
                    int len = 0;
                    byte[] buffer = new byte[1024];
                    while ((len = is.read(buffer)) != -1) {
                        baos.write(buffer, 0, len);
                        cur_length += len;
                        // 加载进度百分比
                        int progress = (int) (cur_length / (float) file_length * 100);
                        // 发布当前进度
                        publishProgress(progress);
                        baos.flush();

                        // 模拟网络环境不好
                        Thread.sleep(500);
                    }
                    data = baos.toByteArray();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        }

        // 向主线程发送子线程中获取到的数据
        @Override
        protected void onPostExecute(byte[] result) {
            super.onPostExecute(result);
            // 创建位图对象
            Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
            // 将位图对象设置给ImageView控件
            mImageView.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // 接受到每次while中发布的进度
            int progress = values[0];
            // 设置进度
            progressDialog.setProgress(progress);

            if (progress == 100) {
                // 对话框消失
                progressDialog.dismiss();
            }
        }
    }

    //xUtils下载图片
    private void loadImage() {
        ImageOptions imageOptions = new ImageOptions.Builder()
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                // 默认自动适应大小
                // .setSize(...)
                .setIgnoreGif(false)
                // 如果使用本地文件url, 添加这个设置可以在本地文件更新后刷新立即生效.
                //.setUseMemCache(false)
                //设置图片为圆形
                //.setCircular(true)
                //设置圆角半径
                //.setRadius(50)
                //设置图片样式
                .setImageScaleType(ImageView.ScaleType.FIT_XY).build();
        x.image().bind(mImageView, IMG_URL, imageOptions);
    }
}

























