package com.vv.zvv.activity;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.vv.zvv.BuildConfig;

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

        //fresco
        Fresco.initialize(this);
    }
}
