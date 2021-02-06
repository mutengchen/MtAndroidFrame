package com.cmt.mt_android_frame.api.imp;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NotifiApi {
    @GET("/api/notify")
    Observable<ResponseBody> getNotifyList(@Query("userid") String userid);
    @POST("/api/notify")
    Observable<ResponseBody> getNotifyList(@Body RequestBody body);

}
