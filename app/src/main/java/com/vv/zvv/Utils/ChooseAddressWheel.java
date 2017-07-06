package com.vv.zvv.Utils;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.vv.zvv.Adapters.AreaWheelAdapter;
import com.vv.zvv.Adapters.CityWheelAdapter;
import com.vv.zvv.Adapters.ProvinceWheelAdapter;
import com.vv.zvv.Interface.OnAddressChangeListener;
import com.vv.zvv.JavaBean.AddressDetailsEntity;
import com.vv.zvv.R;
import com.vv.zvv.Utils.view.wheelview.MyOnWheelChangedListener;
import com.vv.zvv.Utils.view.wheelview.MyWheelView;

import java.util.List;


public class ChooseAddressWheel implements MyOnWheelChangedListener {

    MyWheelView provinceWheel;
    MyWheelView cityWheel;
    MyWheelView districtWheel;

    private TextView confirm_button, cancel_button;

    private Activity context;
    private View parentView;
    private PopupWindow popupWindow = null;
    private WindowManager.LayoutParams layoutParams = null;
    private LayoutInflater layoutInflater = null;

    private List<AddressDetailsEntity.ProvinceEntity> province = null;

    private OnAddressChangeListener onAddressChangeListener = null;

    public ChooseAddressWheel(Activity context) {
        this.context = context;
        init();
    }

    private void init() {
        layoutParams = context.getWindow().getAttributes();
        layoutInflater = context.getLayoutInflater();
        initView();
        initPopupWindow();
    }

    private void initView() {
        parentView = layoutInflater.inflate(R.layout.choose_city_layout, null);
        //省市区选择器
        provinceWheel = (MyWheelView) parentView.findViewById(R.id.province_wheel);
        cityWheel = (MyWheelView) parentView.findViewById(R.id.city_wheel);
        cityWheel.setDrawShadows(false);
        districtWheel = (MyWheelView) parentView.findViewById(R.id.district_wheel);

        //确定按钮
        confirm_button = (TextView) parentView.findViewById(R.id.confirm_button);
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAddressChangeListener != null) {
                    int provinceIndex = provinceWheel.getCurrentItem();
                    int cityIndex = cityWheel.getCurrentItem();
                    int areaIndex = districtWheel.getCurrentItem();

                    String provinceName = null, cityName = null, areaName = null;

                    List<AddressDetailsEntity.ProvinceEntity.CityEntity> cities = null;//城市
                    if (province != null && province.size() > provinceIndex) {
                        AddressDetailsEntity.ProvinceEntity provinceEntity = province.get(provinceIndex);
                        cities = provinceEntity.City;
                        provinceName = provinceEntity.Name;
                    }
                    List<AddressDetailsEntity.ProvinceEntity.AreaEntity> districts = null;
                    if (cities != null && cities.size() > cityIndex) {
                        AddressDetailsEntity.ProvinceEntity.CityEntity cityEntity = cities.get(cityIndex);
                        districts = cityEntity.Area;
                        cityName = cityEntity.Name;
                    }

                    if (districts != null && districts.size() > areaIndex) {
                        AddressDetailsEntity.ProvinceEntity.AreaEntity areaEntity = districts.get(areaIndex);
                        areaName = areaEntity.Name;
                    }

                    onAddressChangeListener.onAddressChange(provinceName, cityName, areaName);
                }
                popupWindow.dismiss();
            }
        });


        cancel_button = (TextView) parentView.findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


        provinceWheel.setVisibleItems(7);
        cityWheel.setVisibleItems(7);
        districtWheel.setVisibleItems(7);

        provinceWheel.addChangingListener(this);
        cityWheel.addChangingListener(this);
        districtWheel.addChangingListener(this);
    }

    private void initPopupWindow() {
        //设置选择器的高度
//        popupWindow = new PopupWindow(parentView, WindowManager.LayoutParams.MATCH_PARENT, (int) (Utils.getScreenHeight(context) * (2.0 / 5)));
        popupWindow = new PopupWindow(parentView, WindowManager.LayoutParams.MATCH_PARENT, (int) (Utils.getScreenHeight(context) * 0.4));
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setAnimationStyle(R.style.anim_push_bottom);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());//保证 点击屏幕内弹窗外的区域 弹窗消失
        popupWindow.setBackgroundDrawable(new AnimationDrawable());//非空 可保证点击其他区域 弹窗消失
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                layoutParams.alpha = 1.0f;
                context.getWindow().setAttributes(layoutParams);
                popupWindow.dismiss();
            }
        });
    }

    private void bindData() {
        provinceWheel.setViewAdapter(new ProvinceWheelAdapter(context, province));
        updateCity();
        updateDistrict();
    }

    @Override
    public void onChanged(MyWheelView wheel, int oldValue, int newValue) {
        if (wheel == provinceWheel) {
            updateCity();//省份改变后城市和地区联动
        } else if (wheel == cityWheel) {
            updateDistrict();//城市改变后地区联动
        } else if (wheel == districtWheel) {
        }
    }

    private void updateCity() {
        int index = provinceWheel.getCurrentItem();
        List<AddressDetailsEntity.ProvinceEntity.CityEntity> cities = province.get(index).City;
        if (cities != null && cities.size() > 0) {
            cityWheel.setViewAdapter(new CityWheelAdapter(context, cities));
            cityWheel.setCurrentItem(0);
            updateDistrict();
        }
    }

    private void updateDistrict() {
        int provinceIndex = provinceWheel.getCurrentItem();
        List<AddressDetailsEntity.ProvinceEntity.CityEntity> citys = province.get(provinceIndex).City;
        int cityIndex = cityWheel.getCurrentItem();
        List<AddressDetailsEntity.ProvinceEntity.AreaEntity> districts = citys.get(cityIndex).Area;
        if (districts != null && districts.size() > 0) {
            districtWheel.setViewAdapter(new AreaWheelAdapter(context, districts));
            districtWheel.setCurrentItem(0);
        }

    }

    public void show(View v) {
        layoutParams.alpha = 0.6f;//设置弹窗背景透明度-vv
        context.getWindow().setAttributes(layoutParams);
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }

    public void setProvince(List<AddressDetailsEntity.ProvinceEntity> province) {
        this.province = province;
        bindData();
    }

    //设置默认数据：控制地址选择器停留的地址
    public void defaultValue(String provinceStr, String city, String area) {
        if (TextUtils.isEmpty(provinceStr)) return;
        for (int i = 0; i < province.size(); i++) {
            AddressDetailsEntity.ProvinceEntity provinces = province.get(i);
            if (provinces != null && provinces.Name.equalsIgnoreCase(provinceStr)) {//equalsIgnoreCase() 忽略大小写比较
                provinceWheel.setCurrentItem(i);
                if (TextUtils.isEmpty(city)) return;
                List<AddressDetailsEntity.ProvinceEntity.CityEntity> cities = provinces.City;
                for (int j = 0; j < cities.size(); j++) {
                    AddressDetailsEntity.ProvinceEntity.CityEntity cityEntity = cities.get(j);
                    if (cityEntity != null && cityEntity.Name.equalsIgnoreCase(city)) {
                        cityWheel.setViewAdapter(new CityWheelAdapter(context, cities));
                        cityWheel.setCurrentItem(j);
                        if (TextUtils.isEmpty(area)) return;
                        List<AddressDetailsEntity.ProvinceEntity.AreaEntity> areas = cityEntity.Area;
                        for (int k = 0; k < areas.size(); k++) {
                            AddressDetailsEntity.ProvinceEntity.AreaEntity areaEntity = areas.get(k);
                            if (areaEntity != null && areaEntity.Name.equalsIgnoreCase(area)) {
                                districtWheel.setViewAdapter(new AreaWheelAdapter(context, areas));
                                districtWheel.setCurrentItem(k);
                            }
                        }
                    }
                }
            }
        }
    }


    public void setOnAddressChangeListener(OnAddressChangeListener onAddressChangeListener) {
        this.onAddressChangeListener = onAddressChangeListener;
    }
}