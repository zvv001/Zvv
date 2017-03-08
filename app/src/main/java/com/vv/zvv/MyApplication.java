package com.vv.zvv;

import android.app.Application;

import org.xutils.x;

/**
 * Created by zvv on 2017/1/20 11:18.
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
