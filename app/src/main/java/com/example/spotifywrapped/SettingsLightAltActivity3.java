package com.example.spotifywrapped;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsLightAltActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingslightalt3);

        // Button to navigate to SettingsDarkAltActivity3
        Button settingsDarkButton = findViewById(R.id.button35);
        settingsDarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsDarkAltActivity3 when the button is clicked
                startActivity(new Intent(SettingsLightAltActivity3.this, SettingsDarkAltActivity3.class));
            }
        });

        // Button to navigate to UpdateAccountActivity
        Button updateAccountButton = findViewById(R.id.button36);
        updateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start UpdateAccountActivity when the button is clicked
                startActivity(new Intent(SettingsLightAltActivity3.this, UpdateAccountActivity.class));
            }
        });
    }
}
