package com.a21vianet.quincysx.library.mvpcore.mvp;

import com.a21vianet.quincysx.library.commoncore.ViewNullException;

import java.lang.ref.WeakReference;

/**
 * Created by wamg.rongqiang on 2017/1/6.
 */

public abstract class BasePresenter<V extends BaseView> {
    private WeakReference<V> mReference;

    public void attachView(V view) {
        mReference = new WeakReference<V>(view);
        onStart();
    }

    public abstract void onStart();

    public void detachView() {
        if (this.mReference != null) {
            this.mReference.clear();
            this.mReference = null;
        }
    }

    public boolean isViewBind() {
        return this.mReference != null ? true : false;
    }

    /**
     * 获得View，当View为空时，抛出异常
     * @return
     * @throws ViewNullException
     */
    public V getView() throws ViewNullException {
        if (isViewBind()) {
            return this.mReference.get();
        } else {
            throw new ViewNullException("View is NUll");
        }
    }

    /**
     * 获得View，但是当View为空时返回NUll
     * @return
     */
    public V getViewOrEmpty() {
        return this.mReference.get() == null ? null : this.mReference.get();
    }
}
