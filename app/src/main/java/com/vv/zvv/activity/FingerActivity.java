package com.vv.zvv.activity;

import android.Manifest;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.vv.zvv.R;
import com.vv.zvv.Utils.ToastUtil;

public class FingerActivity extends Activity {

    FingerprintManager manager;
    KeyguardManager mKeyManager;
    private final static int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 0;
    private final static String TAG = "finger_log";

    CancellationSignal mCancellationSignal;
    FingerprintManager.AuthenticationCallback mSelfCancelled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger);

        init();
    }

    private void init() {
        manager = (FingerprintManager) this.getSystemService(Context.FINGERPRINT_SERVICE);
        mKeyManager = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mCancellationSignal = new CancellationSignal();

            mSelfCancelled = new FingerprintManager.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    //但多次指纹密码验证错误后，进入此方法；并且，不能短时间内调用指纹验证
                    Toast.makeText(FingerActivity.this, errString, Toast.LENGTH_SHORT).show();
                    showAuthenticationScreen();
                }

                @Override
                public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

                    Toast.makeText(FingerActivity.this, helpString, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

                    Toast.makeText(FingerActivity.this, "指纹识别成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationFailed() {
                    Toast.makeText(FingerActivity.this, "指纹识别失败", Toast.LENGTH_SHORT).show();
                }
            };
        }
    }


    public boolean isFinger() {
        //android studio 上，没有这个会报错
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "没有指纹识别权限", Toast.LENGTH_SHORT).show();
            return false;
        }
        Log(TAG, "有指纹权限");
        //判断硬件是否支持指纹识别
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!manager.isHardwareDetected()) {
                Toast.makeText(this, "没有指纹识别模块", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        Log(TAG, "有指纹模块");
        //判断 是否开启锁屏密码

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (!mKeyManager.isKeyguardSecure()) {
                Toast.makeText(this, "没有开启锁屏密码", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        Log(TAG, "已开启锁屏密码");
        //判断是否有指纹录入
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!manager.hasEnrolledFingerprints()) {
                Toast.makeText(this, "没有录入指纹", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        Log(TAG, "已录入指纹");

        return true;
    }


    private void Log(String tag, String msg) {
        Log.d(tag, msg);
    }

    /**
     * 锁屏密码
     */
    private void showAuthenticationScreen() {
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            intent = mKeyManager.createConfirmDeviceCredentialIntent("finger", "测试指纹识别");
        }
        if (intent != null) {
            startActivityForResult(intent, REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS);
        }
    }

    public void startListening(FingerprintManager.CryptoObject cryptoObject) {
        //android studio 上，没有这个会报错
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "没有指纹识别权限", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            manager.authenticate(cryptoObject, mCancellationSignal, 0, mSelfCancelled, null);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            // Challenge completed, proceed with using cipher
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "识别成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "识别失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_useFinger://使用指纹
                ToastUtil.showShortToast(this, Build.VERSION.SDK_INT+"");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (isFinger()) {
                        Toast.makeText(FingerActivity.this, "请进行指纹识别", Toast.LENGTH_LONG).show();
                        Log(TAG, "keyi");
                        startListening(null);
                    }
                } else {
                    ToastUtil.showShortToast(this, "SDK_INT<23");
                }
                break;
            default:
                break;
        }
    }
}
