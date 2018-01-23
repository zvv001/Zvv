package com.vv.zvv.Activity;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vv.zvv.R;
import com.vv.zvv.Utils.Base64Util;
import com.vv.zvv.Utils.DrawUtil;
import com.vv.zvv.Views.DecimalScaleRulerView;

import org.xutils.view.annotation.Event;
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

    @ViewInject(R.id.tv_showNumber)
    private TextView tv_showNumber;

    @ViewInject(R.id.iv_base64)
    private ImageView iv_base64;

    ValueAnimator valueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decimal_scale_ruler_view);

        x.view().inject(this);

        init();
        initAnimation();
    }

    @Event(value = {R.id.tv_showNumber}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_showNumber:
                valueAnimator.start();
                break;

        }
    }

    private void initAnimation() {
        valueAnimator = ValueAnimator.ofFloat(0, 1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();

                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                String formatStr = decimalFormat.format(animatedValue);
                final float show = Float.parseFloat(formatStr);

//                tv_showNumber.setText("$" + animatedValue);
                tv_showNumber.setText("$" + show);
            }
        });
        valueAnimator.setDuration(6000);

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
                String showValue = df.format(value * 10000) + "元";
                mWeightValueTwo.setText(showValue);


                mWeight = value;

                int progress = ((int) value);
                double progressPercent = value / 20;
                String showPercent = getCeil(progressPercent) + "%";

                progressBar.setProgress(progress);
                tv_progressPercent.setText(showPercent);//展示百分比
            }
        });

        Log.d("vv", "init: \n"
                + "getCeil(10.12 000000000): " + getCeil(10.120000000001) + "\n"
                + "getCeil(10.12 431): " + getCeil(10.12431) + "\n"
                + "getCeil(10.12 481): " + getCeil(10.12481) + "\n"
                + "getCeil(10.12 601): " + getCeil(10.12601) + "\n"
                + "getCeil(10.12 681): " + getCeil(10.12681) + "\n"

        );


       Bitmap b  =  Base64Util.base64ToBitmap(Base64Util.baseDemo);
        iv_base64.setImageBitmap(b);
    }

    /**
     * @向上取整
     */
    public static String getCeil(double d) {
        // d=2.523214324;
        DecimalFormat df = new DecimalFormat("###,##0.00");
        df.setRoundingMode(RoundingMode.UP);
        return df.format(d);
    }

}
