package com.example.spotifywrapped.DarkActivities.Settings;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.AccountInfoActivity;
import com.example.spotifywrapped.DarkActivities.Data.WrappedDarkActivity;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.firebaseServices.FirebaseProvider;
import com.example.spotifywrapped.spotifyServices.SpotifyProvider;

public class SettingsDarkOneActivity extends AppCompatActivity {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switch1;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switch2;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switch3;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switch4;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsdark1);

        // Initialize switches
        switch4 = findViewById(R.id.switch4);

        SpotifyProvider.getInstance().getMyUserInfo(info -> {
            username = info.getUsername();
            FirebaseProvider.getInstance().getUserPublic(info.getUsername(), isPublic -> {
                switch4.setText(isPublic.getResult()? "Public" : "Private");
                switch4.setChecked(isPublic.getResult());
            });
        });

        // Set listeners to switches
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch4.setText(isChecked ? "Public" : "Private");
                FirebaseProvider.getInstance().setUserPublic(username, isChecked);
            }
        });

        // Initialize button and set OnClickListener
        Button myAccountButton = findViewById(R.id.button);
        myAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the accountinfo activity
                Intent intent = new Intent(SettingsDarkOneActivity.this, AccountInfoActivity.class);
                startActivity(intent);
            }
        });

        // Initialize button3 and set OnClickListener to navigate back to WrappedDarkActivity
        Button backButton = findViewById(R.id.button3);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to WrappedDarkActivity
                startActivity(new Intent(SettingsDarkOneActivity.this, WrappedDarkActivity.class));
                finish(); // Finish current activity to prevent navigating back to it with the back button
            }
        });
    }
}
