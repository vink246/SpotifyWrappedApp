package com.example.spotifywrapped;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsLightAltActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingslightalt1);

        // Button to navigate to SettingsDarkAltActivity
        Button settingsDarkButton = findViewById(R.id.button5);
        settingsDarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsDarkAltActivity when the button is clicked
                startActivity(new Intent(SettingsLightAltActivity1.this, SettingsDarkAltActivity1.class));
            }
        });

        // Button to navigate to LoginActivity
        Button loginButton = findViewById(R.id.button6);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity when the button is clicked
                startActivity(new Intent(SettingsLightAltActivity1.this, LoginActivity.class));
            }
        });
    }
}
