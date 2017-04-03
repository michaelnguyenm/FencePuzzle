package mlt.fencepuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LevelSelectorActivity extends AppCompatActivity {

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

    private static final String TAG = "LevelSelect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selector);
    }

    public void selectLevel(View view){
        Button b = (Button) view;
        Log.d(TAG, "" + Integer.parseInt(b.getText().toString()));
    }

    public void selectLevelOne(View view) {
        Button b = (Button) view;
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("levelID", Integer.parseInt(b.getText().toString()));
        startActivity(intent);
    }

}
