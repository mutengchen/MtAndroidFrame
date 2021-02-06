package com.cmt.mt_android_frame.api.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ResponseHeaderInterceptor implements Interceptor {
    private String TAG = "ResponseHeaderInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        Log.d(TAG, "intercept: version_code = "+response.headers().get("version"));
        return response;

    }
}
