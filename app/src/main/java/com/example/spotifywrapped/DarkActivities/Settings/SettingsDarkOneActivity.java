package com.example.spotifywrapped.DarkActivities.Settings;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
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

    private CompoundButton.OnCheckedChangeListener switchListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // Detach the listener to avoid recursion
            switch4.setOnCheckedChangeListener(null);

            // Create the AlertDialog builder
            AlertDialog.Builder builder = new AlertDialog.Builder(SettingsDarkOneActivity.this);
            builder.setTitle("Confirm Action");
            builder.setMessage("Are you sure you want to change your profile to " + (isChecked ? "Public" : "Private") + "?");

            // Add the buttons
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked Confirm button
                    switch4.setText(isChecked ? "Public" : "Private");
                    FirebaseProvider.getInstance().setUserPublic(username, isChecked);
                    // Reattach the listener
                    switch4.setOnCheckedChangeListener(switchListener);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog, revert the switch state
                    switch4.setChecked(!isChecked);
                    // Reattach the listener
                    switch4.setOnCheckedChangeListener(switchListener);
                }
            });

            // Create and show the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

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
                // Set listeners to switches
                switch4.setOnCheckedChangeListener(switchListener);
            });
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
