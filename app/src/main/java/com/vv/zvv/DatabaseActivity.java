package com.vv.zvv;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.vv.zvv.Utils.MySQLiteOpenHelper;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 数据库
 */
public class DatabaseActivity extends AppCompatActivity {
    @ViewInject(R.id.tv_show)
    private TextView mText;

    //数据库助手
    private MySQLiteOpenHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        x.view().inject(this);

        //创建数据库助手
        mHelper = new MySQLiteOpenHelper(this);
    }

    @Event(value = {R.id.btn_createDb, R.id.btn_add, R.id.btn_delete, R.id.btn_modify, R.id.btn_select,
            R.id.btn_SimpleAdapter, R.id.btn_CursorAdapter, R.id.btn_SimpleCursorAdapter}, type = View.OnClickListener.class)
    private void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_createDb:
//                SQLiteDatabase db = mHelper.getWritableDatabase();
                SQLiteDatabase db = getDb();
                //不使用时,关闭数据库
                db.close();
                break;
            case R.id.btn_add:
//                SQLiteDatabase dbAdd = mHelper.getWritableDatabase();
                SQLiteDatabase dbAdd = getDb();
                ContentValues values = new ContentValues();
                values.put("name", "刘德华");
                values.put("age", 50);
                // table：要插入数据的表名
                //nullColumnHack：当插入数据为null时，nullColumnHack不允许为空，通过系统的处理保证了程序的稳定性；当插入数据不为null时，nullColumnHack排不上用场，为null
                // values：插入的数据
                dbAdd.insert("table_person", null, values);

                // 模式事务，特点：事务开始与结束之间的代码，要么全执行，要么全不执行。

                // 开始事务
                dbAdd.beginTransaction();
                try {
                    for (int i = 0; i < 10; i++) {
                        // 插入数据
                        String sql = "insert into table_person(name, age) values('赵四', " + i + ")";
                        dbAdd.execSQL(sql);
                    }
                    //事务成功
                    dbAdd.setTransactionSuccessful();
                } catch (Exception e) {

                } finally {
                    //结束事务
                    dbAdd.endTransaction();
                }
                //关闭数据库
                dbAdd.close();
                break;
            case R.id.btn_delete:
//                SQLiteDatabase dbDelete = mHelper.getWritableDatabase();
                SQLiteDatabase dbDelete = getDb();
                // 删除数据库指定表中的数据
                // table：表名
                // whereClause：删除条件，格式“name = ?”
                // whereArgs：满足删除的条件，即删除的数据，格式“"张三"”
                dbDelete.delete(mHelper.TABLE_NAME, "name = ?", new String[]{"刘德华"});
                dbDelete.close();
                break;
            case R.id.btn_modify:
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "张三");
                contentValues.put("age", 25);
//                SQLiteDatabase dbModify = mHelper.getWritableDatabase();
                SQLiteDatabase dbModify = getDb();
                // table：表名
                // values：更新的数据
                // whereClause：更新条件
                // whereArgs：满足更新的条件
                dbModify.update(mHelper.TABLE_NAME, contentValues, "name = ?", new String[]{"刘德华"});
                dbModify.close();
                break;
            case R.id.btn_select:
                //得到数据库
//                SQLiteDatabase dbSelect = mHelper.getReadableDatabase();
                SQLiteDatabase dbSelect = getDb();

                Log.d("vv", "onCreate: dbSelect.getPath(): " + dbSelect.getPath());

                // 查询数据库
                // table：表名
                // columns：被查询的列（字段），可以有很多个
                // selection：查询条件
                // selectionArgs：满足查询的条件
                // groupBy：指定分组（多数情况不使用）
                // having：分组筛选数据关键字（多数情况不使用）
                // orderBy：排序
                Cursor cursor = dbSelect.query(mHelper.TABLE_NAME, new String[]{"name", "age"}, null, null, null, null, null);

                int nameIndex = cursor.getColumnIndex("name");
                int ageIndex = cursor.getColumnIndex("age");
                String showText = "";
                while (cursor.moveToNext()) {
                    String name = cursor.getString(nameIndex);
                    int age = cursor.getInt(ageIndex);

                    showText = showText + "姓名: " + name + "\t年龄: " + age + "\n";
                }
                mText.setText(showText);
                dbSelect.close();
                cursor.close();
                break;
            case R.id.btn_CursorAdapter:
                startActivity(new Intent(this, CursorAdapterActivity.class));
                break;
            case R.id.btn_SimpleAdapter:
                startActivity(new Intent(this, SimpleAdapterActivity.class));
                break;
            case R.id.btn_SimpleCursorAdapter:
                startActivity(new Intent(this, SimpleCursorAdapterActivity.class));
                break;

            default:
                break;

        }
    }

    @Event(value = R.id.btn_deleteAll, type = View.OnClickListener.class)
    private void OnDeleteClick(View view) {
        switch (view.getId()) {
            case R.id.btn_deleteAll:
//                SQLiteDatabase database = mHelper.getReadableDatabase();
                SQLiteDatabase database = getDb();
//                database.delete(mHelper.TABLE_NAME, "name = ?", new String[]{"张三"});
                database.delete(mHelper.TABLE_NAME, "age >= ?", new String[]{"0"});
                break;

        }
    }

    public SQLiteDatabase getDb() {
        //创建数据库 my_database.db
        String filePath = Environment.getExternalStorageDirectory() + "/my_database.db";
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(filePath, null);
        //在数据库中创建一张表 table_person
        String sql = "create table if not exists table_person(_id integer primary key autoincrement, name varchar, age integer)";
        // 执行创建表的数据库语句
        database.execSQL(sql);
        return database;
    }
}


























