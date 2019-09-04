package com.vv.zvv.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vv.zvv.JavaBean.CircularAnimationChartViewItem;
import com.vv.zvv.R;
import com.vv.zvv.Utils.ToastUtil;
import com.vv.zvv.Views.CircularAnimationChartView;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 环（圆）形动态统计图
 * author: zvv
 * date: 2017/11/13 13:21
 * update: 2017/11/13
 * version:
 */
public class CircularAnimationChartViewActivity extends AppCompatActivity {
    /**
     * app:animation="false" 动效
     * app:background="@color/f5f4f2" 背景
     * app:round="false" 是否是圆环
     * app:shadow="false"
     * app:shadowColor="#F2F2F2"
     * app:showTitle="false"
     */

    @ViewInject(R.id.magnificentChart)
    private CircularAnimationChartView circularAnimationChartView;

//    @ViewInject(R.id.locusPassWordView)
//    private LocusPassWordView locusPassWordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_animation_chart_view);

        x.view().inject(this);

        initView();
    }

    /*初始化视图*/
    private void initView() {
         /*动效统计图*/
        circularAnimationChartView = (CircularAnimationChartView) findViewById(R.id.magnificentChart);
        circularAnimationChartView.setChartBackgroundColor(Color.parseColor("#f5f4f2"));

//        locusPassWordView.setVisibility(View.GONE);
    }

    @Event(value = {R.id.btn_loadData, R.id.btn_showLocus}, type = View.OnClickListener.class)
    private void onCLick(View view) {
        switch (view.getId()) {
            case R.id.btn_loadData:
//                locusPassWordView.setVisibility(View.GONE);
                ToastUtil.showShortToast(this, "Todo!");
                initChat();
                break;
            case R.id.btn_showLocus:
//                locusPassWordView.setVisibility(View.VISIBLE);
                break;
            default:
                break;

        }
    }

    private void initChat() {
        try {
            CircularAnimationChartViewItem defaultItem = new CircularAnimationChartViewItem("默认", 100, Color.parseColor("#f5f4f2"));
            CircularAnimationChartViewItem terminalPaidIncomeItem = new CircularAnimationChartViewItem("定期已兑付收益", 40, Color.parseColor("#FBAF00"));
            CircularAnimationChartViewItem terminalInterestItem = new CircularAnimationChartViewItem("月返息已兑付收益", 10, Color.parseColor("#00a0ff"));
            CircularAnimationChartViewItem monthReturnPaidInterestItem = new CircularAnimationChartViewItem("定期未兑付收益", 35, Color.parseColor("#FF6575"));
            CircularAnimationChartViewItem monthReturnUnpaidInterestItem = new CircularAnimationChartViewItem("月返息未兑付收益", 15, Color.parseColor("#2dce8f"));

            List<CircularAnimationChartViewItem> chartItemsList = new ArrayList<>();
            chartItemsList.add(terminalPaidIncomeItem);
            chartItemsList.add(monthReturnPaidInterestItem);
            chartItemsList.add(terminalInterestItem);
            chartItemsList.add(monthReturnUnpaidInterestItem);
            circularAnimationChartView.setChartItemsList(chartItemsList);
            circularAnimationChartView.setMaxValue(100);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            circularAnimationChartView.setAnimationState(true);
        }
    }
}
