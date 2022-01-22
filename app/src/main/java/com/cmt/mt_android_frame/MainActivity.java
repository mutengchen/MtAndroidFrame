package com.cmt.mt_android_frame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String conntent = "{\"aaa\":\"zzzzzzzzzz\",\"zz\":\"zzz\"}";
//        Logger.json(conntent);
    }
}
