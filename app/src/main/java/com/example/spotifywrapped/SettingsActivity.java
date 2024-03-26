package com.example.spotifywrapped;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import Utility.DarkModePreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check and set the layout based on the dark mode setting
        if (DarkModePreferenceManager.isDarkMode(getApplicationContext())) {
            setContentView(R.layout.dark_settings);
            // Setup button in dark mode to switch to light mode
            setupModeToggle(R.id.dark_light_mode_button, false);
            // Setup exit button in dark mode
            setupExitButton(R.id.dark_exit_button);
        } else {
            setContentView(R.layout.light_settings);
            // Setup button in light mode to switch to dark mode
            setupModeToggle(R.id.light_dark_mode_button, true);
            // Setup exit button in light mode
            setupExitButton(R.id.light_exit_button);
        }
    }

    private void setupModeToggle(int buttonId, boolean setDarkMode) {
        Button modeToggleButton = findViewById(buttonId);
        modeToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the dark mode preference and recreate the activity to reflect the change
                DarkModePreferenceManager.setDarkMode(getApplicationContext(), setDarkMode);
                recreate();
            }
        });
    }

    private void setupExitButton(int buttonId) {
        Button exitButton = findViewById(buttonId);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity to return to the previous screen
                finish();
            }
        });
    }
}
