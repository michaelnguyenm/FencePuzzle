package mlt.fencepuzzle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class GameActivity extends AppCompatActivity {

    // Logging
    private static final String TAG = "Debug: GameActivity";

    // Settings member variables
    private boolean mMusicOn;
    private boolean mSoundOn;
    private int mTheme;

    // Settings
    private static int SETTINGS_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get settings first
        setInstanceVarsFromSharedPrefs();
        // Now draw views
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_REQUEST) {
            setInstanceVarsFromSharedPrefs();
        }
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

        // Get theme index
        String themeName = sharedPref.getString("theme_option", getString(R.string.white));
        String[] themes = getResources().getStringArray(R.array.theme_options);
        int i = 0;
        while(i < themes.length) {
            if(themeName.equals(themes[i])) {
                mTheme = i;
            }
            i++;
        }

        mMusicOn = sharedPref.getBoolean("music", true);

        Log.d(TAG, "Sound is: " + mSoundOn);
        Log.d(TAG, "Theme is: " + mTheme + ": " + themeName);
        Log.d(TAG, "Music is: " + mMusicOn);
    }
}
