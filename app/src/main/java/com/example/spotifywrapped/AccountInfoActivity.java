package com.example.spotifywrapped;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.DarkActivities.Settings.SettingsDarkOneActivity;

public class AccountInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountinfo); // Set the content view to the XML layout

        // Initialize button4 and set OnClickListener to navigate to SettingsDarkOneActivity
        Button settingsButton = findViewById(R.id.button4);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsDarkOneActivity
                Intent intent = new Intent(AccountInfoActivity.this, SettingsDarkOneActivity.class);
                startActivity(intent);
            }
        });
    }
}
