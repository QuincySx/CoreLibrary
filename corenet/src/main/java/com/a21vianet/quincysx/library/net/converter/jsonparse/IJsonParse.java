package com.a21vianet.quincysx.library.net.converter.jsonparse;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by wang.rongqiang on 2017/5/26.
 */

public interface IJsonParse<T> {
    T responseConvert(ResponseBody value, Type type) throws IOException;

    T responseConvert(String value, Type type) throws IOException;

    RequestBody requestConvert(T value, Type type) throws IOException;
}
