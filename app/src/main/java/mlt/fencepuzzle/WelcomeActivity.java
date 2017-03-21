package mlt.fencepuzzle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    // Buttons
    private Button mStartButton;
    private Button mInstructionButton;
    private Button mOptionButton;

    // Settings
    private static int SETTINGS_REQUEST = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_REQUEST) {
            SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
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


}
