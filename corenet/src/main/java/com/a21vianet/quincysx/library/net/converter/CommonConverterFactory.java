package com.a21vianet.quincysx.library.net.converter;

import com.a21vianet.quincysx.library.net.converter.jsonparse.GsonParse;
import com.a21vianet.quincysx.library.net.converter.jsonparse.IJsonParse;
import com.a21vianet.quincysx.library.net.converter.jsonparse.StringParse;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by wang.rongqiang on 2017/5/26.
 */

public class CommonConverterFactory extends Converter.Factory {
    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to
     * JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static CommonConverterFactory create() {
        return create(new GsonParse());
    }

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static CommonConverterFactory create(IJsonParse parse) {
        if (parse == null) {
            parse = new GsonParse();
        }
        return new CommonConverterFactory(parse);
    }

    private final IJsonParse parse;

    private CommonConverterFactory(IJsonParse parse) {
        if (parse == null) throw new NullPointerException("gson == null");
        this.parse = parse;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        if (parse instanceof StringParse && type != String.class) {
            return null;
        }
        return new ResponseBodyConverter<>(parse, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
        if (parse instanceof StringParse && type != String.class) {
            return null;
        }
        return new RequestBodyConverter<>(parse, type);
    }

    public static class ResponseBodyConverter<T> implements Converter<ResponseBody,
            T> {
        private final IJsonParse<T> parse;
        private final Type type;

        ResponseBodyConverter(IJsonParse parse, Type type) {
            this.parse = parse;
            this.type = type;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            try {
                return parse.responseConvert(value, type);
            } finally {
                value.close();
            }
        }
    }

    static final class RequestBodyConverter<T> implements Converter<T, RequestBody> {
        private final IJsonParse<T> parse;
        private final Type type;

        RequestBodyConverter(IJsonParse parse, Type type) {
            this.parse = parse;
            this.type = type;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            return parse.requestConvert(value, type);
        }
    }
}
