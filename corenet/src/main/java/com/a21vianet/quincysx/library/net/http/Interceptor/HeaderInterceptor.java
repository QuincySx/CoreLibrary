package com.a21vianet.quincysx.library.net.http.Interceptor;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wamg.rongqiang on 2017/1/13.
 * 请求头添加
 */

public class HeaderInterceptor implements Interceptor {
    private Map<String, String> mHeaderMap;

    public void setHeaderMap(@NonNull Map<String, String> headerMap) {
        mHeaderMap = headerMap;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        if (mHeaderMap != null) {
            for (String key : mHeaderMap.keySet()) {
                requestBuilder.addHeader(key, mHeaderMap.get(key) == null ? "" : mHeaderMap.get
                        (key).toString());
            }
        }
        return chain.proceed(requestBuilder.build());
    }
}
