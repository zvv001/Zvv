package com.vv.zvv.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vv.zvv.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 点击按钮实现加减输入框内的数值
 * 要求：
 */
public class EditTextActivity extends AppCompatActivity {
    private static final String TAG = "EditTextActivity";
    int minBuyAmount = 1;//起购金额
    int sumAmount = 5;//累加金额
    int limitAmount = 10000;//限购金额
    int canBuyAmount = 33;//剩余可购金额

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
        switch (view.getId()) {
            case R.id.btn_up_EditTextActivity:
                String currentAmount = mEditText.getText().toString();
                clickUpButton(currentAmount);
                break;
            case R.id.btn_down_EditTextActivity:
                String currentAmount01 = mEditText.getText().toString();
                clickDownButton(currentAmount01);
                break;
        }
    }

    //加号按钮
    private void clickUpButton(String currentAmount) {
        int canBuy = Math.min(canBuyAmount, limitAmount);
        if ("".equals(currentAmount)) {
            mEditText.setText(minBuyAmount + "");
        } else {
            int currentInt = (int) Double.parseDouble(mEditText.getText().toString());
            if (canBuy < minBuyAmount) {
                Toast.makeText(this, "剩余金额小于起投金额", Toast.LENGTH_SHORT).show();
            } else if (canBuy < minBuyAmount + sumAmount) {
                int show = minBuyAmount + sumAmount;
                mEditText.setText(show + "");
            } else if (minBuyAmount + sumAmount <= canBuy) {
                if (currentInt < minBuyAmount) {
                    mEditText.setText(minBuyAmount + "");
                } else if (currentInt < minBuyAmount + sumAmount) {
                    int show = minBuyAmount + sumAmount;
                    mEditText.setText(show + "");
                } else if (currentInt <= canBuy) {
                    int show = ((currentInt - minBuyAmount) / sumAmount + 1) * sumAmount + minBuyAmount;
                    if (show > canBuy) {
                        int show01 = show - sumAmount;
                        mEditText.setText(show01 + "");
                        Toast.makeText(this, "最大可购金额: " + canBuy, Toast.LENGTH_SHORT).show();
                    } else {
                        mEditText.setText(show + "");
                    }
                } else {//即 currentInt > canBuy
                    int show = ((canBuy - minBuyAmount) / sumAmount) * sumAmount + minBuyAmount;
                    mEditText.setText(show + "");
                    Toast.makeText(this, "最大可购金额: " + canBuy, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    //减号按钮
    private void clickDownButton(String currentAmount) {
        int canBuy = Math.min(canBuyAmount, limitAmount);
        if ("".equals(currentAmount)) {
            Toast.makeText(this, "请输入购买金额", Toast.LENGTH_SHORT).show();
        } else {
            int currentInt = (int) Double.parseDouble(mEditText.getText().toString());
            if (canBuy < minBuyAmount) {
                Toast.makeText(this, "剩余金额小于起投金额", Toast.LENGTH_SHORT).show();
            } else if (canBuy <= minBuyAmount + sumAmount) {
                if (currentInt <= minBuyAmount) {
                    mEditText.setText(minBuyAmount + "");
                    Toast.makeText(this, "最小起投金额: " + minBuyAmount, Toast.LENGTH_SHORT).show();
                } else if (currentInt <= minBuyAmount + sumAmount) {
                    mEditText.setText(minBuyAmount + "");
                } else {
                    int show = minBuyAmount + sumAmount;
                    mEditText.setText(show + "");
                    Toast.makeText(this, "可投金额: " + show, Toast.LENGTH_SHORT).show();
                }
                mEditText.setText(minBuyAmount + "");
            } else {//即minBuyAmount + sumAmount > canBuy)
                if (currentInt <= minBuyAmount) {
                    mEditText.setText(minBuyAmount + "");
                    Toast.makeText(this, "最小起投金额: " + minBuyAmount, Toast.LENGTH_SHORT).show();
                } else if (currentInt <= minBuyAmount + sumAmount) {
                    mEditText.setText(minBuyAmount + "");
                } else if (currentInt <= canBuy) {
                    if ((currentInt - minBuyAmount) % sumAmount == 0) {
                        int show = currentInt - sumAmount;
                        mEditText.setText(show + "");
                    } else {
                        int show = ((currentInt - minBuyAmount) / sumAmount) * sumAmount + minBuyAmount;
                        mEditText.setText(show + "");
                    }
                } else {//即currentInt<=canBuy
                    if ((canBuy - minBuyAmount) % sumAmount == 0) {
                        int show = canBuy;
                        mEditText.setText(show + "");
                    } else {
                        int show = ((canBuy - minBuyAmount) / sumAmount) * sumAmount + minBuyAmount;
                        mEditText.setText(show + "");
                    }
                    Toast.makeText(this, "可投金额: " + canBuy, Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}



























