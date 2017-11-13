package com.vv.zvv.Activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.vv.zvv.Adress.FinalAddress;
import com.vv.zvv.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskActivity extends AppCompatActivity {
    private static final String TAG = "AsyncTaskActivity";
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
                new ImageTask().execute(new FinalAddress().getIMG_URL());
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
                disableConnectionReuse();//HttpURLConnection 在Android 2.2版本之前的一个bug
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

    /**
     * Android 2.2版本之前，HttpURLConnection一直存在着一些令人厌烦的bug。
     * 比如说对一个可读的InputStream调用close()方法时，就有可能会导致连接池失效了。那么我们通常的解决办法就是直接禁用掉连接池的功能：
     */
    private void disableConnectionReuse() {
        // 这是一个2.2版本之前的bug
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }
}

























