package mlt.fencepuzzle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    // Logging
    private static final String TAG = "Debug: WelcomeActivity";

    // Controls sound and music
    private boolean mMusicOn;
    private boolean mSoundOn;

    // Settings
    private static int SETTINGS_REQUEST = 0;

    // MediaPlayer
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setInstanceVarsFromSharedPrefs();

        mp = MediaPlayer.create(this, R.raw.menu_music);
        mp.setLooping(true);
        playMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setInstanceVarsFromSharedPrefs();
        playMusic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
        mp.release();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_REQUEST) {
            setInstanceVarsFromSharedPrefs();
            playMusic();
        }
    }

    public void chooseLevel(View view) {
        Intent intent = new Intent(this, LevelSelectorActivity.class);
        startActivity(intent);
    }

    public void viewInstructions(View view) {
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }

    public void changeSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SETTINGS_REQUEST);
    }

    private void setInstanceVarsFromSharedPrefs() {
        SharedPreferences sharedPref = getSharedPreferences("FencePuzzle", MODE_PRIVATE);
        mSoundOn = sharedPref.getBoolean("sound", true);
        //mTheme = sharedPref.getString("theme_option", getString(R.string.white));
        mMusicOn = sharedPref.getBoolean("music", true);


        Log.d(TAG, "Sound is: " + mSoundOn);
        //Log.d(TAG, "Theme is: " + mTheme);
        Log.d(TAG, "Music is: " + mMusicOn);
    }

    private void playMusic() {
        if(mMusicOn) {
            mp.start();
        }
    }

    private void stopMusic() {
        if(mMusicOn) {
            mp.pause();
        }
    }
}
