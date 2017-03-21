package mlt.fencepuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    // Buttons
    private Button mStartButton;
    private Button mInstructionButton;
    private Button mOptionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void chooseLevel(View view) {
        Intent intent = new Intent(this, LevelSelectorActivity.class);
        startActivity(intent);
    }
}
