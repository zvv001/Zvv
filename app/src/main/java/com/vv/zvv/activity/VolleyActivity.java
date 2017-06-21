package com.vv.zvv.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vv.zvv.R;

import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * Volley的学习界面
 */
public class VolleyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        x.view().inject(this);
    }

    @Event(value = {R.id.btn_requestData}, type = View.OnClickListener.class)
    private void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.btn_requestData:
                    // TODO: 2017/5/10 请求数据
                    break;
            }
        }
    }
}
