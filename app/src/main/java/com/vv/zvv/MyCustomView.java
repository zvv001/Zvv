package com.vv.zvv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vv.zvv.Utils.MyChatView;
import com.vv.zvv.Utils.MyElectronicCoupons;
import com.vv.zvv.Utils.MyLineChartView;
import com.vv.zvv.Utils.MyPolygonView;
import com.vv.zvv.Utils.MyTimeView;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 我的自定义View的展示
 */
public class MyCustomView extends AppCompatActivity {
    @ViewInject(R.id.cv_MyElectronicCoupons)
    private MyElectronicCoupons mMyElectronicCoupons;

    @ViewInject(R.id.cv_MyChatView)
    private MyChatView mMyChatView;

    @ViewInject(R.id.cv_MyLineChartView)
    private MyLineChartView mMyLineChartView;

    @ViewInject(R.id.cv_MyPolygonView)
    private MyPolygonView mMyPolygonView;

    @ViewInject(R.id.cv_MyTimeView)
    private MyTimeView mMyTimeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom_view);

        x.view().inject(this);
    }

    @Event(value = {R.id.btn_MyElectronicCoupons, R.id.btn_MyChatView, R.id.btn_MyLineChartView,
            R.id.btn_MyPolygonView, R.id.btn_MyTimeView}, type = View.OnClickListener.class)
    private void OnCLick(View view) {
        initButton();
        switch (view.getId()) {
            case R.id.btn_MyElectronicCoupons:
                mMyElectronicCoupons.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_MyChatView:
                mMyChatView.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_MyLineChartView:
                mMyLineChartView.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_MyPolygonView:
                mMyPolygonView.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_MyTimeView:
                mMyTimeView.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private void initButton() {
        mMyElectronicCoupons.setVisibility(View.GONE);
        mMyChatView.setVisibility(View.GONE);
        mMyLineChartView.setVisibility(View.GONE);
        mMyPolygonView.setVisibility(View.GONE);
        mMyTimeView.setVisibility(View.GONE);
    }
}
