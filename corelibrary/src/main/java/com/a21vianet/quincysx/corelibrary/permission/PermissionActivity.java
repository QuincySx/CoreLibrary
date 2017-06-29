package com.a21vianet.quincysx.corelibrary.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.a21vianet.quincysx.corelibrary.avtivityManager.ActivityCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wamg.rongqiang on 2017/1/4.
 * 处理权限的方法
 */

public class PermissionActivity extends AppCompatActivity {
    /**
     * 请求权限的请求码
     */
    private static final int PermissionRequestCode = 0x001;

    /**
     * 权限处理结果的回调接口
     */
    private static PermissionRequestCallBack sPermissionRequestCallBack;

    /**
     * <p>检测权限并向用户请求权限</p>
     *
     * @param permissions 需要检测的权限
     * @param listener    权限状态回调接口
     */
    public static void requestRuntimePermission(
            @NonNull String[] permissions,
            @NonNull PermissionRequestCallBack listener) {
        //获取现在栈顶的Activity
        Activity topActivity = ActivityCollector.getCurrentActivity();
        if (topActivity == null) {
            return;
        }
        sPermissionRequestCallBack = listener;
        List<String> permissionList = new ArrayList<>();
        //判断是否有相应权限，把未授权的权限放在一个List里
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager
                    .PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        //当有未授权的权限进行权限请求
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
                    //判读没有授予权限的权限，并放到一个集合里
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            deniedPermission.add(permissions[i]);
                        }
                    }
                    //判断是否全部通过授权
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
