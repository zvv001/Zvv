package com.vv.zvv.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vv.zvv.R;

import org.xutils.common.util.LogUtil;

/**
 * description: 临时文件
 * author: zvv
 * date: 2017/12/18 9:35
 * update: 2017/12/18
 * version:
 */
public class DemoActivity extends AppCompatActivity {
    //成员变量
    private String strMember;//null
    int intMember;//0
    double doubleMember;//0.0
    boolean booleanMember;//false


    //构造方法
    public DemoActivity(String strMember) {
        this.strMember = strMember;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        init();
    }

    private void init() {
        //局部变量 必须赋初值，否则使用时系统会报错
        String strLocal;
        int intLocal;

        // DemoActivity.init(L:35): 打印数据: ......
        LogUtil.d("打印数据：strMember：" + strMember
                + "strLocal: " + intMember
                + "doubleMember: " + doubleMember
                + "booleanMember: " + booleanMember

        );//

    }


    static void getAmountStatic() {

    }

    void getSumNoneStatic() {
    }
}




















