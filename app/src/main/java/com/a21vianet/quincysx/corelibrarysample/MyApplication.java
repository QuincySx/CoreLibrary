package com.a21vianet.quincysx.corelibrarysample;

import com.a21vianet.quincysx.corelibrary.baseApp.BaseAppliaction;

/**
 * Created by wamg.rongqiang on 2017/1/10.
 */

public class MyApplication extends BaseAppliaction {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected String getLogTag() {
        return getPackageName();
    }

    @Override
    protected boolean isOpenLeakCanaryTest() {
        return true;
    }
}
