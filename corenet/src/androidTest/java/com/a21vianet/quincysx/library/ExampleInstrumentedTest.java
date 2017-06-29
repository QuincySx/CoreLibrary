package com.a21vianet.quincysx.library;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.github.pwittchen.reactivenetwork.library.rx2.ConnectivityPredicate;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        //判断网络连接示例
        ReactiveNetwork.observeNetworkConnectivity(appContext)
                .subscribeOn(Schedulers.io())
                //判断网络连接状态
                .filter(ConnectivityPredicate.hasState(NetworkInfo.State.CONNECTED))
                //判断网络连接类型
                .filter(ConnectivityPredicate.hasType(ConnectivityManager.TYPE_WIFI))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Connectivity>() {
                    @Override
                    public void accept(@NonNull Connectivity connectivity) throws Exception {
                        //获得具体状态
                        connectivity.getState();
                        //获得具体连接类型
                        connectivity.getType();
                        // do something
                    }
                });


    }
}
