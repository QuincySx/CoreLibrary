package com.a21vianet.quincysx.library.commoncore.manager;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

/**
 * Created by wang.rongqiang on 2017/5/4.
 * <p>Freagment/Activity 总的一些操作</p>
 */

public interface IBaseOperation<P> {
    /**
     * <p>初始化视图</p>
     * <p>不需要可以不重写</p>
     *
     * @param state
     */
    void initView(Bundle state);

    /**
     * <p>创建 Presenter </p>
     * <p>不需要可以不重写</p>
     */
    P createPresenter();

    /**
     * <p>返回布局</p>
     *
     * @return
     */
    @LayoutRes
    int getLayoutResId();
}
