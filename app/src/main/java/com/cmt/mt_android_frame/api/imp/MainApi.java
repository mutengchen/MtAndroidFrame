package com.cmt.mt_android_frame.api.imp;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MainApi {
    @POST("/api/login")
    Observable<ResponseBody> login(@Body RequestBody body);

    @FormUrlEncoded
    @POST("/api/logout")
    Observable<ResponseBody> loginOut(@Part("tokeb") RequestBody token);

    @GET("/api/configdata")
    Observable<ResponseBody> getConfig();

    @POST("/api/setpwd")
    Observable<ResponseBody> changePwd(@Body RequestBody body);
    @POST("/api/userdetail")
    Observable<ResponseBody> getUserDetail(@Body RequestBody body);

    // 版本更新接口
    @GET("/api/currentversion")
    Observable<ResponseBody> getLatestVersion();

}
