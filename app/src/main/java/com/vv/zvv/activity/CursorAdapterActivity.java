package com.vv.zvv.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.vv.zvv.Adapters.MyCursorAdapter;
import com.vv.zvv.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

public class CursorAdapterActivity extends AppCompatActivity {

    @ViewInject(R.id.lv)
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_adapter);

        x.view().inject(this);
        init();
    }

    private void init() {
        //拿到数据
        File file = new File(Environment.getExternalStorageDirectory(), "my_database.db"); // 数据库文件 /storage/emulated/0/my_database.db
        SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase(file.getPath(), null, SQLiteDatabase.OPEN_READONLY);

//        String path = "/data/data/com.vv.zvv/databases/my_database.db";
//        SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        Log.d("vv", "init: path:" + sqLiteDatabase.getPath());

        //读取数据
//        Cursor cursor = sqLiteDatabase.query("", new String[]{"age >= ?"}, "0", null, null, null, null);
        Cursor cursor = sqLiteDatabase.query("table_person", new String[]{"_id", "name", "age"}, "age >= ?", new String[]{"0"}, null, null, null);

        //适配
//        MyCursorAdapter myCursorAdapter = new MyCursorAdapter(this,cursor,null);
        MyCursorAdapter adapter = new MyCursorAdapter(this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListView.setAdapter(adapter);
    }
}
