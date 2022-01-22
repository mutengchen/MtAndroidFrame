package com.cmt.mt_android_frame.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.cmt.mt_android_frame.common.MyApplication;


/**
 * Date: 2020/3/31
 * Time: 9:14
 * author: cmt
 * desc:
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static MyDatabaseHelper myDatabaseHelper = null;
    public static MyDatabaseHelper getInstance(){
            if(myDatabaseHelper==null)
                myDatabaseHelper = new MyDatabaseHelper(MyApplication.getContext(),"cyg.db",null,2);
        return myDatabaseHelper;
    }
    private Context mContext;
    //创建任务表
    private static final String CREATE_USER_TASK = "create table result("
            + "id integer primary key autoincrement, "
            + "name string, "
            + "a1 float, "
            + "a2 float, "
            + "create_time string, "
            + "tc float) ";


    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库
        db.execSQL(CREATE_USER_TASK);
        Log.i("db:create","Create db success");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //如果没有改动version的话，是不会upgrade的
            db.execSQL("drop table if exists result");
            onCreate(db);
    }
}
