package com.cmt.mt_android_frame.api.imp;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface TaskApi {

    @POST("/api/task/list")
    Observable<ResponseBody> getTaskList(@Body RequestBody body);

    @GET("/api/task/detail")
    Observable<ResponseBody> getTaskDetail(@Query("taskid") String taskid);

    @POST("/api/task/publish")
    Observable<ResponseBody> publishTask(@Body RequestBody body);

    @POST("/api/image")
    Observable<ResponseBody> getImage(@Body RequestBody body);

    @POST("/api/task/updateexecutorstate")
    Observable<ResponseBody> updateTaskStatus(@Body RequestBody body);


    @POST("/api/userlist")
    Observable<ResponseBody> getOfficer(@Body RequestBody body);

    @POST("/api/task/search")
    Observable<ResponseBody> taskSearch(@Body RequestBody body);

    @POST("/api/task/stats")
    Observable<ResponseBody> taskstats(@Body RequestBody body);

    @HTTP(method = "DELETE",path="api/task/modify",hasBody = true)
    Observable<ResponseBody> deleteTask(@Body RequestBody body);

    @PUT("api/task/modify")
    Observable<ResponseBody> updateTaskInfo(@Body RequestBody body);
}
