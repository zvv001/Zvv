package com.vv.zvv;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.vv.zvv.Utils.ToastUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.FileNotFoundException;

/**
 * 获取本地图片并展示
 */
public class ShowLocalPictureActivity extends AppCompatActivity {
    private final static int IMAGE_CODE = 1;
    private static final String TAG = "ShowLocalPictureActivity";

    @ViewInject(R.id.image)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_local_picture);

        x.view().inject(this);
    }


    @Event(value = {R.id.btn_getPicture}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_getPicture:
                getPicture(IMAGE_CODE);
                ToastUtil.showShortToast(ShowLocalPictureActivity.this, "CLick!");
                break;
            default:
                break;
        }
    }

    //获取本地图片
    private void getPicture(int code) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);//允许用户选择特殊种类的数据，并返回（特殊种类的数据：照一张相片或录一段音）
        intent.setType("image/*");
        startActivityForResult(intent, code);
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String img_url = uri.getPath();//这是本机的图片路径
            Log.d(TAG, "onActivityResult: " + img_url);
            ContentResolver contentResolver = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri));
                /* 将Bitmap设定到ImageView */
                mImageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        } else {
            return;
        }
    }
}





























