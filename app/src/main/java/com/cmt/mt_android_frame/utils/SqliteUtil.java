package com.cmt.mt_android_frame.utils;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Date: 2020/3/31
 * Time: 9:25
 * author: cmt
 * desc:
 */
public class SqliteUtil {
    public static String TASK_TABLE_NAME = "result";
    public static class Result{
        public static final String NAME = "name";
        public static final String A1 = "a1";
        public static final String A2 = "a2";
        public static final String TC ="tc";
        public static final String CREATE_TIME = "create_time";
    }

    public static MyDatabaseHelper getHelper(){
        return MyDatabaseHelper.getInstance();
    }
    public static boolean insertData(ContentValues contentValues){
           SQLiteDatabase db =  getHelper().getWritableDatabase();
           db.insert(TASK_TABLE_NAME,null,contentValues);
        return true;
    }



    /**
     * 查找某个频道内没有发送出去的消息
     * @return
     */

    public static void log(String s){
        Log.e("sqlite utils",s);
    }
    public static ContentValues addContentValues(String name , double a1, double a2,double tc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Result.NAME,name);
        contentValues.put(Result.A1,a1);
        contentValues.put(Result.A2,a2);
        contentValues.put(Result.TC,tc);
        contentValues.put(Result.CREATE_TIME,getDate());

        return contentValues;
    }
    private static String getDate() {
        // Create a DateFormatter object for displaying date in specified
        // format.
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Create a calendar object that will convert the date and time value in
        // milliseconds to date.
        return formatter.format(date);
    }
}
