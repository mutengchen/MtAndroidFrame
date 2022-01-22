package com.cmt.mt_android_frame.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * 权限申请工具类
 */
public class PermissionUtil {
    //申请一个或者多个权限
    public static void requestPermission(Activity activity,PermissionListener permissionListener,String... permissions){
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEach(permissions).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if(permissionListener!=null){
                    permissionListener.accept(permission);
                }else
                    Logger.e("未添加权限返回监听");
            }
        });
    }
    //判断权限是否已经被授予了
    public static boolean checkPermission(Activity activity,String permissions){
        RxPermissions rxPermissions = new RxPermissions(activity);
        return rxPermissions.isGranted(permissions);
    }

    public  abstract class PermissionListener{
        abstract void accept(Permission permission);
    }
}
