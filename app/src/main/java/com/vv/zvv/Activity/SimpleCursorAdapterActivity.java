package com.vv.zvv.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.vv.zvv.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

/**
 * SimpleCursorAdapterActivity
 */
public class SimpleCursorAdapterActivity extends AppCompatActivity {
    @ViewInject(R.id.lv)
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_cursor_adapter);

        x.view().inject(this);
        init();
    }

    private void init() {
        File file = new File(Environment.getExternalStorageDirectory(), "my_database.db");
        SQLiteDatabase database = SQLiteDatabase.openDatabase(file.getPath(), null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = database.query("table_person", new String[]{"_id", "name", "age"}, "age >= ?", new String[]{"0"}, null, null, null);
        // flags：FLAG_REGISTER_CONTENT_OBSERVER，官方推荐使用的标签，当数据发生变化时，通过观察者标签，实时观察数据的变化，并刷新UI界面
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                this, R.layout.item_database, cursor, new String[]{"_id","name", "age"}, new int[]{R.id.tv_id,R.id.tv_name, R.id.tv_age}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        mListView.setAdapter(simpleCursorAdapter);
    }
}
