package mlt.fencepuzzle;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Display fragment as the main content
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragment {

        private SharedPreferences sharedPrefs;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            getPreferenceManager().setSharedPreferencesName("FencePuzzle");
            sharedPrefs = getActivity().getSharedPreferences("FencePuzzle", MODE_PRIVATE);

            addPreferencesFromResource(R.xml.preferences);

            updateThemeSummary();
            setThemeListener();

            //setResetListener();
        }

        private void setThemeListener() {
            final Preference themePref = findPreference("theme_option");
            themePref.setOnPreferenceChangeListener(
                    new Preference.OnPreferenceChangeListener() {

                        @Override
                        public boolean onPreferenceChange(Preference preference, Object newValue) {
                            String themeSummary = "Current theme: " + newValue;
                            themePref.setSummary(themeSummary);

                            SharedPreferences.Editor ed = sharedPrefs.edit();
                            ed.putString("theme_option", newValue.toString());
                            ed.apply();
                            return true;
                        }
                    });
        }

        private void updateThemeSummary() {
            String themeSummary = "Current theme: " +
                    sharedPrefs.getString("theme_option",
                            getString(R.string.white));

            Preference themeMessagePref = findPreference("theme_option");
            themeMessagePref.setSummary(themeSummary);
        }
/*
        private void setResetListener() {
            final Preference resetPref = findPreference("reset_option");
        }
        */
    }
}