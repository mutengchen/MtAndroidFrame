package com.cmt.mt_android_frame.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cmt.mt_android_frame.api.imp.MainApi;
import com.cmt.mt_android_frame.api.interceptor.OnResponseIntercepter;
import com.cmt.mt_android_frame.api.loader.MainApiLoader;
import com.cmt.mt_android_frame.model.UserBean;

public class UserViewModel extends ViewModel {
    MutableLiveData<UserBean> data;
    MainApiLoader apiLoader = MainApiLoader.getInstance();

    public UserViewModel() {
        this.data = new MutableLiveData<>();
    }

    public void getUserInfo(){
        apiLoader.login(null).subscribeWith(new OnResponseIntercepter(new OnResponseIntercepter.ResponsePretreatListener() {
            @Override
            public void onFailed(String result, int code) {

            }

            @Override
            public void onSuccess(String result) {
//                data.setValue();
            }
        },null));
    }
}
