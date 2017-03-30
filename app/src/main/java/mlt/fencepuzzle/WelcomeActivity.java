package mlt.fencepuzzle;

import android.content.Intent;
import android.content.SharedPreferences;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setInstanceVarsFromSharedPrefs();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_REQUEST) {
            setInstanceVarsFromSharedPrefs();
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
}
