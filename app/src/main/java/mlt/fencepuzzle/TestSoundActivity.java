package mlt.fencepuzzle;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TestSoundActivity extends AppCompatActivity {

    private static final String TAG = "Test Sound Activity";

    // Controls sound
    boolean mSoundOn;

    // Controls background
    String mTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sound);

        // Load preferences
        // setInstanceVarsFromSharedPrefs();
        SharedPreferences sharedPref = getSharedPreferences("FencePuzzle", MODE_PRIVATE);
        mSoundOn = sharedPref.getBoolean("sound", true);
        mTheme = sharedPref.getString("theme_option", getString(R.string.white));

        Log.d(TAG, "Sound is: " + mSoundOn);
        Log.d(TAG, "Theme is: " + mTheme);

        Button test = (Button) this.findViewById(R.id.testSoundButton);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.beep);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSoundOn) {
                    mp.start();
                }
            }
        });
    }
}
