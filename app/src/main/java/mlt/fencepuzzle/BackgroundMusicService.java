package mlt.fencepuzzle;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BackgroundMusicService extends Service {

    // Logging
    private static final String TAG = "Debug: BGMusicService";
    private static MediaPlayer mp;
    private final IBinder mBinder = new LocalBinder();
    private boolean musicOn;
    private boolean isPlaying = false;

    @Override
    public void onCreate() {
        mp = MediaPlayer.create(this, R.raw.test_music);
        mp.setLooping(true);
        checkPref();
    }

    public class LocalBinder extends Binder {
        BackgroundMusicService getService() {
            return BackgroundMusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return mBinder;
    }

    private void checkPref() {
        SharedPreferences sharedPref = getSharedPreferences("FencePuzzle", MODE_PRIVATE);
        musicOn = sharedPref.getBoolean("music", true);
    }

    // Controls for this service
    public void playMusic() {
        checkPref();
        if (musicOn && !isPlaying) {
            try {
                mp.start();
            } catch (final Exception e) {}
            isPlaying = true;
        }
    }

    public void pauseMusic() {
        checkPref();
        if (isPlaying) {
            mp.pause();
            isPlaying = false;
        }
    }

    public void stopMusic() {
        checkPref();
        isPlaying = false;
        try {
            mp.stop();
            mp.release();
        } catch (final Exception e) {}
    }

//    public IBinder onBind(Intent arg0) {
//        return null;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mp = MediaPlayer.create(this, R.raw.test_music);
//        mp.setLooping(true);
//        mp.setVolume(100, 100);
//    }
//
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        mp.start();
//        Log.d(TAG, "Music is starting.");
//        return START_STICKY_COMPATIBILITY;
//    }
//
//    public static void onPause() {
//        mp.pause();
//    }
//
//    public static void onStop() {
//        mp.pause();
//    }
//
//    @Override
//    public void onDestroy() {
//        mp.stop();
//        mp.release();
//    }
}
