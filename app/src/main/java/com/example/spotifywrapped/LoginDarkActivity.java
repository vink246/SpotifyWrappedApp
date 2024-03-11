package com.example.spotifywrapped;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginDarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logindark);

        // Button to navigate to SettingsDarkAltActivity1
        Button settingsButton = findViewById(R.id.button7);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsDarkAltActivity1 when the button is clicked
                startActivity(new Intent(LoginDarkActivity.this, SettingsDarkAltActivity1.class));
            }
        });

        // Button to navigate to CreateAccountDarkActivity
        Button createAccountButton = findViewById(R.id.button9);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CreateAccountDarkActivity when the button is clicked
                startActivity(new Intent(LoginDarkActivity.this, CreateAccountDarkActivity.class));
            }
        });

        // Button to navigate to UpdateAccountDarkActivity
        Button updateAccountButton = findViewById(R.id.button10);
        updateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start UpdateAccountDarkActivity when the button is clicked
                startActivity(new Intent(LoginDarkActivity.this, UpdateAccountDarkActivity.class));
            }
        });

        // Button to navigate to DeleteAccountDarkActivity
        Button deleteAccountButton = findViewById(R.id.button12);
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DeleteAccountDarkActivity when the button is clicked
                startActivity(new Intent(LoginDarkActivity.this, DeleteAccountDarkActivity.class));
            }
        });
    }
}
