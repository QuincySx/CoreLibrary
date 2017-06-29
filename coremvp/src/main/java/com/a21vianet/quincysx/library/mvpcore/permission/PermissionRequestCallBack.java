package com.a21vianet.quincysx.library.mvpcore.permission;

import java.util.List;

/**
 * Created by wamg.rongqiang on 2017/1/4.
 * 权限请求回调
 */

public interface PermissionRequestCallBack {
    void onGranted();

    void onDenied(List<String> deniedPermission);
}
