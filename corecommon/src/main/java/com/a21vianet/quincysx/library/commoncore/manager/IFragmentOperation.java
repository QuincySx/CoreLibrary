package com.a21vianet.quincysx.library.commoncore.manager;

import android.support.v4.app.Fragment;

/**
 * Created by wang.rongqiang on 2017/1/10.
 * Fragment 操作的接口
 */

public interface IFragmentOperation {

    /**
     * 添加Fragment
     *
     * @param contentID 布局 Id
     * @param fragment  添加的 Fragment
     */
    void addFragment(int contentID, Fragment fragment);

    /**
     * 删除Fragment
     *
     * @param fragment 删除的 Fragment
     */
    void removeFragment(Fragment fragment);

    /**
     * 隐藏Fragment
     *
     * @param fragment 隐藏的 Fragment
     */
    void hideFragment(Fragment fragment);

    /**
     * 显示Fragment
     *
     * @param contentID 布局 Id
     * @param fragment  显示的 Fragment
     */
    void showFragment(int contentID, Fragment fragment);

    /**
     * <p>替换Fragment</p>
     *
     * @param contentID 布局 Id
     * @param fragment  替换的 Fragment
     */
    void replaceFragment(int contentID, Fragment fragment);
}
