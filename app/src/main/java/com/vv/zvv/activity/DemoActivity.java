package com.vv.zvv.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vv.zvv.R;
import com.vv.zvv.Utils.view.LoopViewMaizi.LoopView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 临时 Demo
 */
public class DemoActivity extends AppCompatActivity {
    @ViewInject(R.id.loopView)
    private LoopView loopView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        x.view().inject(this);

        init();
    }

    private void init() {
        loopView.setAutoRotation(true);
        loopView.setAutoRotationTime(3000);
    }
}
