package com.cmt.mt_android_frame.api.interceptor;

import android.app.Activity;
import android.content.Context;
import android.util.Log;


import com.cmt.mt_android_frame.utils.AlertUtil;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Date: 2020/1/9
 * Time: 15:15
 * author: cmt
 * desc:
 */
public class OnResponsNoAlertIntecepter extends DisposableObserver<ResponseBody> {
    private static final String TAG ="OnResponseIntercepter";
    private OnResponsNoAlertIntecepter.ResponsePretreatListener responsePretreatListener; //请求处理的监听器
    private Context context;
    @Override
    public void onNext(ResponseBody responseBody) {
        Log.i(TAG,"onNext");
        //成功接收到有效的数据,并返回给调用者
        if(context!=null){
            Log.i(TAG,"请求api完毕");
            if(!((Activity)context).isFinishing())
                AlertUtil.loadDissmiss();
        }
        String result = "";
        try {
            result = responseBody.string();
            responsePretreatListener.onSuccess(result);
        } catch (Exception e) {
//            ErrorLogUtils.getprintStackInfo(e);
            e.printStackTrace();
        }
    }

    public OnResponsNoAlertIntecepter(OnResponsNoAlertIntecepter.ResponsePretreatListener responseToast, Context context) {
        this.responsePretreatListener = responseToast;
        this.context = context;
    }

    /**
     * 对错误进行统一处理
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        AlertUtil.loadDissmiss();
        if(e instanceof SocketTimeoutException){
            Log.i(TAG,"SocketTimeoutException");
            responsePretreatListener.onFailed("Network connection timeout",400);//网络连接超时
//            ErrorLogUtils.setLog(e.toString());
        }//请求超时
        else if (e instanceof ConnectException){
            Log.i(TAG,"网络连接超时");
            responsePretreatListener.onFailed("Network connection timeout",400);//网络连接超时
//            ErrorLogUtils.setLog(e.toString());
        }
        else if(e instanceof SSLHandshakeException){
            Log.i(TAG,"SSL证书异常");
            responsePretreatListener.onFailed("Ssl certificate is abnormal",403);
//            ErrorLogUtils.setLog(e.toString());
        }
        else if(e instanceof HttpException){
            //获取错误码
            int code = ((HttpException) e).code();
            String msg = null;
            try {
                //获取错误的response信息
                msg = ((HttpException)e).response().errorBody().string();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
//            AlertUtils.toastAlert(code+":"+msg);

            Log.i(TAG,"code="+code);
            if(code == 504||code==502){
                responsePretreatListener.onFailed(msg,code);
            }
            else if(code == 404){
                responsePretreatListener.onFailed(msg,code);
            }else if(code == 601 || code == 600) {
                //包括了token过期，sso未登录，
                responsePretreatListener.onFailed(msg,code);
//                AppUtils.exitUser(MyApplication.getContext());
            }
            else{
                responsePretreatListener.onFailed(msg,code);
            }
            String temp = "error : code ="+code+"; msg = "+msg;
//            ErrorLogUtils.setLog(temp);

        }
        else if(e instanceof UnknownHostException){
            Log.i(TAG,"域名解析失败");
            responsePretreatListener.onFailed("Domain access failed",400);
//            ErrorLogUtils.setLog(e.toString());

        }else{
            //其他未知类型的错误
            Log.i(TAG,"other");
            responsePretreatListener.onFailed(e.getMessage(),400);
//            ErrorLogUtils.setLog(e.toString());

        }
    }

    //TODO 如果需要在请求结束的时候实现额外的操作，可以在该函数里面实现
    @Override
    public void onComplete() {
        //TODO 请求成功之后，需要隐藏加载框，在这里，自己实现

    }
    //TODO 如果需要在请求开始的时候实现额外的操作，可以在该函数里面实现
    @Override
    protected void onStart() {
        super.onStart();
        //TODO 需要显示加载框的，看这里，自己实现
        if(context==null)return;
//        if(!((Activity)context).isFinishing())
        Log.i(TAG,"开始请求api了");
        AlertUtil.getLoadingDialogInstance((Activity) context).show();
    }


    public interface  ResponsePretreatListener{
        void onFailed(String result, int code);
        void onSuccess(String result);
    }
}
