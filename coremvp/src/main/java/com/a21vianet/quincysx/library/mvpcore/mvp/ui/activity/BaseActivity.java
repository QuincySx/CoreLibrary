package com.a21vianet.quincysx.library.mvpcore.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.a21vianet.quincysx.library.mvpcore.mvp.BasePresenter;
import com.a21vianet.quincysx.library.mvpcore.mvp.BaseView;
import com.a21vianet.quincysx.library.mvpcore.mvp.ui.fragment.IFragmentOperation;
import com.a21vianet.quincysx.library.mvpcore.permission.PermissionActivity;
import com.a21vianet.quincysx.library.commoncore.common.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wamg.rongqiang on 2017/1/4.
 */

public abstract class BaseActivity<P extends BasePresenter> extends
        PermissionActivity implements IFragmentOperation, BaseView {
    protected P mPresenter;
    protected FragmentManager mFragmentManager;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mUnbinder = ButterKnife.bind(this);
        mFragmentManager = this.getSupportFragmentManager();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initView(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mUnbinder.unbind();
    }

    protected abstract void initView(Bundle state);

    protected P createPresenter() {
        return null;
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showPrompt(String msg) {
        showPrompt(msg, false);
    }

    @Override
    public void showPrompt(String msg, boolean b) {
        ToastUtils.showLongToast(msg);
    }

    @Override
    public void addFragment(int contentID, Fragment fragment) {
        mFragmentManager.beginTransaction().add(contentID, fragment).commit();
    }

    @Override
    public void removeFragment(Fragment fragment) {
        mFragmentManager.beginTransaction().remove(fragment).commit();
    }

    @Override
    public void hideFragment(Fragment fragment) {
        mFragmentManager.beginTransaction().hide(fragment).commit();
    }

    @Override
    public void showFragment(int contentID, Fragment fragment) {
        mFragmentManager.beginTransaction().show(fragment).commit();
    }

    @Override
    public void replaceFragment(int contentID, Fragment fragment) {
        mFragmentManager.beginTransaction().replace(contentID, fragment).commit();
    }
}
