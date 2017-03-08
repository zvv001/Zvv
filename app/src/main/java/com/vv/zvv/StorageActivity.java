package com.vv.zvv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StorageActivity extends AppCompatActivity {
    @ViewInject(R.id.tv)
    private TextView textView;

    @ViewInject(R.id.et)
    private EditText editText;

    //文件名
    private String fileName = "file_name";

    //缓存文件名(包含随机名)
    private String patch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        x.view().inject(this);
    }

    @Event(value = {R.id.btn_writeInternalStorage, R.id.btn_writeInternalStorageCache,
            R.id.btn_readInternalStorage, R.id.btn_readInternalStorageCache, R.id.btn_deleteInternalStorage,
            R.id.btn_gotoSharedPreference}, type = View.OnClickListener.class)
    private void OnCLick(View view) {
        switch (view.getId()) {
            case R.id.btn_writeInternalStorage:
                writeInternalStorage();
                break;
            case R.id.btn_writeInternalStorageCache:
                writeInternalStorageCache();
                break;
            case R.id.btn_readInternalStorage:
                readInternalStorage();
                break;
            case R.id.btn_readInternalStorageCache:
                readInternalStorageCache();
                break;
            case R.id.btn_deleteInternalStorage:
                deleteInternalStorage();
                break;
            case R.id.btn_gotoSharedPreference:
                startActivity(new Intent(this, SharedPreferenceActivity.class));
                break;
        }
    }


    //在内部存储中写入文件
    public void writeInternalStorage() {
        FileOutputStream fos = null;
        //创建内部存储的文件输出流对象
        try {
            fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            //向指定的内部存储中的文件写入数据
            fos.write(editText.getText().toString().getBytes());
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //在内部存储中读取文件
    public void readInternalStorage() {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        //获取内部存储中文件的输入流
        try {
            FileInputStream fis = openFileInput(fileName);
            br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            textView.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //在内部存储中写入缓存文件
    public void writeInternalStorageCache() {
        FileOutputStream fos = null;
        //指定缓存文件
        //prefix:前缀,文件名
        //suffix:后缀,文件类型.null使用默认.tmp
        //directory:文件存储的路径
        try {
            File tempFile = File.createTempFile(fileName, null, getCacheDir());
            //获取缓存文件路径(包含随机数)
            patch = tempFile.getPath();
            fos = new FileOutputStream(tempFile);
            fos.write(editText.getText().toString().getBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //在内部存储中读取缓存文件
    public void readInternalStorageCache() {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        //获取目标的缓存文件
        File tempfile = new File(patch);
        try {
            FileInputStream fis = new FileInputStream(tempfile);
            br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            textView.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //删除内部存储的文件
    public void deleteInternalStorage() {
        boolean isDelete = deleteFile(fileName);
        Toast.makeText(this, "是否删除成功:\t" + isDelete, Toast.LENGTH_LONG).show();
    }
}
