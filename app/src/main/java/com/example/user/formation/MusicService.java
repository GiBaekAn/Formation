package com.example.user.formation;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2017-05-23.
 */

public class MusicService extends Service {
    //List<String> songs = new ArrayList<String>();
    public MediaPlayer mp;

    public IBinder onBind(Intent arg0) {
        return null;
    }

    public void onStart(Intent intent, int startId) {
        Log.i("Example", "Service onStart()");
        super.onStart(intent, startId);
        mp = MediaPlayer.create(this, R.raw.backgroundsound3);
        mp.setLooping(true); // 반복 재생 설정 (true와 false로 조정 가능)
        mp.start(); //음악 재생

    }
    public void onDestroy() {
        Log.i("Example", "Service onDestroy()");
        super.onDestroy();
        mp.stop(); //음악 정지
    }
}

