package com.a21vianet.quincysx.library.mvpcore.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.a21vianet.quincysx.library.mvpcore.avtivityManager.ActivityCollector;
import com.a21vianet.quincysx.library.mvpcore.avtivityManager.BaseManagerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wamg.rongqiang on 2017/1/4.
 * 处理权限的方法
 */

public class PermissionActivity extends BaseManagerActivity {
    private static final int PermissionRequestCode = 0x001;
    private static PermissionRequestCallBack sPermissionRequestCallBack;

    /**
     * 检测权限
     * @param permissions 需要检测的权限
     * @param listener 权限状态回调接口
     */
    public static void requestRuntimePermission(
            @NonNull String[] permissions,
            @NonNull PermissionRequestCallBack listener) {
        Activity topActivity = ActivityCollector.getTopActivity();
        if (topActivity == null) {
            return;
        }
        sPermissionRequestCallBack = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager
                    .PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(topActivity, permissionList.toArray(new
                    String[permissionList.size()]), PermissionRequestCode);
        } else {
            sPermissionRequestCallBack.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionRequestCode:
                if (grantResults.length > 0) {
                    List<String> deniedPermission = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermission.add(permission);
                        }
                    }
                    if (deniedPermission.isEmpty()) {
                        sPermissionRequestCallBack.onGranted();
                    } else {
                        sPermissionRequestCallBack.onDenied(deniedPermission);
                    }
                }
            default:
                break;
        }

    }
}
