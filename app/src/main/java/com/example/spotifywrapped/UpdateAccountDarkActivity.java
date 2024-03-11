package com.example.spotifywrapped;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateAccountDarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_account_dark);

        // Button to navigate to SettingsDarkAltActivity1
        Button settingsButton1 = findViewById(R.id.button30);
        settingsButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsDarkAltActivity1 when the button is clicked
                startActivity(new Intent(UpdateAccountDarkActivity.this, SettingsDarkAltActivity3.class));
            }
        });

        // Button to navigate to LoginDarkActivity
        Button loginButton = findViewById(R.id.button32);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginDarkActivity when the button is clicked
                startActivity(new Intent(UpdateAccountDarkActivity.this, LoginDarkActivity.class));
            }
        });
    }
}
