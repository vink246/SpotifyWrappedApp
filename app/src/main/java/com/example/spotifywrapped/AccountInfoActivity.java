package com.example.spotifywrapped;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.DarkActivities.Settings.SettingsDarkOneActivity;
import com.example.spotifywrapped.spotifyServices.SpotifyProvider;

public class AccountInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountinfo); // Set the content view to the XML layout

        // Initialize button4 and set OnClickListener to navigate to SettingsDarkOneActivity
        Button settingsButton = findViewById(R.id.button4);
        Button editAccountButton = findViewById(R.id.button2);

        TextView usernameView = findViewById(R.id.usernameTextView);
        TextView emailView = findViewById(R.id.emailTextView);

        SpotifyProvider.getInstance().getMyUserInfo(info -> {
            usernameView.setText(info.getUsername());
            emailView.setText(info.getEmail());
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsDarkOneActivity
                Intent intent = new Intent(AccountInfoActivity.this, SettingsDarkOneActivity.class);
                startActivity(intent);
            }
        });

        editAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to spotify settings
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("spotify:internal:preferences"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.spotify.music"); // Specify the package name of the Spotify app
                startActivity(intent);
            }
        });
    }
}
