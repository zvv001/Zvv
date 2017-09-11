package com.vv.zvv.activity;

import android.app.Activity;
import android.os.Bundle;

import com.vv.zvv.R;

import org.xutils.x;

/**
 * 临时 Demo
 */
public class DemoActivity extends Activity {
//    @ViewInject(R.id.loopView)
//    private LoopView loopView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        x.view().inject(this);

        init();
    }

    private void init() {
//        loopView.setAutoRotation(true);
//        loopView.setAutoRotationTime(3000);
    }
}
