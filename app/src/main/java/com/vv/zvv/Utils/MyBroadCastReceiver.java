package com.vv.zvv.Utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.vv.zvv.R;

/**
 * Created by zvv on 2017/2/21 15:14.
 * 代码中注册广播接收器
 */
public class MyBroadCastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        Log.d("vv", "动态广播接收到: " + message);
        Toast.makeText(context, "动态广播接收到: " + message, Toast.LENGTH_SHORT).show();

        //通知接收到的消息

        //1 消息构建者
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.image_bank1);
        String contentText = "账户*4038于02月21日18:46支出￥1800000.00元，可用余额378909.26元。支付宝快捷支付。【工商银行】";
        builder.setContentTitle("中国工商银行");
        builder.setContentText(contentText);

        //2 创建通知
        Notification notification = builder.build();

        //3 实例化消息管理者
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //4 消息管理者发送消息
        manager.notify(1, notification);

    }
}























