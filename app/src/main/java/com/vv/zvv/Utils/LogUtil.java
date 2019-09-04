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


    public static void d(String tag, String msg) {  //信息太长,分段打印
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.i(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.d(tag, msg);
    }
}
