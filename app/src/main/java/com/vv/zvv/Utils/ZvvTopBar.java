package com.vv.zvv.Utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vv.zvv.R;

/**
 * Created by zvv on 2017/3/10 11:22.
 */
public class ZvvTopBar extends RelativeLayout {
    //标题
    private TextView mTitleTextView;
    private String mTitle;
    private int mTitleTextColor;
    private float mTitleTextSize;
    private Drawable mTitleBackGround;

    //左边按钮
    private Button mLeftButton;
    private String mLeftTitle;
    private int mLeftTextColor;
    private float mLeftTextSize;
    private Drawable mLeftBackGround;

    //右边按钮
    private Button mRightButton;
    private String mRightTitle;
    private int mRightTextColor;
    private float mRightTextSize;
    private Drawable mRightBackGround;

    //
    private RelativeLayout.LayoutParams mLayoutParamsTitle;
    private RelativeLayout.LayoutParams mLayoutParamsLeft;
    private RelativeLayout.LayoutParams mLayoutParamsRight;

    //
    TopBarClickListener mTopBarClickListener;

    public ZvvTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ZvvTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ZvvTopBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //将 attrs.xml中自定义的declare-styleable的所有属性储存到 TypeArray中
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZvvTopBar);
        //从TypeArray中取出对应值来为要设置的属性赋值
        mTitle = typedArray.getString(R.styleable.ZvvTopBar_titleZvvTopBar);
        mTitleTextColor = typedArray.getColor(R.styleable.ZvvTopBar_titleTextColorZvvTopBar, 0);
        mTitleTextSize = typedArray.getDimension(R.styleable.ZvvTopBar_titleTextSizeZvvTopBar, 10);
        mTitleBackGround = typedArray.getDrawable(R.styleable.ZvvTopBar_titleBackGroundZvvTopBar);

        mLeftTitle = typedArray.getString(R.styleable.ZvvTopBar_leftTitleZvvTopBar);
        mLeftTextColor = typedArray.getColor(R.styleable.ZvvTopBar_leftTextColorZvvTopBar, 0);
        mLeftTextSize = typedArray.getDimension(R.styleable.ZvvTopBar_leftTextSizeZvvTopBar, 10);
        mLeftBackGround = typedArray.getDrawable(R.styleable.ZvvTopBar_leftBackGroundZvvTopBar);

        mRightTitle = typedArray.getString(R.styleable.ZvvTopBar_rightTitleZvvTopBar);
        mRightTextColor = typedArray.getColor(R.styleable.ZvvTopBar_rightTextColorZvvTopBar, 0);
        mRightTextSize = typedArray.getDimension(R.styleable.ZvvTopBar_rightTextSizeZvvTopBar, 10);
        mRightBackGround = typedArray.getDrawable(R.styleable.ZvvTopBar_rightBackGroundZvvTopBar);

        //避免创新创建错误
        typedArray.recycle();

        mTitleTextView = new TextView(context);
        mLeftButton = new Button(context);
        mRightButton = new Button(context);

        //赋值
        mTitleTextView.setText(mTitle);
        mTitleTextView.setTextColor(mTitleTextColor);
        mTitleTextView.setTextSize(mTitleTextSize);

        mLeftButton.setText(mLeftTitle);
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setTextSize(mLeftTextSize);

        mRightButton.setText(mRightTitle);
        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setTextSize(mRightTextSize);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mTitleTextView.setBackground(mTitleBackGround);
            mLeftButton.setBackground(mLeftBackGround);
            mRightButton.setBackground(mRightBackGround);
        }

        //为组件元素设置相应的布局元素
        mLayoutParamsTitle = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mLayoutParamsTitle.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        //添加到ViewGroup
        addView(mTitleTextView, mLayoutParamsTitle);

        mLayoutParamsLeft = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLayoutParamsLeft.addRule(ALIGN_PARENT_LEFT, TRUE);
        addView(mLeftButton, mLayoutParamsLeft);

        mLayoutParamsRight = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLayoutParamsRight.addRule(ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mLayoutParamsRight);


        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopBarClickListener.LeftClick();
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopBarClickListener.RightClick();
            }
        });
    }

    //自定义接口
    public interface TopBarClickListener {
        void LeftClick();

        void RightClick();
    }

    //暴露方法给调用者注册接口回调
    public void setOnTopBarClickListener(TopBarClickListener topBarClickListener) {
        this.mTopBarClickListener = topBarClickListener;
    }

    public void setButtonVisible(boolean isLeft, boolean isShow) {
        if (isLeft) {
            if (isShow) {
                mLeftButton.setVisibility(VISIBLE);
            } else {
                mLeftButton.setVisibility(INVISIBLE);
            }
        } else {
            if (isShow) {
                mRightButton.setVisibility(VISIBLE);
            } else {
                mRightButton.setVisibility(INVISIBLE);
            }
        }
    }


}
