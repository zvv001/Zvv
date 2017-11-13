package com.vv.zvv.Adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.vv.zvv.JavaBean.College;
import com.vv.zvv.R;
import com.vv.zvv.Views.CustomExpandableListView;

import java.util.List;

/**
 * Created by zvv on 2017/6/7 11:15.
 * 三级联动适配器
 */
public class MyThreeExpandableListViewAdapter extends BaseExpandableListAdapter {
    List<College> mCollegeList;
    Context mContext;
    LayoutInflater mInflater;

    public MyThreeExpandableListViewAdapter(Context context, List<College> collegeList) {
        mCollegeList = collegeList;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return mCollegeList == null ? 0 : mCollegeList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // 很关键，，一定要返回  1
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mCollegeList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mCollegeList.get(groupPosition).getClassList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
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
            view = mInflater.inflate(R.layout.item_expandablelistview_first, null);
        } else {
            view = convertView;
        }

        TextView tv_first = (TextView) view.findViewById(R.id.tv_first);
//        ImageView icon = (ImageView) view.findViewById(R.id.imageView_arrow);
//        if (isExpanded) {
//            icon.setBackgroundResource(R.drawable.icon_arrow_up);
//        } else {
//            icon.setBackgroundResource(R.drawable.icon_arrow_down);
//        }
        tv_first.setText(mCollegeList.get(groupPosition).getCollegeName());
        return view;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return getGenericExpandableListView(mCollegeList.get(groupPosition));
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * 根据字符串生成布局，，因为我没有写layout.xml 所以用java 代码生成
     * <p/>
     * 实际中可以通过Inflate加载自己的自定义布局文件，设置数据之后并返回
     *
     * @param string
     * @return
     */
    private TextView getGenericView(String string) {

        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView textView = new TextView(mContext);
        textView.setLayoutParams(layoutParams);

        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);

        textView.setPadding(40, 20, 0, 20);
        textView.setText(string);
        textView.setTextColor(Color.RED);
        textView.setBackgroundColor(Color.GRAY);
        return textView;
    }

    /**
     * 返回子ExpandableListView 的对象  此时传入的是该大学下所有班级的集合。
     *
     * @param college
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public ExpandableListView getGenericExpandableListView(College college) {
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        CustomExpandableListView customExpandableListView = new CustomExpandableListView(mContext);
        customExpandableListView.setDivider(null);
        customExpandableListView.setGroupIndicator(null);

        // 加载班级的适配器
//        MyThreeExpandableListViewAdapter adapter = new MyThreeExpandableListViewAdapter(college.getClassList(), mContext);
//
//        customExpandableListView.addAdapter(adapter);

        customExpandableListView.setPadding(20, 0, 0, 0);
        return customExpandableListView;
    }
}
