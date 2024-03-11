package com.example.spotifywrapped;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsLightAltActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingslightalt4);

        // Button to navigate to SettingsDarkAltActivity4
        Button settingsDarkButton = findViewById(R.id.button37);
        settingsDarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsDarkAltActivity4 when the button is clicked
                startActivity(new Intent(SettingsLightAltActivity4.this, SettingsDarkAltActivity4.class));
            }
        });

        // Button to navigate to DeleteAccountActivity
        Button deleteAccountButton = findViewById(R.id.button38);
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DeleteAccountActivity when the button is clicked
                startActivity(new Intent(SettingsLightAltActivity4.this, DeleteAccountActivity.class));
            }
        });
    }
}
