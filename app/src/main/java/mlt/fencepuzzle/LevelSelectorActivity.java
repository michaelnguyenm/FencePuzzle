package mlt.fencepuzzle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class LevelSelectorActivity extends AppCompatActivity {

//    Button[] buttons = {(Button) this.findViewById(R.id.level_01),
//            (Button) this.findViewById(R.id.level_02),
//            (Button) this.findViewById(R.id.level_03),
//            (Button) this.findViewById(R.id.level_04),
//            (Button) this.findViewById(R.id.level_05),
//            (Button) this.findViewById(R.id.level_06),
//            (Button) this.findViewById(R.id.level_07),
//            (Button) this.findViewById(R.id.level_08),
//            (Button) this.findViewById(R.id.level_09),
//            (Button) this.findViewById(R.id.level_10),
//            (Button) this.findViewById(R.id.level_11),
//            (Button) this.findViewById(R.id.level_12)};
    int[] ids = {R.id.level_01,
            R.id.level_02,
            R.id.level_03,
            R.id.level_04,
            R.id.level_05,
            R.id.level_06,
            R.id.level_07,
            R.id.level_08,
            R.id.level_09,
            R.id.level_10,
            R.id.level_11,
            R.id.level_12
    };

    int[][] levels = {
            /*Easy*/
            {3,4,3,4,1,6,5,1,1,3,4,1,6,5,6,5},
            {0,3,4,0,3,5,6,4,6,4,3,5,0,6,5,0},
            {3,2,2,4,1,0,0,1,1,0,0,1,6,2,2,5},
            {3,4,3,4,6,8,7,5,3,7,8,3,6,5,6,5},
            /*Medium*/
            {3,2,4,3,4,0,6,4,1,6,8,4,3,7,5,3,7,5
            ,1,1,3,5,1,0,6,8,7,2,8,4,0,6,5,0,6,5},
            {3,4,3,4,3,4
            ,6,8,7,5,1,1
            ,3,8,8,2,7,5
            ,6,8,7,2,8,4
            ,3,5,1,3,5,1
            ,6,2,5,6,2,5},
            {0,3,4,3,4,0
            ,3,7,5,6,8,4
            ,6,8,4,0,1,1
            ,3,7,5,3,7,5
            ,6,8,2,7,8,4
            ,0,6,1,5,6,5},
            {3,4,3,4,3,4
            ,6,8,7,8,5,1
            ,3,7,5,6,2,5
            ,6,8,4,3,2,4
            ,3,7,8,7,4,1
            ,6,5,6,5,6,5}
    };

    // Logging
    private static final String TAG = "Debug: LevelActivity";

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
        setContentView(R.layout.activity_level_selector);
        setInstanceVarsFromSharedPrefs();

        mp = MediaPlayer.create(this, R.raw.level_music);
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

    public void selectLevel(View view){
        Button b = (Button) view;
        Log.d(TAG, "" + Integer.parseInt(b.getText().toString()));
    }

    public void selectLevelOne(View view) {
        Button b = (Button) view;
        Intent intent = new Intent(this, GameActivity.class);
//        intent.putExtra("levelID", Integer.parseInt(b.getText().toString()));

        intent.putExtra("levelID", Integer.parseInt(b.getTag().toString()));
        Log.d(TAG, "LEVEL ID SELECTED: " + Integer.parseInt(b.getTag().toString()));
        startActivity(intent);
    }

    private void setInstanceVarsFromSharedPrefs() {
        SharedPreferences sharedPref = getSharedPreferences("FencePuzzle", MODE_PRIVATE);
        mSoundOn = sharedPref.getBoolean("sound", true);
        //mTheme = sharedPref.getString("theme_option", getString(R.string.white));
        mMusicOn = sharedPref.getBoolean("music", true);

        for (int x : ids){
            Button b = (Button) this.findViewById(x);
            String sTag = b.getTag().toString();
            int lastScore = sharedPref.getInt(sTag, 0);
                Log.d(TAG, "************************setInstanceVarsFromSharedPrefs: lastScore: "+lastScore);
            Log.d(TAG, "************************setInstanceVarsFromSharedPrefs: Tag: "+sTag);

            if (lastScore<=0){
                    b.setText(sTag);
                }
                else
                    b.setText("♕");
        }


//        PercentRelativeLayout layout = (PercentRelativeLayout) this.findViewById(R.id.levelLayout);
//
//        for (int i = 0; i < layout.getChildCount(); i++) {
//            View v = layout.getChildAt(i);
//            Log.d(TAG, "************************setInstanceVarsFromSharedPrefs: View: ");
//
//            if (v instanceof Button) {
//                //validate your EditText here
//                Button b = (Button) v;
//                String sTag = v.getTag().toString();
//                int iTag = Integer.parseInt(b.getTag().toString());
//                int lastScore = sharedPref.getInt(sTag, -1);
//                Log.d(TAG, "************************setInstanceVarsFromSharedPrefs: lastScore: "+lastScore);
//                if (lastScore<=0){
//                    b.setText(sTag);
//                }
//                else
//                    b.setText("♕");
//            }
//        }

//        String id = "R.id.level_04";
//        Button b = (Button)findViewById(R.id.level_04);
//        Log.d(TAG, "setInstanceVarsFromSharedPrefs: "+b.getTag());
//        for (int i=1; i<13; i++){
//            Button b = (Button) view.findViewWithTag(i+"");
//            Log.d(TAG, "setInstanceVarsFromSharedPrefs: "+b.getTag());
////            int lastScore = sharedPref.getInt(i+"", i);
////            if(lastScore<=0)
////                b.setText(i+"");
////            else
////                b.setText("♕");
//        }


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
