package com.vv.zvv;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SimpleAdapterActivity
 */
public class SimpleAdapterActivity extends AppCompatActivity {
    @ViewInject(R.id.lv)
    private ListView mListView;

    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter);

        x.view().inject(this);
        init();
    }

    private void init() {
        //打开数据库
        File file = new File(Environment.getExternalStorageDirectory(), "my_database.db");
        SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getPath(), null, SQLiteDatabase.OPEN_READONLY);
        mCursor = database.query("table_person", new String[]{"_id", "name", "age"}, "age >= ?", new String[]{"0"}, null, null, null);
        //实例适配器
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(), R.layout.item_database,
                new String[]{"_id", "name", "age"}, new int[]{R.id.tv_id, R.id.tv_name, R.id.tv_age});
        //绑定适配器
        mListView.setAdapter(simpleAdapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();

        int idIndex = mCursor.getColumnIndex("_id");
        int nameIndex = mCursor.getColumnIndex("name");
        int ageIndex = mCursor.getColumnIndex("age");

        while (mCursor.moveToNext()) {
            int id = mCursor.getInt(idIndex);
            String name = mCursor.getString(nameIndex);
            int age = mCursor.getInt(ageIndex);

            Map<String, Object> map = new HashMap<>();
            map.put("_id", id);
            map.put("name", name);
            map.put("age", age);

            list.add(map);
        }
        mCursor.close();
        return list;
    }
}























