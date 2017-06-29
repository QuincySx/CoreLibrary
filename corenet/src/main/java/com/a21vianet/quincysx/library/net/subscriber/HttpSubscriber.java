package com.a21vianet.quincysx.library.net.subscriber;

import android.widget.Toast;

import com.a21vianet.quincysx.library.commoncore.utility.ContentUtility;
import com.a21vianet.quincysx.library.net.netexception.Error;
import com.a21vianet.quincysx.library.net.netexception.NetErrorManager;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by wamg.rongqiang on 2017/1/5.
 */

public abstract class HttpSubscriber<T> implements Subscriber<T> {
    private boolean isToast = false;

    public HttpSubscriber() {
    }

    /**
     * 打开Api异常自动调用Toast
     *
     * @param isToast
     */
    public HttpSubscriber(boolean isToast) {
        this.isToast = isToast;
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public final void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public final void onError(Throwable t) {
        end();
        //异常处理
        _onError(NetErrorManager.handleError(t));
    }

    @Override
    public void onComplete() {
        end();
    }

    protected void _onError(Error error) {
        if (error != null) {
//            LogUtils.e(error);
            if (isToast && error.getErrMsg() != null) {
                Toast.makeText(ContentUtility.getContext(), error.getErrMsg(), Toast
                        .LENGTH_SHORT).show();
            }
        }
    }

    private void end() {
    }

    protected abstract void onSuccess(T t);
}
