package com.cmt.mt_android_frame.api.retrofit;

import com.cmt.mt_android_frame.api.Api;
import com.cmt.mt_android_frame.utils.OkHttpUtil;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final int DEFAULT_TIME_OUT = 5;
    public static<T> T getInstance(Class<T> serveice){
        Retrofit client = new Retrofit.Builder()
                .client(OkHttpUtil.getBuilder().build()) //okhttpClickent 参数拦截
                .baseUrl(Api.API_HOST)//默认访问地址
                .addConverterFactory(GsonConverterFactory.create())  //将json 转化成bean
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return client.create(serveice);
    }

}
