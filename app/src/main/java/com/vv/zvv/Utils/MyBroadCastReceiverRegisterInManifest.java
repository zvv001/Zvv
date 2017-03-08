package com.vv.zvv.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by zvv on 2017/2/21 15:37.
 */
public class MyBroadCastReceiverRegisterInManifest extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        Log.d("vv", "静态广播接收到: " + message);
        Toast.makeText(context, "静态广播接收到: " + message, Toast.LENGTH_SHORT).show();
    }
}
