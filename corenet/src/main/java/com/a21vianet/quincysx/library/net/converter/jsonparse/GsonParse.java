package com.a21vianet.quincysx.library.net.converter.jsonparse;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by wang.rongqiang on 2017/5/26.
 */

public class GsonParse<T> implements IJsonParse<T> {
    protected static final Charset UTF_8 = Charset.forName("UTF-8");
    protected static final MediaType MEDIA_TYPE = MediaType.parse("application/json; " +
            "charset=UTF-8");
    protected Gson mGson;

    public GsonParse() {
        mGson = new Gson();
    }

    @Override
    public T responseConvert(ResponseBody value, Type type) throws IOException {
        TypeAdapter<T> adapter = (TypeAdapter<T>) mGson.getAdapter(TypeToken.get(type));
        JsonReader jsonReader = mGson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }

    @Override
    public T responseConvert(String value, Type type) throws IOException {
        TypeAdapter<T> adapter = (TypeAdapter<T>) mGson.getAdapter(TypeToken.get(type));
        return adapter.fromJson(value);
    }

    @Override
    public RequestBody requestConvert(T value, Type type) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = mGson.newJsonWriter(writer);
        TypeAdapter<T> adapter = (TypeAdapter<T>) mGson.getAdapter(TypeToken.get(type));
        adapter.write(jsonWriter, value);
        jsonWriter.close();
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }
}
