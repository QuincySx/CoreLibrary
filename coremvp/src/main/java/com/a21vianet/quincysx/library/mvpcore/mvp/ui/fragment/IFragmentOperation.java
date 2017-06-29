package com.a21vianet.quincysx.library.mvpcore.mvp.ui.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by wamg.rongqiang on 2017/1/10.
 * Fragment 操作的接口
 */

public interface IFragmentOperation {
    void addFragment(int contentID, Fragment fragment);

    void removeFragment(Fragment fragment);

    void hideFragment(Fragment fragment);

    void showFragment(int contentID, Fragment fragment);

    void replaceFragment(int contentID, Fragment fragment);
}
