package com.example.spotifywrapped;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class DuoWrapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dark_duo); // Using dark layout

        // Setup button to navigate to WrapActivity
        Button wrapButton = findViewById(R.id.dark_duo_wrap);
        wrapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToWrapActivity();
            }
        });

        // Setup button to navigate to SettingsActivity
        Button settingsButton = findViewById(R.id.dark_duo_setting);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSettingsActivity();
            }
        });
    }

    // Navigate to WrapActivity
    private void navigateToWrapActivity() {
        Intent intent = new Intent(this, WrapActivity.class);
        startActivity(intent);
    }

    // Navigate to SettingsActivity
    private void navigateToSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
