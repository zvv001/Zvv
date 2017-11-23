package com.vv.zvv.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vv.zvv.R;
import com.vv.zvv.Utils.DrawUtil;
import com.vv.zvv.Views.DecimalScaleRulerView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * description: 小数点横向滚动器
 * author: zvv
 * date: 2017/11/14 9:52
 * update: 2017/11/14
 * version:
 */
public class DecimalScaleRulerViewActivity extends AppCompatActivity {

    @ViewInject(R.id.ruler_weight)
    DecimalScaleRulerView mWeightRulerView;

    @ViewInject(R.id.tv_user_weight_value_two)
    TextView mWeightValueTwo;

    private float mWeight = 0.2f;
    private float mMaxWeight = 200;
    private float mMinWeight = 25;


    @ViewInject(R.id.progressBar)
    private ProgressBar progressBar;
    @ViewInject(R.id.tv_progressPercent)
    private TextView tv_progressPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decimal_scale_ruler_view);

        x.view().inject(this);

        init();
    }

    private void init() {
        /*最大进度值200*/
        progressBar.setMax(20);

        mWeightValueTwo.setText(mWeight + "元");

        // setParam(int itemSpacing, int maxLineHeight, int middleLineHeight, int minLineHeight, int textMarginTop, int textSize) {
        mWeightRulerView.setParam(DrawUtil.dip2px(8), DrawUtil.dip2px(32), DrawUtil.dip2px(24),
                DrawUtil.dip2px(14), DrawUtil.dip2px(16), DrawUtil.dip2px(12));

        mWeightRulerView.initViewParam(mWeight, 0.0f, 20.0f, 1);

        mWeightRulerView.setValueChangeListener(new DecimalScaleRulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                DecimalFormat df = new DecimalFormat("##0");
                String showValue =  df.format(value*10000)+"元";
                mWeightValueTwo.setText(showValue);


                mWeight = value;

                int progress = ((int) value);
                double progressPercent = value / 20;
                String showPercent = getCeil(progressPercent) + "%";

                progressBar.setProgress(progress);
                tv_progressPercent.setText(showPercent);//展示百分比
            }
        });
    }

    private String getCeil(double d) {
        DecimalFormat df = new DecimalFormat("##0.0");
        df.setRoundingMode(RoundingMode.UP);
        return df.format(d);
    }
}
