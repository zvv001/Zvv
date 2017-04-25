package com.vv.zvv.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vv.zvv.R;

import java.util.List;

/**
 * Created by zvv on 2017/4/14 09:44.
 * <p/>
 * 自定义
 */
public class MyListViewAdapter extends BaseAdapter {
    private List<String> mList;
    private Context mContext;
    private LayoutInflater mInflater;
    int currentItem = 0;
    public MyListViewAdapter(Context context, List<String> list) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //检查是否有缓存
        if (convertView == null) {
            holder = new ViewHolder();
            //实例化布局
            convertView = mInflater.inflate(R.layout.item_listview_lvda, null);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.iv_item_listView);
            holder.mTextView = (TextView) convertView.findViewById(R.id.tv_item_listView);

            convertView.setTag(holder);
        } else {
            //通过tag找到缓存的布局
            holder = (ViewHolder) convertView.getTag();
        }

        //设置布局控件中的视图
        int count = position + 1;
        count = count > 9 ? count - 9 : count;
        int id = mContext.getResources().getIdentifier("image_bank" + count, "drawable", mContext.getPackageName());
        Log.d("vv", "getView: " + id);
        holder.mImageView.setImageResource(id);
        holder.mTextView.setText(mList.get(position));

        if (currentItem == position){
            holder.mImageView.setVisibility(View.INVISIBLE);
            holder.mTextView.setTextColor(0xff00ff00);
        }else {
            holder.mImageView.setVisibility(View.VISIBLE);
            holder.mTextView.setTextColor(0xff000000);
        }
        return convertView;
    }

    public final class ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;
    }

    public void setCurrentItem(int currentItemID){
        currentItem = currentItemID;
    }


}
