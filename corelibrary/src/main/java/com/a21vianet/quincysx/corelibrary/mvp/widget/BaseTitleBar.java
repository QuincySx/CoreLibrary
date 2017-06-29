package com.a21vianet.quincysx.corelibrary.mvp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a21vianet.quincysx.library.commoncore.utility.ConvertUtils;
import com.a21vianet.wangrongqiang.corelibrary.R;

/**
 * Created by wamg.rongqiang on 2017/1/6.
 * 通用title
 */

public class BaseTitleBar extends RelativeLayout {
    private Context mContext;
    private AttributeSet mAttributeSet;
    private int mDefStyleAttr;
    private ImageView mBtnLeft;
    private ImageView mBtnRight;
    private TextView mTvTitle;
    private RelativeLayout mRootView;

    public BaseTitleBar(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public BaseTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mAttributeSet = attrs;
        initView();
    }

    public BaseTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mAttributeSet = attrs;
        initView();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseTitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        mAttributeSet = attrs;
        mDefStyleAttr = defStyleAttr;
        initView();
    }

    private void initView() {
        inflate(mContext, R.layout.base_title_bar_layout, this);
        mBtnLeft = findView(R.id.btn_left);
        mBtnRight = findView(R.id.btn_right);
        mTvTitle = findView(R.id.tv_titlebar_text);
        mRootView = findView(R.id.root_title);

        TypedArray array = mContext.obtainStyledAttributes(mAttributeSet, R.styleable
                .BaseTitleBar, mDefStyleAttr, 0);
        int backDrawable = array.getResourceId(R.styleable.BaseTitleBar_backDrawable, 0);
        String titleText = array.getString(R.styleable.BaseTitleBar_TitleText);
        int titleTextResId = array.getResourceId(R.styleable.BaseTitleBar_TitleText, 0);
        int titleTextGravity = array.getInt(R.styleable.BaseTitleBar_TitleTextGravity, 4);
        boolean titleTextShow = array.getBoolean(R.styleable.BaseTitleBar_TitleShow, true);
        int titleTextClcor = array.getColor(R.styleable.BaseTitleBar_TitleTextColor, 0);
        int TitleTextSize = ConvertUtils.px2sp(array.getDimension(R.styleable
                .BaseTitleBar_TitleTextSize, 0));
        boolean btnLeftShow = array.getBoolean(R.styleable.BaseTitleBar_BtnLeftShow, true);
        int btnLeftDrawable = array.getResourceId(R.styleable.BaseTitleBar_BtnLeftDrawable, 0);
        boolean btnRightShow = array.getBoolean(R.styleable.BaseTitleBar_BtnRightShow, true);
        int btnRightDrawable = array.getResourceId(R.styleable.BaseTitleBar_BtnRightDrawable, 0);

        if (backDrawable != 0) {
            setImageDrawable(mRootView, backDrawable);
        }
        if (titleText != null) {
            mTvTitle.setText(titleText);
        }
        if (titleTextResId != 0) {
            mTvTitle.setText(titleTextResId);
        }
        switch (titleTextGravity) {
            case 0:
                mTvTitle.setGravity(Gravity.BOTTOM);
                break;
            case 1:
                mTvTitle.setGravity(Gravity.CENTER);
                break;
            case 2:
                mTvTitle.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            case 3:
                mTvTitle.setGravity(Gravity.CENTER_VERTICAL);
                break;
            case 4:
                mTvTitle.setGravity(Gravity.LEFT);
                break;
            case 5:
                mTvTitle.setGravity(Gravity.RIGHT);
                break;
            case 6:
                mTvTitle.setGravity(Gravity.TOP);
                break;
        }
        if (titleTextShow) {
            mTvTitle.setVisibility(VISIBLE);
        } else {
            mTvTitle.setVisibility(INVISIBLE);
        }
        if (titleTextClcor != 0) {
            mTvTitle.setTextColor(titleTextClcor);
        }
        if (TitleTextSize != 0) {
            mTvTitle.setTextSize(TitleTextSize);
        }
        if (btnLeftShow) {
            mBtnLeft.setVisibility(VISIBLE);
        } else {
            mBtnLeft.setVisibility(INVISIBLE);
        }
        if (btnLeftDrawable != 0) {
            setImageColor(mBtnLeft, btnLeftDrawable);
        }
        if (btnRightShow) {
            mBtnRight.setVisibility(VISIBLE);
        } else {
            mBtnRight.setVisibility(INVISIBLE);
        }
        if (btnRightDrawable != 0) {
            setImageColor(mBtnRight, btnRightDrawable);
        }
        array.recycle();

    }

    public void setTitleLeftOnClickListener(OnClickListener listener) {
        mBtnLeft.setOnClickListener(listener);
    }

    public void setTitleRightOnClickListener(OnClickListener listener) {
        mBtnLeft.setOnClickListener(listener);
    }

    public void setTitleTextOnClickListener(OnClickListener listener) {
        mBtnLeft.setOnClickListener(listener);
    }

    public void setBackground(@DrawableRes int resId) {
        setImageDrawable(mRootView, resId);
    }

    public void setBackgroundColor(@ColorRes int color) {
        setImageColor(mRootView, color);
    }

    public void setBackground(Drawable background) {
        mRootView.setBackground(background);
    }

    public void setBtnLeftDrawable(Drawable background) {
        mBtnLeft.setImageDrawable(background);
    }

    public void setBtnLeftDrawable(@DrawableRes int resId) {
        setImageDrawable(mBtnLeft, resId);
    }

    public void setBtnLeftColor(@ColorRes int resId) {
        setImageColor(mBtnLeft, resId);
    }

    /**
     * 设置隐藏显示
     *
     * @param visibility One of {@link #VISIBLE}, {@link #INVISIBLE}, or {@link #GONE}.
     */
    public void setBtnLeftVisibility(int visibility) {
        mBtnLeft.setVisibility(visibility);
    }

    public void setBtnRightDrawable(Drawable background) {
        mBtnRight.setImageDrawable(background);
    }

    public void setBtnRightDrawable(@DrawableRes int resId) {
        setImageDrawable(mBtnRight, resId);
    }

    public void setBtnRightColor(@ColorRes int resId) {
        setImageColor(mBtnRight, resId);
    }

    /**
     * 设置隐藏显示
     *
     * @param visibility One of {@link #VISIBLE}, {@link #INVISIBLE}, or {@link #GONE}.
     */
    public void setBtnRightVisibility(int visibility) {
        mBtnRight.setVisibility(visibility);
    }

    public void setTitleText(@StringRes int strId) {
        mTvTitle.setText(strId);
    }

    public void setTitleText(String str) {
        mTvTitle.setText(str);
    }

    /**
     * 设置隐藏显示
     *
     * @param visibility One of {@link #VISIBLE}, {@link #INVISIBLE}, or {@link #GONE}.
     */
    public void setTitleTextVisibility(int visibility) {
        mTvTitle.setVisibility(visibility);
    }

    public ImageView getBtnLeft() {
        return mBtnLeft;
    }

    public ImageView getBtnRight() {
        return mBtnRight;
    }

    public TextView getTitleText() {
        return mTvTitle;
    }

    private void setImageDrawable(View view, @DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setBackground(getResources().getDrawable(resId, null));
        } else {
            view.setBackground(getResources().getDrawable(resId));
        }
    }

    private void setImageColor(View view, int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setBackgroundColor(getResources().getColor(resId, null));
        } else {
            view.setBackgroundColor(getResources().getColor(resId));
        }
    }

    private <V> V findView(@IdRes int resId) {
        return (V) findViewById(resId);
    }
}
