package com.a21vianet.quincysx.corelibrary.baseApp;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.a21vianet.quincysx.corelibrary.avtivityManager.ActivityCollector;
import com.a21vianet.quincysx.library.commoncore.utility.ContentUtility;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by wamg.rongqiang on 2017/1/4.
 */

public abstract class BaseAppliaction extends Application implements Application
        .ActivityLifecycleCallbacks {
    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(this);

        if (isOpenLeakCanaryTest()) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            LeakCanary.install(this);
        }
        
        MultiDex.install(this);
        ContentUtility.init(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    public void onTerminate() {
        //注销这个接口。
        unregisterActivityLifecycleCallbacks(this);
        super.onTerminate();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ActivityCollector.addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ActivityCollector.removeActivity(activity);
    }

    /**
     * Log标签
     *
     * @return
     */
    protected abstract String getLogTag();

    /**
     * 是否要开启LeakCanary内存泄漏检测
     *
     * @return true:开启内存泄漏检测
     * false:不开启内存泄漏检测
     */
    protected abstract boolean isOpenLeakCanaryTest();
}
