package com.vv.zvv.Utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zvv on 2017/2/23 09:59.
 * 需要在Manifest.xml中注册
 */
public class MyService extends Service {

    // 使用startService方式启动时，该方法不适用，直接返回null
    // 绑定Service。
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //创建
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", " = onCreate: MyService");
    }

    /**
     * 当Service创建完毕，开始运行，执行该方法(startedService有该方法，BoundService没有该方法)
     * <p/>
     * intent：启动Service使用的意图
     * flags：返回值标记，决定了Service是否为粘性
     * <p/>
     * START_STICKY：代表Service为粘性，当被异常杀死，会自动重新启动，此时intent为null
     * START_NOT_STICKY：代表Service为非粘性，当被异常杀死，不会重新启动。
     * START_REDELIVER_INTENT：代表Service为粘性，当被异常杀死时，会自动重新启动，但intent不为null
     * <p/>
     * startId：代表Service的id
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG", " = onStartCommand: MyService");
        if (flags == START_STICKY) {
            Log.d("TAG", "START_STICKY ");
        } else if (flags == START_NOT_STICKY) {
            Log.d("TAG", "START_NOT_STICKY ");
        } else if (flags == START_REDELIVER_INTENT) {
            Log.d("TAG", "START_REDELIVER_INTENT ");
        }
        if (intent != null) {
            String order = intent.getStringExtra("order");
            Log.d("TAG", "--- " + order);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    //销毁
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", " = onDestroy: MyService");
    }
}
