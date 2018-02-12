package com.vv.zvv.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vv.zvv.R;

/**
 * description: 临时文件
 * author: zvv
 * date: 2017/12/18 9:35
 * update: 2017/12/18
 * version:
 */
public class DemoActivity extends Activity {

    private EditText et_number;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        init();
    }

    private void init() {
        et_number = (EditText) findViewById(R.id.et_number);
        tv_result = (TextView) findViewById(R.id.tv_result);

    }

    /*点击*/
    public void OnClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.btn_calculator:
                        print();
                default:
                    break;
            }
        }
    }

    private void print() {
        Wine a = new JNC();
        a.fun1();
    }

}

class Wine {
    public void fun1(){
        System.out.println("Wine 的Fun.....");
        fun2();
    }

    public void fun2(){
        System.out.println("Wine 的Fun2...");
    }
}

 class JNC extends Wine{

//     public void fun1(){
//         System.out.println("JNC 的 Fun1_01...");
//     }

    /**
     * @desc 子类重写父类方法
     *        父类中不存在该方法，向上转型后，父类是不能引用该方法的
     * @param a
     * @return void
     */
    public void fun1(String a){
        System.out.println("JNC 的 Fun1...");
        fun2();
    }

    /**
     * 子类重写父类方法
     * 指向子类的父类引用调用fun2时，必定是调用该方法
     */
    public void fun2(String nam){
        System.out.println("JNC 的Fun2...");
    }
}















