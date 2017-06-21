package com.vv.zvv.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vv.zvv.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class SharedPreferenceActivity extends AppCompatActivity {
    @ViewInject(R.id.ed_inputText)
    private EditText mInputEditText;

    @ViewInject(R.id.ed_showText)
    private EditText mShowEditText;

    @ViewInject(R.id.btn_write)
    private Button mWriteButton;

    @ViewInject(R.id.btn_red)
    private Button mRedButton;

    private SharedPreferences mSharedPreferences;
    private String FileName = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        x.view().inject(this);

        initSharedPreferenceActivity();
    }

    private void initSharedPreferenceActivity() {
        mSharedPreferences = getSharedPreferences(FileName, Context.MODE_PRIVATE);

    }

    @Event(value = {R.id.btn_write, R.id.btn_red}, type = View.OnClickListener.class)
    private void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_write:
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                String inputString = mInputEditText.getText().toString();
                if (inputString.isEmpty() || inputString.equals("")) {
                    Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString("text", inputString);
                    editor.commit();
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_red:
                SharedPreferences sharedPreferences = getSharedPreferences(FileName, Context.MODE_PRIVATE);
                String text = sharedPreferences.getString("text", "获取内容失败");
                mShowEditText.setText(text);
                break;
        }
    }
}
