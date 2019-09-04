package com.vv.zvv.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.vv.zvv.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class SpinnerActivity extends AppCompatActivity {

    @ViewInject(R.id.btn_spinner)
    Spinner mSpinnerSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        x.view().inject(this);

        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mSpinnerSimple.setDropDownWidth((int) this.getResources().getDimension(R.dimen.xx1080)); //下拉宽度
            mSpinnerSimple.setDropDownHorizontalOffset(0); //下拉的横向偏移
            mSpinnerSimple.setDropDownVerticalOffset((int) this.getResources().getDimension(R.dimen.yy132)); //下拉的纵向偏移
        }
        mSpinnerSimple.setBackgroundColor(this.getResources().getColor(R.color.ffffff)); //下拉的背景色
        //spinner mode ： dropdown or dialog , just edit in layout xml
        //mSpinnerSimple.setPrompt("Spinner Title"); //弹出框标题，在dialog下有效


        final String[] spinnerItems = {"10", "200", "400"};
        //自定义选择填充后的字体样式
        //只能是textview样式，否则报错：ArrayAdapter requires the resource ID to be a TextView
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                R.layout.item_select, spinnerItems);
        //自定义下拉的字体样式
        spinnerAdapter.setDropDownViewResource(R.layout.item_drop);
        //这个在不同的Theme下，显示的效果是不同的
        //spinnerAdapter.setDropDownViewTheme(Theme.LIGHT);
        mSpinnerSimple.setAdapter(spinnerAdapter);

        //选择监听
        mSpinnerSimple.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //parent就是父控件spinner
            //view就是spinner内填充的textview,id=@android:id/text1
            //position是值所在数组的位置
            //id是值所在行的位置，一般来说与positin一致
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Log.d("vvvv", "onItemSelected: " + spinnerItems[pos]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
                Log.d("vvvv", "onItemSelected====");

            }
        });
    }
}
































