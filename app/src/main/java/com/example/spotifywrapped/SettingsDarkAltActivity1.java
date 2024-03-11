package com.example.spotifywrapped;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsDarkAltActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsdarkalt1);

        // Button to navigate to SettingsLightAltActivity
        Button settingsLightButton = findViewById(R.id.button14);
        settingsLightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsLightAltActivity when the button is clicked
                startActivity(new Intent(SettingsDarkAltActivity1.this, SettingsLightAltActivity1.class));
            }
        });

        // Button to navigate to LoginActivity
        Button loginButton = findViewById(R.id.button15);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity when the button is clicked
                startActivity(new Intent(SettingsDarkAltActivity1.this, LoginDarkActivity.class));
            }
        });
    }
}
