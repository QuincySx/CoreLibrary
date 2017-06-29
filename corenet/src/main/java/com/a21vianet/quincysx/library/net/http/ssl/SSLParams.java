package com.a21vianet.quincysx.library.net.http.ssl;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by wamg.rongqiang on 2017/1/16.
 */

public class SSLParams {
    public SSLSocketFactory sSLSocketFactory;
    public X509TrustManager trustManager;

    public boolean isNull() {
        if (sSLSocketFactory != null && trustManager != null) {
            return true;
        } else {
            return false;
        }
    }
}
