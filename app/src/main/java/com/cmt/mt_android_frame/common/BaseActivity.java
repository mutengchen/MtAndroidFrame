package com.cmt.mt_android_frame.common;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.cmt.mt_android_frame.utils.ClickUtil;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 下载文件权限请求码
     */
    private static final int RC_PERMISSION_DOWNLOAD = 1;
    /**
     * 删除文件权限请求码
     */
    private static final int RC_PERMISSION_DELETE = 2;

//    private DownloadApkDialog mDownloadingDialog;

    private String mNewVersion = "1.0.3";
    private String mApkUrl = "http://7xk9dj.com1.z0.glb.clouddn.com/BGAUpdateDemo_v1.0.3_debug.apk";

    public abstract int setLayoutID();
    String [] permission;
    PermissionListener mPermissionListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //软键盘不挤压布局
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //第一次安装
        if (!this.isTaskRoot()) {
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
        setContentView(setLayoutID());

        //禁止切换屏幕
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getPermissionListbyAndroidVersion();

        // 订阅EventBus
//        EventBus.getDefault().register(this);

        //设置顶部状态栏颜色
        setStatusBarColor();
        requestRunTimePermission(permission, new PermissionListener() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onGranted(List<String> grantedPermission) {

            }

            @Override
            public void onDenied(List<String> deniedPermission) {

            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            if(ClickUtil.isFastClick()){
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Resources resource = getResources();
        Configuration configuration = resource.getConfiguration();
//        int fontScale = SharePreferencesUtils.getInstance().getInt("app_size");
//        if(fontScale!=0){
//            configuration.fontScale = (float) (1+((fontScale*1.0)/100));// 设置字体的缩放比例
//        }
        resource.updateConfiguration(configuration, resource.getDisplayMetrics());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        loadData();

    }

    protected void requestRunTimePermission(String[] permissions, PermissionListener listener) {

        //todo 获取栈顶activity，如果null。return；

        this.mPermissionListener = listener;

        List<String> permissionList = new ArrayList<>();
        //遍历所有需要请求的权限的授予情况
        for (String permission : permissions) {
            if(ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                permissionList.add(permission);
            }
        }
        if(!permissionList.isEmpty()){
            //弹出权限授予框
            ActivityCompat.requestPermissions(this,permissionList.toArray(new String[permissionList.size()]),1);
        }else{
            //所要求的权限都授予成功
            listener.onGranted();
        }
    }
    private void getPermissionListbyAndroidVersion() {
        permission = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.VIBRATE

        };
    }

    private void setStatusBarColor(){
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#000000"));
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    //加载api数据，如果页面需要加载的话
    public abstract void loadData();



}

interface PermissionListener {
    //授权成功
    void onGranted();
    //授权部分
    void onGranted(List<String> grantedPermission);
    //拒绝授权
    void onDenied(List<String> deniedPermission);
}
