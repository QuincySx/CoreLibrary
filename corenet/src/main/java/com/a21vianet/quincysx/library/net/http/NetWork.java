package com.a21vianet.quincysx.library.net.http;

import android.content.Context;
import android.support.annotation.IntDef;

import com.a21vianet.quincysx.library.net.http.Interceptor.HeaderInterceptor;
import com.a21vianet.quincysx.library.net.http.cache.CacheInterceptor;
import com.a21vianet.quincysx.library.net.http.cache.NetCache;
import com.a21vianet.quincysx.library.net.http.ssl.SSLParams;
import com.a21vianet.quincysx.library.net.converter.CommonConverterFactory;
import com.a21vianet.quincysx.library.net.converter.jsonparse.IJsonParse;
import com.a21vianet.quincysx.library.net.converter.jsonparse.StringParse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by quincysx on 17-5-7.
 */

public class NetWork {
    public static final int NONE = 0X000001;
    public static final int HEADERS = 0X000002;
    public static final int BODY = 0X000003;

    /**
     * 缓存
     */
    private NetCache mNetCache;

    /**
     * BaseUrl
     */
    private StringBuilder BaseUrl;

    /**
     * 日志打印级别
     */
    @HTTPLogLevel
    private int mHTTPLogLevel;

    /**
     * 链接超时时长判断
     */
    private TimeKey mConnectTimeout;

    /**
     * 拦截器列表
     */
    private List<Interceptor> mInterceptorList;

    /**
     * 请求头列表
     */
    private Map<String, String> mHeaderMap = new HashMap<>();

    /**
     * 解析库
     */
    private IJsonParse mJsonParse;

    /**
     * SSL证书
     */
    private SSLParams mSSLParams;

    @IntDef({NONE, HEADERS, BODY})
    public @interface HTTPLogLevel {
    }

    private Retrofit mRetrofit;

    public NetWork(Builder builder) {
        this.mNetCache = builder.mNetCache;
        this.BaseUrl = builder.BaseUrl;
        this.mHTTPLogLevel = builder.mHTTPLogLevel;
        this.mConnectTimeout = builder.mConnectTimeout;
        this.mInterceptorList = builder.mInterceptorList;
        this.mHeaderMap = builder.mHeaderMap;
        this.mJsonParse = builder.mJsonParse;
        this.mSSLParams = builder.mSSLParams;

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        //设置超时时间
        okHttpBuilder
                .connectTimeout(mConnectTimeout.getValue(), mConnectTimeout.getTimeUnit())
                .writeTimeout(mConnectTimeout.getValue(), mConnectTimeout.getTimeUnit())
                .readTimeout(mConnectTimeout.getValue(), mConnectTimeout.getTimeUnit());

        //添加请求头
        if (mHeaderMap != null && mHeaderMap.size() > 0) {
            HeaderInterceptor interceptor = new HeaderInterceptor();
            interceptor.setHeaderMap(mHeaderMap);
            okHttpBuilder.addInterceptor(interceptor);
        }

        //设置拦截器
        if (mInterceptorList != null && mInterceptorList.size() > 0) {
            for (Interceptor i : mInterceptorList) {
                okHttpBuilder.addInterceptor(i);
            }
        }

        if (mNetCache.isEnable()) {
            //缓存拦截器
            CacheInterceptor cacheInterceptor = new CacheInterceptor();
            //缓存
            Cache cache = new Cache(mNetCache.getFile(), mNetCache.getSize());
            okHttpBuilder
                    .addNetworkInterceptor(cacheInterceptor)
                    .cache(cache)
                    .addInterceptor(cacheInterceptor);
        }

        //设置信任非CA颁发的SSL证书
        if (mSSLParams != null && !mSSLParams.isNull()) {
            okHttpBuilder.sslSocketFactory(mSSLParams.sSLSocketFactory,
                    mSSLParams.trustManager);
        }

        //设置网络日志监听
        if (mHTTPLogLevel != NONE) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            switch (mHTTPLogLevel) {
                case BODY:
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    break;
                case HEADERS:
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
                    break;
                default:
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            }
            okHttpBuilder.addInterceptor(loggingInterceptor);
        }

        // 设置Json解析 首要添加String解析类
        retrofitBuilder.addConverterFactory(CommonConverterFactory.create(new StringParse()));
        if (mJsonParse != null) {
            retrofitBuilder.addConverterFactory(CommonConverterFactory.create(mJsonParse));
        } else {
            retrofitBuilder.addConverterFactory(CommonConverterFactory.create());
        }

        //设置BaseUrl
        retrofitBuilder.baseUrl(BaseUrl.toString());

        //与OkHttp关联
        retrofitBuilder.client(okHttpBuilder.build());

        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        mRetrofit = retrofitBuilder.build();
        System.gc();
    }

    public <S> S createService(Class<S> aClass) {
        //获取OkHttp实例
        return mRetrofit.create(aClass);
    }

    /**
     * 创建一个新的对象
     *
     * @return
     */
    public Builder newBuilder() {
        return new Builder(this);
    }

    /**
     * 构建器
     */
    public static final class Builder {
        private NetCache mNetCache = new NetCache(null, false);

        private StringBuilder BaseUrl = new StringBuilder();

        @HTTPLogLevel
        private int mHTTPLogLevel = NONE;

        private TimeKey mConnectTimeout = new TimeKey();

        private List<Interceptor> mInterceptorList = new ArrayList<>();

        private Map<String, String> mHeaderMap = new HashMap<>();

        private IJsonParse mJsonParse;

        private SSLParams mSSLParams;

        public Builder() {

        }

        private Builder(NetWork netWork) {
            this.mNetCache = netWork.mNetCache;
            this.BaseUrl = netWork.BaseUrl;
            this.mHTTPLogLevel = netWork.mHTTPLogLevel;
            this.mConnectTimeout = netWork.mConnectTimeout;
            this.mInterceptorList = netWork.mInterceptorList;
            this.mHeaderMap = netWork.mHeaderMap;
            this.mJsonParse = netWork.mJsonParse;
            this.mSSLParams = netWork.mSSLParams;
        }

        /**
         * 设置是否可以缓存
         *
         * @param netCache 缓存对象
         */
        public Builder setNetCache(NetCache netCache) {
            mNetCache = netCache;
            return this;
        }

        /**
         * @param context
         * @param enable  是否开始缓存
         */
        public Builder setNetCache(Context context, boolean enable) {
            mNetCache = new NetCache(context, enable);
            return this;
        }

        /**
         * @param context
         * @param enable  是否开始缓存
         * @param size    缓存内容最大
         */
        public Builder setNetCache(Context context, boolean enable, long size) {
            mNetCache = new NetCache(context, enable, size);
            return this;
        }

        /**
         * @param context
         * @param chchefile 缓存目录
         * @param enable    是否开始缓存
         * @param size      缓存内容最大
         */
        public Builder setNetCache(Context context, File chchefile, boolean enable, long size) {
            mNetCache = new NetCache(context, chchefile, enable, size);
            return this;
        }

        /**
         * 关闭缓存
         *
         * @return
         */
        public Builder closeNetCache() {
            mNetCache = new NetCache(null, null, false, 0);
            return this;
        }


        /**
         * 设置BaseUrl
         *
         * @param baseUrl
         */
        public Builder setBaseUrl(String baseUrl) {
            BaseUrl = new StringBuilder(baseUrl);
            return this;
        }

        /**
         * 设置网络请求打印级别
         *
         * @param level
         */
        public Builder setHTTPLogLevel(@HTTPLogLevel int level) {
            mHTTPLogLevel = level;
            return this;
        }

        /**
         * 设置网络请求超时时间
         *
         * @param connectTimeout
         */
        public Builder setConnectTimeout(TimeKey connectTimeout) {
            mConnectTimeout = connectTimeout;
            return this;
        }

        /**
         * 设置网络请求超时时间
         *
         * @param time 时间
         * @param unit 单位
         */
        public Builder setConnectTimeout(long time, TimeUnit unit) {
            mConnectTimeout = new TimeKey(time, unit);
            return this;
        }

        /**
         * 设置网络请求超时时间 默认单位秒
         *
         * @param time 时间 默认单位秒
         */
        public Builder setConnectTimeout(long time) {
            setConnectTimeout(time, TimeUnit.SECONDS);
            return this;
        }

        /**
         * 添加一个拦截器
         *
         * @param interceptorList 拦截器
         */
        public Builder addInterceptor(Interceptor interceptorList) {
            mInterceptorList.add(interceptorList);
            return this;
        }

        /**
         * 添加请求头
         *
         * @param key   请求头的 key
         * @param value 请求头键的值
         */
        public Builder addHeaderMap(String key, String value) {
            mHeaderMap.put(key, value);
            return this;
        }

        /**
         * 清除请求头
         */
        public Builder clearHeaderMap() {
            mHeaderMap.clear();
            return this;
        }

        /**
         * 清除所有拦截器
         */
        public Builder clearInterceptor() {
            mInterceptorList.clear();
            return this;
        }

        /**
         * 设置解析库
         *
         * @param jsonParse 对应的JSON解析库
         */
        public Builder setJsonConverter(IJsonParse jsonParse) {
            mJsonParse = jsonParse;
            return this;
        }

        /**
         * 构建对象
         *
         * @return NetWork连接对象
         */
        public NetWork build() {
            return new NetWork(this);
        }
    }
}
