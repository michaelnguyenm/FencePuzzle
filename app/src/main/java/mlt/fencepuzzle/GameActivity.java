package mlt.fencepuzzle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.AlertDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    // Logging
    private static final String TAG = "Debug: GameActivity";

    // Settings member variables
    private boolean mMusicOn;
    private boolean mSoundOn;
    private int mTheme;

    // I think we need these??? ;w;
    private BoardView mBoardView;
    private Puzzle mPuzzle;
    private Level mLevel;

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
        // Aye aye, captain Michael! :D

        //***
        Intent myIntent = getIntent(); // gets the previously created intent
        int levelID = myIntent.getIntExtra("levelID", -1); // fetches level id from LevelSelectorActivity
        Log.d(TAG, "In GameActivity, intent extra is: " + levelID);
        mBoardView = (BoardView) findViewById(R.id.boardView);
        mLevel = new Level(levelID);
        mPuzzle = mLevel.puzzle;
        mBoardView.setLevel(mLevel);
        mBoardView.setPuzzle(mPuzzle);
        mBoardView.setOnTouchListener(mTouchListener);
        startNewGame();

    }

    public Fence getFenceAt(int index){
        return mPuzzle.getFenceAt(index);
    }

    private void startNewGame() {
        mPuzzle.resetPositions();
        mBoardView.invalidate();
        //TODO: reset a 'turns' counter
    }

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            Log.d(TAG, "In GameActivity. method mTouchListener. isCorrect: "+mPuzzle.isCorrect());
            if(!mPuzzle.isCorrect()) {

                int col = (int) event.getX() / mBoardView.getBoardFenceWidth();
                int row = (int) event.getY() / mBoardView.getBoardFenceWidth();
                int pos = row * 8 + col;
                if(pos<=64){
                Log.d(TAG, "In GameActivity. method mTouchListener. Pos: " +pos);
                mPuzzle.movePiece(pos);
                //rotate and redraw
                mBoardView.invalidate();
                //check correct, if so, toast!
                if (mPuzzle.isCorrect()) {
                    //toast
                    Toast.makeText(getApplicationContext(), "You did it! Go BACK and try another level!", Toast.LENGTH_LONG).show();
                }}
            }
            return false;
        }
    };

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

    public void setGameActivity(BoardView boardView) {
    }
}
