package com.vv.zvv.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vv.zvv.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 屏幕适配页面
 */
public class ScreenActivity extends AppCompatActivity {
    @ViewInject(R.id.vs_textView)
    private ViewStub vs_TextView;

    @ViewInject(R.id.simpleDrawView)
    private SimpleDraweeView simpleDrawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        x.view().inject(this);

        findViewById(R.id.top_title).findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Event(value = {R.id.simpleDrawView}, type = View.OnClickListener.class)
    private void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.simpleDrawView:
                    try {// ViewStub must have a non-null ViewGroup viewParent
                        vs_TextView.inflate();
                        simpleDrawView.setActualImageResource(R.drawable.guide1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
//                case R.id.btn_back:  //失效（<include>）
//                    finish();
//                    break;
                default:
                    break;
            }
        }
    }

}
