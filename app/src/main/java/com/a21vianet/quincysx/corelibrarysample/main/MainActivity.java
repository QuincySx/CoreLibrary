package com.a21vianet.quincysx.corelibrarysample.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.a21vianet.quincysx.corelibrary.mvp.BaseActivity;
import com.a21vianet.quincysx.corelibrarysample.R;
import com.a21vianet.quincysx.corelibrarysample.databinding.ActivityMainBinding;


/**
 * Created by wamg.rongqiang on 2017/1/10.
 */

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract
        .View {
    ActivityMainBinding mActivityMainBinding;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle state) {
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mActivityMainBinding.setMainPresenter(mPresenter);
    }

    @Override
    public MainContract.Presenter
    createPresenter() {
        return new MainPresenter();
    }


    @Override
    public void setData(MainViewModel
                                mainViewModel) {
        mActivityMainBinding.setMainViewModel(mainViewModel);
    }

    @Override
    public Context getViewContext() {
        return getApplicationContext();
    }

}
