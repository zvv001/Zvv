package com.vv.zvv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 点击按钮实现加减输入框内的数值
 * 要求：
 */
public class EditTextActivity extends AppCompatActivity {
    private static final String TAG = "EditTextActivity";
    int minBuyAmount = 500;//起购金额
    int sumAmount = 300;//累加金额
    int limitAmount = 10000;//限购金额
    int canBuyAmount = 8500;//剩余可购金额

    @ViewInject(R.id.ed_amount)
    private EditText mEditText;

    @ViewInject(R.id.tv_show)
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        x.view().inject(this);

        initView();
        mEditText.setHint(minBuyAmount + "起投 " + sumAmount + "累加");
    }

    private void initView() {

        mEditText.setSelection(mEditText.length());//光标移到最后
        //监听
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mTextView.setText(s.toString());
                mEditText.setSelection(mEditText.length());//光标移到最后
            }
        });
    }

    @Event(value = {R.id.btn_down_EditTextActivity, R.id.btn_up_EditTextActivity}, type = View.OnClickListener.class)
    private void onCLick(View view) {
        int canBuy = Math.min(canBuyAmount, limitAmount);
        switch (view.getId()) {
            case R.id.btn_up_EditTextActivity:
                String currentAmount = mEditText.getText().toString();
                if ("".equals(currentAmount)) {
                    Log.d(TAG, "onCLick: " + currentAmount);
                    mEditText.setText(minBuyAmount + "");
                } else {
                    int currentInt = (int) Double.parseDouble(mEditText.getText().toString());
                    /*if (currentInt >= canBuy) {
                        Toast.makeText(this, "最大可够金额为：" + canBuy, Toast.LENGTH_SHORT).show();
                        int show = (canBuy / sumAmount) * sumAmount;
                        mEditText.setText(show + "");
                    } else */
                    if (currentInt > canBuy - sumAmount) {
                        Toast.makeText(this, "最大可够金额为：" + canBuy, Toast.LENGTH_SHORT).show();
                        int show = (canBuy / sumAmount) * sumAmount;
                        mEditText.setText(show + "");
                    } else if (currentInt < minBuyAmount) {
                        mEditText.setText(minBuyAmount + "");
                    } else {
                        int show = (currentInt / sumAmount) * sumAmount + sumAmount;
                        mEditText.setText(show + "");
                    }
                }
                break;
            case R.id.btn_down_EditTextActivity:
                String currentAmount01 = mEditText.getText().toString();
                if ("".equals(currentAmount01)) {
                    Toast.makeText(this, "请输入购买金额", Toast.LENGTH_SHORT).show();
                } else {
                    int currentInt = (int) Double.parseDouble(mEditText.getText().toString());
                    if (currentInt <= minBuyAmount) {
                        Toast.makeText(this, "起投金额为：" + minBuyAmount, Toast.LENGTH_SHORT).show();
                    } else if (currentInt <= minBuyAmount + sumAmount) {
                        mEditText.setText(minBuyAmount + "");
                    } else if (currentInt <= canBuy) {
                        if (currentInt == (currentInt / sumAmount) * sumAmount) {
                            int show = (currentInt / sumAmount) * sumAmount - sumAmount;
                            mEditText.setText(show + "");
                        } else {
                            int show = (currentInt / sumAmount) * sumAmount;
                            mEditText.setText(show + "");
                        }
                    } else if (currentInt > canBuy) {
                        Toast.makeText(this, "最大可够金额为：" + canBuy, Toast.LENGTH_SHORT).show();
                        int show = (canBuy / sumAmount) * sumAmount;
                        mEditText.setText(show + "");
                    }
                }
                break;
        }
    }
}



























