package com.vv.zvv.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.vv.zvv.R;
import com.vv.zvv.Utils.MyBroadCastReceiver;
import com.vv.zvv.Utils.MyBroadCastReceiverRegisterInManifest;

import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * 用于发送接收广播
 */
public class BroadcastActivity extends AppCompatActivity {

    //广播接收器_动态
    private MyBroadCastReceiver receiver;
    private MyBroadCastReceiverRegisterInManifest mReceiverInManifest;
    //是否注册
    private boolean isRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brodacast);

        x.view().inject(this);
    }

    @Event(value = {R.id.btn_registerBroadcast, R.id.btn_sendBroadcast, R.id.btn_unregisterBroadcast, R.id.btn_registerBroadcast}, type = View.OnClickListener.class)
    private void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_registerBroadcast://动态注册广播
                if (isRegister) {
                    Toast.makeText(this, "广播已注册无需注册", Toast.LENGTH_SHORT).show();
                } else {
                    //实例自定义广播接收器
                    receiver = new MyBroadCastReceiver();
                    //实例意图过滤器
                    IntentFilter filter = new IntentFilter();
                    //添加过滤条件
                    filter.addAction("com.vv.zvv_sendBroadcast");
                    //注册广播接收器
                    registerReceiver(receiver, filter);
                    isRegister = true;
                }
                break;
            case R.id.btn_sendBroadcast://发送广播
                Intent intent = new Intent();
                intent.setAction("com.vv.zvv_sendBroadcast");
                intent.putExtra("message", "Come On!");
                sendBroadcast(intent);
                break;
            case R.id.btn_unregisterBroadcast://取消广播接收器
                if (isRegister) {
                    unregisterReceiver(receiver);
                    isRegister = false;
                } else {
                    Toast.makeText(this, "无广播可取消注册", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_receiverBroadcast:
                mReceiverInManifest = new MyBroadCastReceiverRegisterInManifest();

                break;
            default:
                break;
        }

    }
}
