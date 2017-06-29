package com.a21vianet.quincysx.corelibrary.permission;

import java.util.List;

/**
 * Created by wamg.rongqiang on 2017/1/4.
 * 权限请求结果回调
 */

public interface PermissionRequestCallBack {
    /**
     * 当前请求的权限全部授权时回调此方法
     */
    void onGranted();

    /**
     * 当前请求的权限有部分或全部没有授权时回调此方法
     */
    void onDenied(List<String> deniedPermission);
}
