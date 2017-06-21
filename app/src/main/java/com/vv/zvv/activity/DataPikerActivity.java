package com.vv.zvv.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.vv.zvv.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class DataPikerActivity extends AppCompatActivity {
    @ViewInject(R.id.tv_DatePikerActivity)
    private TextView mTextView;
    @ViewInject(R.id.dp_tv_DatePikerActivity)
    private DatePicker mDatePicker;

    @ViewInject(R.id.tp)
    private TimePicker mTimePicker;


    private int year = 2017;
    private int month = 1;// 该数值为月份下脚标
    private int day = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_piker);

        x.view().inject(this);

        initDatePicker();
        initTimerPicker();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initTimerPicker() {
//        mTimePicker.setHour(12);
//        mTimePicker.setMinute(0);
        mTimePicker.setIs24HourView(true);
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mTextView.setText(hourOfDay + "时" + minute + "分");
            }
        });
    }


    private void initDatePicker() {
        mDatePicker.init(year, month - 1, day, new DatePicker.OnDateChangedListener() {
            /**
             *
             * @param view
             * @param year
             * @param monthOfYear
             * @param dayOfMonth
             */
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mTextView.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
            }
        });
    }
}
