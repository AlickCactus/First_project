package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            EditTextPreference countingPreference = findPreference("counting");

            if (countingPreference != null) {
                countingPreference.setSummaryProvider(new Preference.SummaryProvider<EditTextPreference>() {
                    @Override
                    public CharSequence provideSummary(EditTextPreference preference) {
                        String text = preference.getText();
                        if (TextUtils.isEmpty(text) || text == null){
                            return "Not set";
                        }
                        return "Length of saved value: " + text.length();
                    }
                });
            }
        }
    }
}