package com.example.spotifywrapped;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        // Button to navigate to SettingsLightAltActivity1
        Button settingsButton1 = findViewById(R.id.button13);
        settingsButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsLightAltActivity1 when the button is clicked
                startActivity(new Intent(CreateAccountActivity.this, SettingsLightAltActivity2.class));
            }
        });

        // Button to navigate to LoginActivity
        Button loginButton = findViewById(R.id.button17);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity when the button is clicked
                startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));
            }
        });
    }
}
