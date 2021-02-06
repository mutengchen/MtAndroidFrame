package com.cmt.mt_android_frame.api.loader;

import com.cmt.mt_android_frame.api.imp.MainApi;
import com.cmt.mt_android_frame.api.retrofit.RetrofitClient;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class MainApiLoader extends BaseLoader{
    private MainApi mainApi;
    private static MainApiLoader loader;
    public MainApiLoader(){this.mainApi = RetrofitClient.getInstance(MainApi.class);}

    public static MainApiLoader getInstance(){
        if(loader == null) loader = new MainApiLoader();
        return loader;
    }
    public io.reactivex.Observable<ResponseBody> login(RequestBody body) {
        return observe(mainApi.login(body));
    }

    public io.reactivex.Observable<ResponseBody> loginOut(RequestBody token) {
        return observe(mainApi.loginOut(token));
    }
    public io.reactivex.Observable<ResponseBody> getConfig() {
        return observe(mainApi.getConfig());
    }

    public io.reactivex.Observable<ResponseBody> changePwd(RequestBody body) {
        return observe(mainApi.changePwd(body));
    }
    public io.reactivex.Observable<ResponseBody> getUserDetail(RequestBody body) {
        return observe(mainApi.getUserDetail(body));
    }

    public io.reactivex.Observable<ResponseBody> getLatestVersion() {
        return observe(mainApi.getLatestVersion());
    }
}
