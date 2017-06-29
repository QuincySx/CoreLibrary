package com.a21vianet.quincysx.corelibrarysample;

import com.a21vianet.quincysx.library.net.http.NetWork;

/**
 * Created by wang.rongqiang on 2017/5/26.
 */

public class HttpUtility {
    private volatile static NetWork mWork;

    public static NetWork getInstance() {
        if (mWork == null) {
            synchronized (HttpUtility.class) {
                if (mWork == null) {
                    mWork = createNetWork();
                }
            }
        }
        return mWork;
    }

    private static NetWork createNetWork() {
        NetWork netWork = new NetWork.Builder()
                .setBaseUrl("http://gank.io/api/")
                .setHTTPLogLevel(NetWork.BODY)
                .setJsonConverter(new ExtGsonParse())
                .build();
        return netWork;
    }
}
