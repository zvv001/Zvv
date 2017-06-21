package com.vv.zvv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.vv.zvv.Interface.OnAddressChangeListener;
import com.vv.zvv.JavaBean.AddressDtailsEntity;
import com.vv.zvv.JavaBean.AddressModel;
import com.vv.zvv.R;
import com.vv.zvv.Utils.ChooseAddressWheel;
import com.vv.zvv.Utils.JsonUtil;
import com.vv.zvv.Utils.Utils;
import com.vv.zvv.Utils.db.DBCopyUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class RegionSelectActivity extends AppCompatActivity {

    private static final int REGION_REQUEST_CODE = 001;

    @ViewInject(R.id.tv_result)
    private TextView tv_result;

    @ViewInject(R.id.tv_showAddress)
    private TextView tv_showAddress;


    private ChooseAddressWheel chooseAddressWheel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_select);
        x.view().inject(this);

        initView();
        initData();
    }


    private void initView() {
        //地址选择器
        chooseAddressWheel = new ChooseAddressWheel(RegionSelectActivity.this);
        chooseAddressWheel.setOnAddressChangeListener(new OnAddressChangeListener() {
            @Override
            public void onAddressChange(String province, String city, String district) {
                tv_showAddress.setText(province + " " + city + " " + district);
            }
        });
    }

    private void initData() {
        String address = Utils.readAssert(this, "address.txt");
        AddressModel model = JsonUtil.parseJson(address, AddressModel.class);
        if (model != null) {
            AddressDtailsEntity data = model.Result;
            if (data == null) return;
            tv_showAddress.setText(data.Province + " " + data.City + " " + data.Area);
            if (data.ProvinceItems != null && data.ProvinceItems.Province != null) {
                chooseAddressWheel.setProvince(data.ProvinceItems.Province);
                chooseAddressWheel.defaultValue(data.Province, data.City, data.Area);
            }
        }
    }

    @Event(value = {R.id.btn_select, R.id.tv_showAddress}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_select:
                DBCopyUtil.copyDataBaseFromAssets(this, "region.db");
                startActivityForResult(new Intent(this, RegionSelectDialogActivity.class), REGION_REQUEST_CODE);
                break;
            case R.id.tv_showAddress://地址选择器第二种方法：
                Utils.hideKeyBoard(this);
                chooseAddressWheel.show(view);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REGION_REQUEST_CODE && resultCode == 200) {
            String province = data.getStringExtra(RegionSelectDialogActivity.REGION_PROVINCE);
            String city = data.getStringExtra(RegionSelectDialogActivity.REGION_CITY);
            String area = data.getStringExtra(RegionSelectDialogActivity.REGION_AREA);

            tv_result.setText(province + " " + city + " " + area);
        }
    }
}
