package com.vv.zvv.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import com.vv.zvv.R;
import com.vv.zvv.Utils.view.FloatScanView;

import java.lang.reflect.Method;

/**
 * description: 打开悬浮权限
 * autour: zvv
 * date: 2017/9/5 16:53
 * update: 2017/9/5
 * version:
 */
public class BaseActivity extends Activity {
    private WindowManager wm = null;
    private WindowManager.LayoutParams wmParams = null;
    private FloatScanView fsv = null;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();


    }

    //初始化
    private void init() {

        //创建dialog
//        dialog = new AlertDialog.Builder(this)
//                .setTitle("悬浮窗权限管理")
//                .setMessage("是否去开启悬浮窗权限？")
//                .setPositiveButton("是", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //打开权限设置
//                        openSetting();
//                    }
//                })
//                .setNegativeButton("否", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .create();
//
//        //创建悬浮框
//        openPermission();
    }

    private void createFloatView() {
        try {
            WindowManager manager = this.getWindowManager();
            DisplayMetrics outMetrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(outMetrics);
            int width = outMetrics.widthPixels;
            int height = outMetrics.heightPixels;

            wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            //        wmParams = new WindowManager.LayoutParams();
            wmParams = ((MyApplication) getApplication()).getMywmParams();


            //        wmParams.type=2002;          //type是关键，这里的2002表示系统级窗口
//        wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            if (Build.VERSION.SDK_INT >= 24) {
                wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            } else {
                wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            }
            wmParams.format = PixelFormat.RGBA_8888;//设置图片格式，效果为背景透明
            wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;//
            wmParams.gravity = Gravity.LEFT | Gravity.TOP;//
//            wmParams.gravity = Gravity.RIGHT | Gravity.BOTTOM;//
            wmParams.x = width - 200;//设置初始位置，相对于左上角
            wmParams.y = height - 200;

            wmParams.width = 100;
            wmParams.height = 100;

            fsv = new FloatScanView(getApplicationContext());
            fsv.setImageResource(R.mipmap.ic_logo);
            fsv.setBackgroundColor(Color.GREEN);

            wm.addView(fsv, wmParams);
        } catch (Exception e) {
        }
    }


    private void openPermission() {
        //开启悬浮窗前先请求权限
        if ("Xiaomi".equals(Build.MANUFACTURER)) {//小米手机
            //            LogUtil.E("小米手机");
            requestPermission();
        } else if ("Meizu".equals(Build.MANUFACTURER)) {//魅族手机
            //            LogUtil.E("魅族手机");
            requestPermission();
        } else {//其他手机
            //            LogUtil.E("其他手机");
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    dialog = new AlertDialog.Builder(this)
                            .setTitle("悬浮窗权限管理")
                            .setMessage("是否去开启悬浮窗权限？")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //打开权限设置
                                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                                    startActivityForResult(intent, 12);
                                }
                            })
                            .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                } else {
                    createFloatView();
                }
            } else {
                createFloatView();
            }
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
//        wm.addView(fsv, wmParams);
    }


    @Override
    protected void onStop() {
//        if (fsv != null) {
//            wm.removeView(fsv);
//        }
        super.onStop();
    }

    //
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (fsv != null) {
//            wm.removeView(fsv);
//        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 11) {
//            if (isFloatWindowOpAllowed(this)) {//已经开启
//                createFloatView();
//            } else {
//                Toast.makeText(this, "开启悬浮窗失败", Toast.LENGTH_SHORT).show();
//            }
//        } else if (requestCode == 12) {
//            if (Build.VERSION.SDK_INT >= 23) {
//                if (!Settings.canDrawOverlays(BaseActivity.this)) {
//                    Toast.makeText(this, "权限授予失败,无法开启悬浮窗", Toast.LENGTH_SHORT).show();
//                } else {
//                    createFloatView();
//                }
//            }
//        }
    }

    /**
     * 判断悬浮窗权限
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean isFloatWindowOpAllowed(Context context) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            return checkOp(context, 24);  // AppOpsManager.OP_SYSTEM_ALERT_WINDOW
        } else {
            if ((context.getApplicationInfo().flags & 1 << 27) == 1 << 27) {
                return true;
            } else {
                return false;
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean checkOp(Context context, int op) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            try {
                Class<?> spClazz = Class.forName(manager.getClass().getName());
                Method method = manager.getClass().getDeclaredMethod("checkOp", int.class, int.class, String.class);
                int property = (Integer) method.invoke(manager, op,
                        Binder.getCallingUid(), context.getPackageName());
                Log.e("399", " property: " + property);

                if (AppOpsManager.MODE_ALLOWED == property) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.e("399", "Below API 19 cannot invoke!");
        }
        return false;
    }


    /**
     * 请求用户给予悬浮窗的权限
     */
    public void requestPermission() {
        if (isFloatWindowOpAllowed(this)) {//已经开启
            Log.e("399", "已经开启！");
        } else {
            dialog.show();
        }
    }


    /**
     * 打开权限设置界面
     */
    public void openSetting() {
        try {
            Intent localIntent = new Intent(
                    "miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter",
                    "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", getPackageName());
            startActivityForResult(localIntent, 11);
            //            LogUtil.E("启动小米悬浮窗设置界面");
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            Intent intent1 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent1.setData(uri);
            startActivityForResult(intent1, 11);
            //            LogUtil.E("启动悬浮窗界面");
        }
    }

}
