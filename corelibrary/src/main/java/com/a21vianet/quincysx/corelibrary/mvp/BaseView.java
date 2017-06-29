package com.a21vianet.quincysx.corelibrary.mvp;

import android.content.Context;

/**
 * Created by wamg.rongqiang on 2017/1/9.
 */

public interface BaseView {
    /**
     * 获取APP上下文
     *
     * @return
     */
    Context getViewContext();

    /**
     * 显示进度条
     */
    void showProgress();

    /**
     * 关闭进度条
     */
    void dismissProgress();

    /**
     * 弹出Toast
     *
     * @param msg 内容
     */
    void showPrompt(String msg);

    /**
     * @param msg 内容
     * @param b   是否连续弹出
     */
    void showPrompt(String msg, boolean b);
}
