package com.a21vianet.quincysx.library.net.netexception;

import android.content.Context;

import com.a21vianet.quincysx.library.R;
import com.a21vianet.quincysx.library.commoncore.utility.ContentUtility;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * Created by wamg.rongqiang on 2017/1/6.
 */

public class NetErrorManager {
    public static Error handleError(Throwable e) {
        Error error;
        if (e instanceof ApiException) {
            error = ((ApiException) e).getError();
        } else {
            e.printStackTrace();

            String errorMsg = "";
            String errorNo = "";

            error = new Error();

            Context context = ContentUtility.getContext();

            if (context != null) {
                if (e instanceof HttpException) {
                    HttpException he = (HttpException) e;
                    errorMsg = context.getString(R.string.api_error_connect);
                    errorNo = String.valueOf(he.code());
                } else if (e instanceof UnknownHostException) {
                    errorMsg = context.getString(R.string.api_error_connect);
                    errorNo = e.getLocalizedMessage();
                } else if (e instanceof SocketTimeoutException) {
                    errorMsg = context.getString(R.string.api_error_connect);
                    errorNo = e.getLocalizedMessage();
                } else if (e instanceof ConnectException) {
                    errorMsg = context.getString(R.string.api_error_connect);
                    errorNo = e.getLocalizedMessage();
                } else if (e instanceof IOException) {
                    errorMsg = context.getString(R.string.api_error_connect);
                    errorNo = e.getLocalizedMessage();
                } else if (e instanceof IllegalStateException) {
                    errorMsg = context.getString(R.string.api_error_state);
                    errorNo = e.getLocalizedMessage();
                } else if (e instanceof JsonSyntaxException) {
                    errorMsg = context.getString(R.string.api_error_json);
                    errorNo = e.getLocalizedMessage();
                } else {
                    errorMsg = e.getMessage();
                    errorNo = e.getLocalizedMessage();
                }
            } else {
                errorMsg = e.getMessage();
                errorNo = e.getLocalizedMessage();
            }
            if (!errorMsg.isEmpty()) {
                if (errorMsg != null) {
                    error.setErrMsg(errorMsg);
                }
                if (errorNo != null) {
                    error.setErrNo(errorNo);
                }
            }
        }
        return error;
    }
}
