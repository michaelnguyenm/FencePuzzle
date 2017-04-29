package mlt.fencepuzzle;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.plattysoft.leonids.ParticleSystem;

import static mlt.fencepuzzle.R.drawable.confetti;

public class GameActivity extends AppCompatActivity {

    // Logging
    private static final String TAG = "Debug: GameActivity";

    // Settings member variables
    private boolean mMusicOn;
    private boolean mSoundOn;
    private boolean mVibration;
    private int mTheme;
    private int mTapCount;
    private int mPrevCount;

    // I think we need these??? ;w;
    private BoardView mBoardView;
    private Puzzle mPuzzle;
    private Level mLevel;

    private Vibrator mVibrator;

    private MediaPlayer mpBackground;
    private MediaPlayer mpSound;

    // Settings
    private static int SETTINGS_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTapCount = 0;
        // Load level
        Intent myIntent = getIntent(); // gets the previously created intent
        int levelID = myIntent.getIntExtra("levelID", -1); // fetches level id from LevelSelectorActivity
        loadLevel(levelID);

        // Background color
        setColor();

        // Background music
        mpBackground = MediaPlayer.create(this, R.raw.game_music);
        mpBackground.setLooping(true);
        playMusic();

        // Sound effects
        mpSound = MediaPlayer.create(this, R.raw.test_click_short);

        // Vibrator
        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.reset_game:
                loadLevel(getLevel());
                return true;
            default:
                return false;
        }
    }

    public void loadLevel(int levelID) {
        Log.d(TAG, "In GameActivity, loading level: " + levelID);
        mBoardView = (BoardView) findViewById(R.id.boardView);
        mLevel = new Level(this, levelID);
        mPuzzle = mLevel.puzzle;
        mBoardView.setLevel(mLevel);
        mBoardView.setPuzzle(mPuzzle);
        mBoardView.setOnTouchListener(mTouchListener);
        startNewGame();

        // Get settings
        setInstanceVarsFromSharedPrefs();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        mpBackground.stop();
        mpBackground.release();
    }

    public Fence getFenceAt(int index){
        return mPuzzle.getFenceAt(index);
    }

    private void startNewGame() {
        mTapCount = 0;
        TextView textView = (TextView) findViewById(R.id.tap_count);
        textView.setText(Integer.toString(mTapCount));
        mPuzzle.resetPositions();
        mBoardView.invalidate();
    }

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            Log.d(TAG, "In GameActivity. method mTouchListener. isCorrect: "+mPuzzle.isCorrect());
            if(!mPuzzle.isCorrect()) {
                int col = (int) event.getX() / mBoardView.getBoardFenceWidth();
                int row = (int) event.getY() / mBoardView.getBoardFenceWidth();
                int pos = row * 8 + col;
                // Ensure that presses are within the board view
                if (pos <= 64) {
                    //Confirmed that puzzle is still not solved and click is within board
                    mTapCount++;
                    TextView textView = (TextView) findViewById(R.id.tap_count);
                    textView.setText(Integer.toString(mTapCount));
                    // Play sound
                    if(mSoundOn) {
                        if(mpSound.isPlaying()) {
                            mpSound.seekTo(0);
                        }
                        mpSound.start();
                    }
                    // Do haptic feedback
                    if(mVibration) {
                        mVibrator.cancel();
                        mVibrator.vibrate(1);
                    }

                    Log.d(TAG, "In GameActivity. method mTouchListener. Pos: " +pos);
                    mPuzzle.movePiece(pos);
                    //rotate and redraw
                    mBoardView.invalidate();
                    //check correct, if so, toast!
                    if (mPuzzle.isCorrect()) {
                        //Fragment
                        FragmentManager fm = getFragmentManager();
                        WinDialogFragment winDialogFragment = new WinDialogFragment();
                        winDialogFragment.show(fm, "win");
                        //winDialogFragment.getDialog().setCanceledOnTouchOutside(false);
                        //Particles
                        new ParticleSystem((Activity) v.getContext(), 30, confetti, 10000)
                                .setSpeedRange(0.2f, 0.5f)
                                .oneShot(v, 30);
                        //Save result
                        SharedPreferences sharedPref = getSharedPreferences("FencePuzzle", MODE_PRIVATE);
                        String currLevel = Integer.toString(getLevel());
                        int lastScore = sharedPref.getInt(currLevel, -1);
                        if (lastScore <= 0 || mTapCount < lastScore ) {
                            if (mTapCount < lastScore) {
                                SharedPreferences.Editor ed = sharedPref.edit();
                                ed.putInt(currLevel, mTapCount);
                                ed.apply();
                            }
                        }
                    }
                }
            }
            return false;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_REQUEST) {
            setInstanceVarsFromSharedPrefs();
            playMusic();
            setColor();
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
        mPrevCount = sharedPref.getInt(Integer.toString(getLevel()), -1);

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
        mVibration = sharedPref.getBoolean("vibration", true);

        Log.d(TAG, "Sound is: " + mSoundOn);
        Log.d(TAG, "Theme is: " + mTheme + ": " + themeName);
        Log.d(TAG, "Music is: " + mMusicOn);
        Log.d(TAG, "Vibration is: " + mVibration);
    }

    public int getLevel () {
        return mLevel.getLevelID();
    }

    private void playMusic() {
        if(mMusicOn) {
            mpBackground.start();
        }
    }

    private void stopMusic() {
        if(mMusicOn) {
            mpBackground.pause();
        }
    }

    private void setColor() {
        CoordinatorLayout cl = (CoordinatorLayout)findViewById(R.id.gameLayout);
        switch(mTheme) {
            case 0:
                cl.setBackgroundColor(Color.WHITE);
                break;
            case 1:
                cl.setBackgroundColor(Color.BLACK);
                break;
            default:
                cl.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    public int getmPrevCount() {
        return mPrevCount;
    }

    public int getmTapCount() {
        return mTapCount;
    }
}
