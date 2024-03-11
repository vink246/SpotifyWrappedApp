package com.example.spotifywrapped;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsLightAltActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingslightalt2);

        // Button to navigate to SettingsDarkAltActivity2
        Button settingsDarkButton = findViewById(R.id.button33);
        settingsDarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsDarkAltActivity2 when the button is clicked
                startActivity(new Intent(SettingsLightAltActivity2.this, SettingsDarkAltActivity2.class));
            }
        });

        // Button to navigate to CreateAccountDarkActivity
        Button createAccountDarkButton = findViewById(R.id.button34);
        createAccountDarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CreateAccountDarkActivity when the button is clicked
                startActivity(new Intent(SettingsLightAltActivity2.this, CreateAccountActivity.class));
            }
        });
    }
}
