package com.a21vianet.quincysx.library.net.converter.jsonparse;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by wang.rongqiang on 2017/5/26.
 */

public class StringParse<T> implements IJsonParse<T> {
    protected static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");

    @Override
    public T responseConvert(ResponseBody value, Type type) throws IOException {
        return (T) value.string();
    }

    @Override
    public T responseConvert(String value, Type type) throws IOException {
        return (T) value;
    }

    @Override
    public RequestBody requestConvert(T value, Type type) throws IOException {
        return RequestBody.create(MEDIA_TYPE, value.toString());
    }
}
