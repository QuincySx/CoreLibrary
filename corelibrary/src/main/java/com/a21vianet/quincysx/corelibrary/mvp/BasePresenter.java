package com.a21vianet.quincysx.corelibrary.mvp;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by wamg.rongqiang on 2017/1/6.
 */

public abstract class BasePresenter<V extends BaseView> {
    private WeakReference<V> mReference;
    private CompositeDisposable mDisposable = new CompositeDisposable();

    /**
     * 绑定 View
     */
    public void attachView(V view) {
        mReference = new WeakReference(view);
        onStart();
    }

    /**
     * 解绑 View
     */
    public void detachView() {
        mDisposable.clear();
        if (this.mReference != null) {
            this.mReference.clear();
        }
        mDisposable = null;
        this.mReference = null;
        System.gc();
    }

    /**
     * 添加监听者到管理
     *
     * @param disposable
     */
    public void addRxDisposable(Disposable disposable) {
        mDisposable.add(disposable);
    }

    /**
     * 检查 View 是否绑定
     *
     * @return
     */
    public boolean isViewBind() {
        if (this.mReference != null) {
            return this.mReference.get() != null ? true : false;
        }
        return false;
    }

    /**
     * 获得View，当View为空时，抛出异常
     *
     * @return
     */
    public V getView() {
        return this.mReference.get();
    }

    /**
     * 绑定完成即刻触发此方法
     */
    public abstract void onStart();
}
