package com.a21vianet.quincysx.corelibrarysample.main;

import android.util.Log;

import com.a21vianet.quincysx.corelibrarysample.Api.HelloApi;
import com.a21vianet.quincysx.corelibrarysample.HttpConnect;
import com.a21vianet.quincysx.corelibrarysample.HttpUtility;
import com.a21vianet.quincysx.corelibrarysample.bean.response.ConentInfoResponse;
import com.a21vianet.quincysx.corelibrarysample.bean.response.sss;
import com.a21vianet.quincysx.library.net.http.NetWork;
import com.a21vianet.quincysx.library.net.rxTransformer.HttpTransformer;
import com.a21vianet.quincysx.library.net.subscriber.HttpSubscriber;

/**
 * Created by wamg.rongqiang on 2017/1/10.
 */

public class MainModel implements MainContract.Model {
    NetWork build = HttpUtility.getInstance().newBuilder().closeNetCache()
            .setBaseUrl("http://172.16.133.60:8080").build();

    @Override
    public void getLoadss(HttpSubscriber<sss> subscriber) {
        if (build == HttpUtility.getInstance()) {
            Log.e("==========", "getLoad: 确认了");
        }
        HttpConnect.newService(HelloApi.class).loadDatess().compose(HttpTransformer.<sss>io2MainSchedulers()).subscribe(subscriber);
    }

    @Override
    public void getLoad(HttpSubscriber<ConentInfoResponse> subscriber) {
        if (build == HttpUtility.getInstance()) {
            Log.e("==========", "getLoad: 确认了");
        }
        HttpConnect.newService(HelloApi.class).loadDate().compose(HttpTransformer.<ConentInfoResponse>io2MainSchedulers()).subscribe(subscriber);
    }

    @Override
    public void getLoadStr(HttpSubscriber<String> subscriber) {
        if (build == HttpUtility.getInstance()) {
            Log.e("==========", "getLoad: 确认了");
        }
        HttpConnect.newService(HelloApi.class).loadDateStr().compose(HttpTransformer.<String>io2MainSchedulers()).subscribe(subscriber);
    }
}
