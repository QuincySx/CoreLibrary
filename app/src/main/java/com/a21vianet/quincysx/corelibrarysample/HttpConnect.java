package com.a21vianet.quincysx.corelibrarysample;

import com.a21vianet.quincysx.library.net.http.NetWork;

/**
 * Created by quincysx on 2017/6/18.
 */

public class HttpConnect {
    private static class NetWorkClient {
        private static HttpConnect INSTANCE = new HttpConnect();
    }

    private static HttpConnect sHttpConnect;

    private NetWork mNetWork;

    private HttpConnect() {
        mNetWork = createHttpClient();
    }

    /**
     * 提供 NetWork 单例
     * @return
     */
    public static NetWork getInstance() {
        createSingleton();
        return sHttpConnect.mNetWork;
    }

    /**
     * 直接根据 Service API 创建 Service 进行网络请求
     * @param aClass
     * @param <S>
     * @return
     */
    public static <S> S newService(Class<S> aClass) {
        createSingleton();
        return sHttpConnect.mNetWork.createService(aClass);
    }

    /**
     * 根据不同业务 比如更换服务器 做出调整在进行访问 调整只会适用于当前一次访问，配置并不会进行保留
     * @return
     */
    public static NetWork.Builder newBuilder() {
        createSingleton();
        return sHttpConnect.mNetWork.newBuilder();
    }

    protected NetWork createHttpClient() {
        NetWork netWork = new NetWork.Builder()
                .setBaseUrl("http://gank.io/api/")
                .setHTTPLogLevel(NetWork.BODY)
                .setJsonConverter(new ExtGsonParse())
                .build();
        return netWork;
    }

    private static void createSingleton(){
        if (sHttpConnect == null) {
            synchronized (HttpConnect.class) {
                if (sHttpConnect == null) {
                    sHttpConnect = new HttpConnect();
                }
            }
        }
    }
}
