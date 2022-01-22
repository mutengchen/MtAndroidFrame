package com.cmt.mt_android_frame.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.cmt.mt_android_frame.common.MyApplication;

/**
 * 声音播放工具类
 */
public class AudioUtil {
    private static MediaPlayer player;
    private static String TAG = AudioUtil.class.getSimpleName();
    public static void playRawVoice(Context context, int rawResId){
        Log.d(AudioUtil.class.getSimpleName(), "playRawVoice: "+rawResId);
        if(player!= null){
            if(player.isPlaying()){
                Log.d(TAG, "playRawVoice: other voice playing!");
                return;
            }
        }
        player = MediaPlayer.create(context,rawResId);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.reset();
                mediaPlayer.release();
                player = null;
            }
        });
        player.start();
    }




    /**
     * 播放提示音
     */
    public static void playAlertVoice(int soundResId) {
        Context context = MyApplication.getContext();
        playRawVoice(context, soundResId);
    }
}
