package com.a21vianet.quincysx.library.mvpcore.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a21vianet.quincysx.library.commoncore.common.ToastUtils;
import com.a21vianet.quincysx.library.mvpcore.mvp.BasePresenter;
import com.a21vianet.quincysx.library.mvpcore.mvp.BaseView;
import com.a21vianet.quincysx.library.mvpcore.mvp.ui.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wamg.rongqiang on 2017/1/6.
 */

public abstract class BaseFragment<P extends BasePresenter> extends
        Fragment implements BaseView {
    protected P mPresenter;
    protected View mView;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutResId(), container, false);
        mUnbinder = ButterKnife.bind(this, mView);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initView(savedInstanceState);
        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mUnbinder.unbind();
    }

    @Override
    public void showProgress() {
        ((BaseActivity) getActivity()).showProgress();
    }

    @Override
    public void dismissProgress() {
        ((BaseActivity) getActivity()).dismissProgress();
    }

    @Override
    public void showPrompt(String msg) {
        showPrompt(msg, false);
    }

    @Override
    public void showPrompt(String msg, boolean b) {
        ToastUtils.showLongToast(msg);
    }

    protected abstract void initView(Bundle state);

    protected abstract P createPresenter();

    protected abstract int getLayoutResId();
}
