package com.vv.zvv;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.vv.zvv.Callback.OnModifyCallBack;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * xUtils注解的使用
 */
public class MainActivity extends Activity {
    //声明
    @ViewInject(R.id.tv_title_MainActivity)
    private TextView tv_title_MainActivity;

    @ViewInject(R.id.linearLayout_notification)
    private LinearLayout mLinearLayout;

    public static OnModifyCallBack mOnModifyCallBack;

    NotificationManager manager;

    // 通知的id，由我们自由定义
    private final int NOTIFICATION_ID = 1;
    // PendingIntent需要的请求码
    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //关联
        x.view().inject(this);

        //默认不显示 通知弹窗
        mLinearLayout.setVisibility(View.GONE);
        /*测试用*/
//        startActivity(new Intent(this, EditTextActivity.class));//直接跳转测试页
    }

    /**
     * 1. 方法必须私有限定,
     * 2. 方法参数形式必须和type对应的Listener接口一致.
     * 3. 注解参数value支持数组: value={id1, id2, id3}
     * 4. 其它参数说明见{@link "org.xutils.event.annotation.Event"}类的说明.
     **/
    @Event(value = {R.id.btn_xUtils_MainActivity, R.id.btn_test01_MainActivity,
            R.id.btn_commonNotification, R.id.btn_customNotification, R.id.btn_cancelNotification, R.id.btn_progressNotification,
            R.id.btn_AsyncTask_MainActivity, R.id.btn_dataPiker, R.id.btn_notification, R.id.btn_ProgressBar, R.id.btn_dataBase,
            R.id.btn_broadcast, R.id.btn_service, R.id.btn_CountDownTimer, R.id.btn_ScrollViewDemo, R.id.btn_EditTextDemo,
            R.id.btn_SwipeRefreshLayout, R.id.btn_ListView, R.id.btn_MyViewPager, R.id.btn_MyCustomView,R.id.btn_RetrofitActivity},
            type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            /*xUtils3.0的使用*/
            case R.id.btn_xUtils_MainActivity:
                mOnModifyCallBack = new OnModifyCallBack() {
                    @Override
                    public void Modify(String text) {
                        tv_title_MainActivity.setText(text);
                    }
                };
                startActivity(new Intent(this, XUtilsActivity.class));
//                overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
                break;
            /*存储*/
            case R.id.btn_test01_MainActivity:
                startActivity(new Intent(this, StorageActivity.class));
                break;
            /*异步任务*/
            case R.id.btn_AsyncTask_MainActivity:
                startActivity(new Intent(this, AsyncTaskActivity.class));
                break;
            /*时间选择器*/
            case R.id.btn_dataPiker:
                startActivity(new Intent(this, DataPikerActivity.class));
                break;
            /*--- Notification --- Start*/
            case R.id.btn_notification://发布通知
                mLinearLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_commonNotification://普通通知
                notifyCommonNotification();
                mLinearLayout.setVisibility(View.GONE);
                break;
            case R.id.btn_customNotification:
                notifyCustomNotification();
                mLinearLayout.setVisibility(View.GONE);
                break;
            case R.id.btn_progressNotification:
                progressNotification();
                break;
            /*--- Notification --- End*/
            case R.id.btn_cancelNotification://取消消息
                manager.cancel(NOTIFICATION_ID);
                break;

            /*ProgressBar*/
            case R.id.btn_ProgressBar:
                startActivity(new Intent(this, ProgressActivity.class));
                break;
            case R.id.btn_dataBase:
                startActivity(new Intent(this, DatabaseActivity.class));
                break;
            case R.id.btn_broadcast:
                startActivity(new Intent(this, BroadcastActivity.class));
                break;
            case R.id.btn_service://服务
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            case R.id.btn_CountDownTimer://倒计时
                startActivity(new Intent(this, CountDownTimerActivity.class));
                break;
            case R.id.btn_ScrollViewDemo://ScrollView
                startActivity(new Intent(this, ScrollViewActivity.class));
                break;
            case R.id.btn_EditTextDemo:
                startActivity(new Intent(this, EditTextActivity.class));
                break;
            case R.id.btn_SwipeRefreshLayout:
                startActivity(new Intent(this, SwipeRefreshLayoutActivity.class));
                break;
            case R.id.btn_ListView://ListView的优化
                startActivity(new Intent(this, ListViewDemoActivity.class));
                break;
            case R.id.btn_MyViewPager://自定义ViewPager
                startActivity(new Intent(this, MyViewPagerActivity.class));
                break;
            case R.id.btn_MyCustomView://自定义View展示
                startActivity(new Intent(this, MyCustomView.class));
                break;
            case R.id.btn_RetrofitActivity://retrofit
                startActivity(new Intent(this,RetrofitActivity.class));
                break;
            default:
                break;
        }
    }

    //发布普通通知
    private void notifyCommonNotification() {
        // Step1: 实例化"构建者"
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("ContentTitle")
                .setContentText("ContentText")
                .setSmallIcon(R.mipmap.ic_logo);
        // 可选
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo);
        builder.setLargeIcon(bitmap);
        builder.setContentInfo("ContentInfo");

        //通过"构建者"设置后续动作
        Intent intent = new Intent(this, DataPikerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE, intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);

        //Step2: 创建"通知"
        Notification notification = builder.build();

        // Step3: 实例化"管理者"
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Step4: 发布"通知"
        manager.notify(NOTIFICATION_ID, notification);//便于统一管理
    }

    //发布自定义通知
    private void notifyCustomNotification() {
        //Step1: 消息构建者
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_logo);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_custom);//自定义notification样式
        remoteViews.setTextViewText(R.id.iv_title, "自定义标题");
        remoteViews.setTextViewText(R.id.iv_context, "自定义内容");
        builder.setSmallIcon(R.mipmap.ic_logo);
        builder.setContent(remoteViews);

//        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
//        inboxStyle.setBuilder(builder);
//        builder.setStyle(inboxStyle);
        //Step2:消息管理者
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }

    //带进度条的通知
    private void progressNotification() {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_logo);
        builder.setContentTitle("下载");
        builder.setContentText("下载中...");
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i += 5) {
                    builder.setProgress(100, i, false);
                    manager.notify(NOTIFICATION_ID, builder.build());

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();

        builder.setContentText("下载完成!");
        manager.notify(NOTIFICATION_ID, builder.build());
        manager.cancel(NOTIFICATION_ID);
    }
}
































