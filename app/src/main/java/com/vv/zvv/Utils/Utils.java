package com.vv.zvv.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;

import java.io.InputStream;

/**
 * Created by xiangzhihong on 2016/4/8 on 11:50.
 */
public class Utils {

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static String readAssert(Context context, String fileName) {
        String jsonString = "";
        String resultString = "";
        try {
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            resultString = new String(buffer, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    //隐藏键盘
    public static void hideKeyBoard(Activity context) {
        if (context != null) {
            try {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(context.getWindow().getDecorView().getWindowToken(), 0);
                }
            } catch (Exception ignored) {
            }
        }
    }


    //px -> dip
    public static int px2dip(int pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //dip -> px
    public static int dip2px(int dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    //获取当前版本号(e.g: v2.9.2)
    public static String loadVersion(Context con) {
        try {
            PackageInfo packageInfo = con.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(con.getPackageName(), 0);

            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            // e.printStackTrace();
            return "";
        }
    }
}
