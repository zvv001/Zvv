package com.vv.zvv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.vv.zvv.Adapters.RegionAdapter;
import com.vv.zvv.JavaBean.RegionModel;
import com.vv.zvv.R;
import com.vv.zvv.Utils.db.RegionDao;

import java.util.List;

public class RegionSelectDialogActivity extends AppCompatActivity {

    public static final String REGION_PROVINCE = "region_province";
    public static final String REGION_CITY = "region_city";
    public static final String REGION_AREA = "region_area";
    private static final int RESULT_CODE_SUCCESS = 200;

    private ListView mListView;
    private RegionAdapter mAdapter;
    private RegionDao mRegionDao;

    private List<RegionModel> mProvinceList;
    private List<RegionModel> mCityList;
    private List<RegionModel> mAreaList;
    private int state = 0;

    private String mProvince;
    private String mCity;
    private String mArea;

    private TextView mTitleTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_select_dialog);


        initView();
        initData();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initStatue(position);
            }
        });
    }

    private void initView() {
        mTitleTextView = (TextView) findViewById(R.id.title);
        mTitleTextView.setText("选择省份");

        mListView = (ListView) findViewById(R.id.listView);
    }

    private void initData() {
        mRegionDao = new RegionDao(this);

        //省
        mProvinceList = mRegionDao.loadProvinceList();
        mAdapter = new RegionAdapter(this, mProvinceList);
        mListView.setAdapter(mAdapter);
    }

    private void initStatue(int position) {
        RegionModel region = mAdapter.getItem(position);
        Log.d("vv", "initStatue: 名称：" + region.getName());
        switch (state) {
            case 0://点击省
                mProvince = region.getName();
                //适配市
                mTitleTextView.setText("选择市");
                mCityList = mRegionDao.loadCityList(region.getId());
                Log.d("vv", "城市个数：" + mCityList.size());
                mAdapter.addData(mCityList);
                state++;
                break;
            case 1://点击市
                mTitleTextView.setText("选择区");
                mCity = region.getName();

                //适配区
                mAreaList = mRegionDao.loadCountyList(region.getId());
                Log.d("vv", "地区个数：" + mAreaList.size());
                if (mAreaList.size() == 0) {
                    //防止有的城市没有县级
                    finishSelect();
                } else {
                    mAdapter.addData(mAreaList);
                }
                state++;
                break;
            case 2://点击区
                mArea = region.getName();
                state++;

                finishSelect();
                break;
            default:
                break;
        }
    }

    /**
     * 完成
     */
    private void finishSelect() {
        Intent data = new Intent();
        data.putExtra(REGION_PROVINCE, mProvince);
        data.putExtra(REGION_CITY, mCity);
        data.putExtra(REGION_AREA, mArea);

        setResult(RESULT_CODE_SUCCESS, data);

        state = 0;
        finish();
    }
}
