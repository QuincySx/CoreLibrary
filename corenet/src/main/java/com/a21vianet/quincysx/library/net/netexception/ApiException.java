package com.a21vianet.quincysx.library.net.netexception;

/**
 * Created by wamg.rongqiang on 2017/1/5.
 */

public class ApiException extends RuntimeException {

    private final Error mError;

    public ApiException(Error error) {
        mError = error;
    }

    public Error getError() {
        return mError;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "mError=" + mError +
                '}';
    }
}
