package com.cmt.mt_android_frame.utils;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.cmt.mt_android_frame.R;
import com.cmt.mt_android_frame.common.MyApplication;
import com.cmt.mt_android_frame.view.dailog.LoadingDialog;


/* 提示控件
 * Created by cmt on 2019/7/5
 */
public class AlertUtil {

    private static LoadingDialog loadingDialog;
    public static LoadingDialog getLoadingDialogInstance(Context context){
        if(loadingDialog==null)
            loadingDialog = new LoadingDialog(context, R.style.MyDialog);
        return loadingDialog;
    }
    public static synchronized void loadDissmiss(){
        if(loadingDialog!=null && loadingDialog.isShowing()){
            loadingDialog.dismiss();
            Log.i("tag","弹出框已经被销毁");
            loadingDialog = null;
        }
    }

    //统一的toast短提示（0.5s）
    public static void toastAlert(String alert){
    Toast toast = Toast.makeText(MyApplication.getContext(),alert, Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.CENTER,0,0);
    toast.show();

    }
    //统一的toast长提示（1.0s)
    public static void toastLongAlert(String alert){
        Toast toast = Toast.makeText(MyApplication.getContext(),alert, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }


}
