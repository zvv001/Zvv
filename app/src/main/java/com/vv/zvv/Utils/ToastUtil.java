package com.vv.zvv.Utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by zvv on 2017/3/10 14:29.
 */
public class ToastUtil extends Toast {
    Context mContext;
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public ToastUtil(Context context) {
        super(context);
        mContext = context;
    }

    public void ToastShort(String text){
        Toast.makeText(mContext,text,Toast.LENGTH_SHORT).show();
    }

    public void ToastLong(String text){
        Toast.makeText(mContext,text,Toast.LENGTH_LONG).show();
    }
}
