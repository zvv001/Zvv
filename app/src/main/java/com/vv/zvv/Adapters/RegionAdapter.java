package com.vv.zvv.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vv.zvv.JavaBean.RegionModel;
import com.vv.zvv.R;

import java.util.List;

/**
 * Created by zvv on 2017/6/20 11:50.
 */
public class RegionAdapter extends BaseAdapter {
    private List<RegionModel> mList;
    private LayoutInflater mInflater;

    public RegionAdapter(Context context, List<RegionModel> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    //自改数据
    public void addData(List<RegionModel> list) {
        mList.clear();
        mList.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public RegionModel getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_footview, null);
            mHolder = new ViewHolder();
            mHolder.mTextView = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        if (mList.get(position).getName() != null) {
            mHolder.mTextView.setText(mList.get(position).getName());
        } else {
            mHolder.mTextView.setText("-");
        }
        return convertView;
    }

    class ViewHolder {
        TextView mTextView;//内容
    }
}
