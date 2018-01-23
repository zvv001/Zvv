package com.vv.zvv.Utils;

import android.util.Log;

/**
 * description:
 * author: zvv
 * date: 2017/11/30 11:35
 * update: 2017/11/30
 * version: 0.0
 */
public class LogUtil {
    private static String TAG = "ds";

    public static void i(Class from, String str) {
        Log.i("ds", "class " + from.getName() + ":" + str);
    }

    public static void e(Class from, String str) {
        Log.e("ds", "class " + from.getName() + ":" + str);
    }
}
