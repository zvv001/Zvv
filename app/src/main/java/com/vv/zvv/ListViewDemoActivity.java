package com.vv.zvv;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.vv.zvv.Adapters.MyListViewAdapter;
import com.vv.zvv.Utils.MyElasticListView;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView优化的讲解
 */
public class ListViewDemoActivity extends AppCompatActivity {

    private static final String TAG = "ListViewDemoActivity";

    @ViewInject(R.id.lv_ListViewDemoActivity)
    private MyElasticListView mListView;

    @ViewInject(R.id.iv_ListViewDemoActivity)
    private ImageView mImageView;

    private List<String> mContextList;
    MyListViewAdapter mAdapter;

    private int lastVisibleItem = 0;//记录上次第一个可视的item的ID

    //自动显示和隐藏布局的ListView
    @ViewInject(R.id.showTitle)
    RelativeLayout mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);

        x.view().inject(this);
        initData();
        initListener();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initData() {
        View headView = LayoutInflater.from(this).inflate(R.layout.item_listview_headview, null);
        mListView.addHeaderView(headView);

        String text[] = {"中国工商银行", "光大银行", "华夏银行", "建设银行", "农业银行", "平安银行", "浦发银行", "招商银行", "中国银行"};
        mContextList = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            mContextList.add(text[i]);
        }

        mAdapter = new MyListViewAdapter(this, mContextList);
        mListView.setAdapter(mAdapter);
        mListView.setEmptyView(findViewById(R.id.iv_showNOData));

        //遍历ListView
        for (int i = 0; i < mListView.getChildCount(); i++) {
            View view = mListView.getChildAt(i);
        }
        //获取可视区域内第一个item的id
        mListView.getFirstVisiblePosition();
        //获取可视区域内最后一个item的id
        mListView.getLastVisiblePosition();

    }

    private void initListener() {
        //OnTouchListener
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //触摸时操作
//                        Log.d(TAG, "ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //移动操作
//                        Log.d(TAG, "ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        //离开时操作
//                        Log.d(TAG, "ACTION_UP");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        //OnScrollListener
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        //正在滚动
                        Log.d(TAG, "SCROLL_STATE_TOUCH_SCROLL");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        //滑动停止
                        Log.d(TAG, "SCROLL_STATE_IDLE");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        //手指抛动，离开后ListView由于惯性会继续滑动
                        Log.d(TAG, "SCROLL_STATE_FLING");
                        break;
                }
            }

            /**
             *
             * @param view
             * @param firstVisibleItem 屏幕可见的第一的item
             * @param visibleItemCount 屏幕可见的item个数
             * @param totalItemCount 整理ListView的item个数
             */
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //在ListView滚动式会一直回调
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                    Log.d(TAG, "onScroll: 别扯了，已经到底了！！！");
                }

                //判断上滑还是下滑
                if (firstVisibleItem > lastVisibleItem) {
                    Log.d(TAG, "onScroll: 下滑");
                    showAnim(false);
                } else if (firstVisibleItem < lastVisibleItem) {
                    Log.d(TAG, "onScroll: 上滑");
                    showAnim(true);

                }
                lastVisibleItem = firstVisibleItem;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.setCurrentItem(position-1);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Event(value = {R.id.iv_ListViewDemoActivity}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_ListViewDemoActivity://测试按钮
//                mListView.setSelection(6);
//                mContextList.add("我是新添加的数据");
                mContextList.add(0, "我是新数据！");
                mAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    private void showAnim(boolean isShow) {
        Animator animator;
        if (isShow){
            animator = ObjectAnimator.ofFloat(mTitle,"translationY",mTitle.getTranslationY(),0);//show
        }else {
            animator = ObjectAnimator.ofFloat(mTitle,"translationY",mTitle.getTranslationY(),- mTitle.getHeight());//hide
        }

        animator.start();
    }

    private void start(){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_uri_browsers = Uri.parse("http://isomobile.com");
        intent.setData(content_uri_browsers);
        intent.setClassName("com.android.browser",  "com.android.browser.BrowserActivity");
        startActivity(intent);
    }
}



























