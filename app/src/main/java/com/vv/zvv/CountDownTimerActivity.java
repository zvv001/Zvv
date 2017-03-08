package com.vv.zvv;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * CountDownTimer使用
 */
public class CountDownTimerActivity extends AppCompatActivity {
    CountDownTimer mCountDownTimer;
    TextView mTextView;
    private String TAG = "CountDownTimerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);
        init();
    }

    private void init() {
        mTextView = (TextView) findViewById(R.id.tv_showTime);
        /**
         * 需要重写onTick(),和onFnish()
         *
         * 第一个参数表示总时间_onTick()
         * 第二个参数表示间隔时间_onFnish()
         *
         * 意思就是每隔一秒会回调一次方法onTick，然后10秒之后会回调onFinish方法。
         */
        mCountDownTimer = new CountDownTimer(10 * 1000l, 1 * 1000l) {

            @Override
            public void onTick(long millisUntilFinished) {
                long remainingTime = millisUntilFinished / 1000;
                mTextView.setText(remainingTime + "s");
                Log.d(TAG, "onTick: " + remainingTime);
            }

            @Override
            public void onFinish() {
                mTextView.setText("倒计时结束");
            }
        };
    }

    //点击事件的
    public void OnClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.btn_start:
                    mTextView.setText("10s倒计时开始");
                    mCountDownTimer.start();
                    break;
                case R.id.btn_cancel:
                    mCountDownTimer.cancel();
                    break;
                default:
                    break;
            }
        }
    }
}
