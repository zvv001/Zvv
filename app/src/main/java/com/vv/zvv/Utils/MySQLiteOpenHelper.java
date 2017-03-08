package com.vv.zvv.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by zvv on 2017/2/13 10:27.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    // 数据库文件名
    private final static String DB_NAME = "my_database.db";
    // 数据库版本号
    private final static int DB_VERSION = 1;
    // 数据库表名
    public final static String TABLE_NAME = "table_person";

    /**
     * 创建数据库文件
     *
     * @param context 上下文
     * @param name    数据库文件名
     * @param factory 游标工厂, 如果为null,Android系统提供默认的游标
     * @param version 数据库版本号
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建数据库文件:
    public MySQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);//创建了名为"my_database.db"数据库,版本号 1  数据库地址: data/data/com.vv.zvv/databases/my_database.db
    }

    /**
     * 创建了数据库
     * @param db 系统返回的数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建表语句，语义：如果不存在则创建表，字段包括：主键_id，name，age
        /*注意: SQ语句: exists后面空格,()不要忘记*/
//        String sql = "create table if not exists" + TABLE_NAME + "(_id integer primary key autoincrement, name varchar, age integer)";
        String sql = "create table if not exists " + TABLE_NAME + "(_id integer primary key autoincrement, name varchar, age integer)";
        // 执行创建表的数据库语句
        db.execSQL(sql);
    }

    /**
     * @param db  系统返回的数据库对象
     * @param oldVersion 旧版本
     * @param newVersion 新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            Log.d("vv", "onUpgrade: onUpgrade");
        }
    }
}
