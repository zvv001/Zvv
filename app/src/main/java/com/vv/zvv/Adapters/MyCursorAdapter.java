package com.vv.zvv.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.vv.zvv.R;

/**
 * Created by zvv on 2017/2/14 10:18.
 */
public class MyCursorAdapter extends CursorAdapter {

    public MyCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_database, null);
        ViewHolder viewHolder = new ViewHolder();

        viewHolder.mIdTextView = (TextView) view.findViewById(R.id.tv_id);
        viewHolder.mAgeTextView = (TextView) view.findViewById(R.id.tv_age);
        viewHolder.mNameTextView = (TextView) view.findViewById(R.id.tv_name);

        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        int id = cursor.getInt(cursor.getColumnIndex("_id"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        int age = cursor.getInt(cursor.getColumnIndex("age"));


        viewHolder.mIdTextView.setText(id + "");
        viewHolder.mNameTextView.setText(name);
        viewHolder.mAgeTextView.setText(age + "");

    }

    class ViewHolder {
        TextView mIdTextView;
        TextView mAgeTextView;
        TextView mNameTextView;
    }
}
