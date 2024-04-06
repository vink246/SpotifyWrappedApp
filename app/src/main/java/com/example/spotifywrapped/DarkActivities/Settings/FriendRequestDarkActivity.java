package com.example.spotifywrapped.DarkActivities.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.DarkActivities.Data.FriendArtistCompDarkActivity;
import com.example.spotifywrapped.DarkActivities.Data.WrappedDarkActivity;
import com.example.spotifywrapped.R;

public class FriendRequestDarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendrequest);

        // Find the Check Friend Requests button by its id
        Button checkFriendRequestsButton = findViewById(R.id.checkFriendRequestsButton);

        // Set OnClickListener for the Check Friend Requests button
        checkFriendRequestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to CheckFriendDarkActivity
                startActivity(new Intent(FriendRequestDarkActivity.this, CheckFriendDarkActivity.class));
            }
        });

        // Find the Exit button by its id
        Button exitButton = findViewById(R.id.button9);

        // Set OnClickListener for the Exit button
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to WrappedDarkActivity
                startActivity(new Intent(FriendRequestDarkActivity.this, WrappedDarkActivity.class));
                // Finish the current activity
                finish();
            }
        });
    }
}
