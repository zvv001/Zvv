/*
 *  Copyright 2010 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.vv.zvv.Adapters;

import android.content.Context;

import com.vv.zvv.JavaBean.AddressDetailsEntity;

import java.util.List;


public class ProvinceWheelAdapter extends BaseWheelAdapter<AddressDetailsEntity.ProvinceEntity> {
	public ProvinceWheelAdapter(Context context, List<AddressDetailsEntity.ProvinceEntity> list) {
		super(context,list);
//		this.setItemResource(R.layout.item_textview);//自定义item
//		this.setItemTextResource(R.id.title);
		this.setTextColor(0xFF00FF00);//设置字体颜色
//		this.setTextSize(30);
	}

	@Override
	protected CharSequence getItemText(int index) {
		AddressDetailsEntity.ProvinceEntity data = getItemData(index);
		if(data != null){
			return data.Name;
		}
		return null;
	}
}
