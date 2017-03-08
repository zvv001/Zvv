package com.vv.zvv.Utils;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zvv on 2017/2/23 10:53.
 * 被绑定的服务
 */
public class MyBoundService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", "onCreate: MyBoundService");
    }

    // 第二步，将胶水对象凃在Service表面，等待粘合
    // 当启动组件（Activity）与Service绑定时，执行该方法
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("TAG", "onBind: MyBoundService");
        return new MyBinder();
    }

    // 当Service解除绑定时的回调方法
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("TAG", "onUnbind: MyBoundService");
        return super.onUnbind(intent);
    }

    // 当Service被销毁时的回调方法
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy: MyBoundService");
    }

    //第一步: 创建胶水类
    // 胶水类返回Service
    public class MyBinder extends Binder {
        public MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    public void doSomething(){
        Log.d("TAG", "doSomething: BoundService中的方法被执行了！");
    }
}
