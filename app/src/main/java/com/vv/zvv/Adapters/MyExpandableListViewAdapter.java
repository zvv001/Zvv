package com.vv.zvv.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vv.zvv.JavaBean.Classes;
import com.vv.zvv.R;

import java.util.List;

/**
 * Created by zvv on 2017/6/7 09:38.
 */
public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
    List<Classes> mClassesList;
    LayoutInflater mInflater;

    public MyExpandableListViewAdapter(List<Classes> classesList, Context context) {
        mClassesList = classesList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        //获取一级条目的数量
        return mClassesList == null ? 0 : mClassesList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //二级条目的数量
        return mClassesList.get(groupPosition).getStudentList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        //获取一级目录对应数据
        return mClassesList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //获取二级目录的数据
        return mClassesList.get(groupPosition).getStudentList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        //
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        //
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_expandablelistview_second, null);
        } else {
            view = convertView;
        }

        TextView tv_first = (TextView) view.findViewById(R.id.tv_second);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView_arrow);
        if (isExpanded) {
            icon.setBackgroundResource(R.drawable.icon_arrow_up);
        } else {
            icon.setBackgroundResource(R.drawable.icon_arrow_down);
        }
        tv_first.setText(mClassesList.get(groupPosition).getClassName());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_expandablelistview_third, null);
        } else {
            view = convertView;
        }

        TextView tv_first = (TextView) view.findViewById(R.id.tv_third);
//        ImageView icon = (ImageView) view.findViewById(R.id.imageView_arrow);
//        if (isExpanded) {
//            icon.setBackgroundResource(R.drawable.icon_arrow_up);
//        } else {
//            icon.setBackgroundResource(R.drawable.icon_arrow_down);
//        }
        tv_first.setText(mClassesList.get(groupPosition).getStudentList().get(childPosition));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        //二级条目是否可被点击
        return true;
    }
}
