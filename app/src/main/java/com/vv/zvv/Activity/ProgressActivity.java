package com.vv.zvv.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.vv.zvv.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/*ProgressBar*/
public class ProgressActivity extends AppCompatActivity {
    @ViewInject(R.id.pb_circle)
    private ProgressBar mProgressBarCircle;

    @ViewInject(R.id.pb_horizontal)
    private ProgressBar mProgressBarHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        x.view().inject(this);
    }

    @Event(value = {R.id.btn_show, R.id.btn_gone, R.id.btn_addPregress}, type = View.OnClickListener.class)
    private void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show:
                if (mProgressBarCircle.getVisibility() == View.GONE) {
                    mProgressBarCircle.setVisibility(View.VISIBLE);
                }

                if (mProgressBarHorizontal.getVisibility() == View.GONE) {
                    mProgressBarHorizontal.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_gone:
                if (mProgressBarCircle.getVisibility() != View.GONE) {
                    mProgressBarCircle.setVisibility(View.GONE);
                }

                if (mProgressBarHorizontal.getVisibility() != View.GONE) {
                    mProgressBarHorizontal.setVisibility(View.GONE);
                    mProgressBarHorizontal.setProgress(0);
                }
                break;
            case R.id.btn_addPregress:
                int progress = mProgressBarHorizontal.getProgress();
                if (progress == 100) {
                    progress = 0;
                }
                mProgressBarHorizontal.setProgress(progress + 10);
                break;
        }
    }
}
