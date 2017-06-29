package com.a21vianet.quincysx.corelibrarysample.Api;

import com.a21vianet.quincysx.corelibrarysample.bean.response.ConentInfoResponse;
import com.a21vianet.quincysx.corelibrarysample.bean.response.sss;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by wamg.rongqiang on 2017/1/10.
 */

public interface HelloApi {
    @GET("history/content/day/2016/05/11")
    Flowable<ConentInfoResponse> loadDate();

    @GET("history/content/day/2016/05/11")
    Flowable<sss> loadDatess();

    @Headers("Cache-Control:public,max-age=0") //并且在请求上setCache(true) 才可生效
    @GET("history/content/day/2016/05/11")
    Flowable<String> loadDateStr();
}
