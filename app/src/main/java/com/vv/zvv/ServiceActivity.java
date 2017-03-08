package com.vv.zvv;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.vv.zvv.Utils.MyBoundService;
import com.vv.zvv.Utils.MyService;

import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * Service
 * 服务
 * <p/>
 * 绑定Service与启动Service的使用区别：
 * 启动Service时，逻辑操作在Service中进行
 * 绑定Service时，通过获取到Service对象，调用其中的方法，实现逻辑处理
 */
public class ServiceActivity extends AppCompatActivity implements ServiceConnection {

    //自定义绑定服务
    MyBoundService boundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        x.view().inject(this);
        Log.d("TAG", "onCreate: ServiceActivity");
    }

    @Event(value = {R.id.btn_createService, R.id.btn_stopService,
            R.id.btn_bindService, R.id.btn_unBindService, R.id.btn_doSomething}, type = View.OnClickListener.class)
    private void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_createService://创建服务
                Intent intent = new Intent(this, MyService.class);
                intent.putExtra("order", "传递给Service的指令");
                //调用者和服务之间没有关联，调用者推出，服务仍可运行
                this.startService(intent);

                break;
            case R.id.btn_stopService://停止服务
                Intent intent01 = new Intent(this, MyService.class);
                stopService(intent01);
                break;
            //BoundService的操作
            case R.id.btn_bindService://绑定服务
                //第三步：将Activity粘在Service表面 绑定服务
                Intent intentBound = new Intent(this, MyBoundService.class);
                this.bindService(intentBound, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unBindService://解绑服务
                // 第五步，解除绑定Service
                // 取消绑定Service
                unbindService(this);
                break;
            case R.id.btn_doSomething://执行BoundService中的方法
                if (boundService != null) {
                    boundService.doSomething();
                } else {
                    Toast.makeText(this, "BoundService为null", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy: ServiceActivity");
    }


    // 第四步，Service连接成功的回调，通过胶水iBinder间接获取Service对象
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        //获取Service表面的胶水
        MyBoundService.MyBinder binder = (MyBoundService.MyBinder) service;
        //通过胶水获取Service
        boundService = binder.getService();
    }

    // 当Service断开连接的回调方法
    @Override
    public void onServiceDisconnected(ComponentName name) {
    }
}
