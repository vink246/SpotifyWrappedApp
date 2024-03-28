package com.example.spotifywrapped;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dark_settings);

        // Setup button to go back to the previous activity
        setupExitButton(R.id.dark_exit_button);
    }

    private void setupExitButton(int buttonId) {
        Button exitButton = findViewById(buttonId);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity to return to the previous screen
                onBackPressed();
            }
        });
    }
}