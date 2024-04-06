package com.example.spotifywrapped.DarkActivities.Data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.DarkActivities.Settings.FriendRequestDarkActivity;

public class FriendArtistCompDarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendartcompdark);

        // Find the button by its id
        Button inviteFriendButton = findViewById(R.id.inviteFriendButton);

        // Set a click listener on the button
        inviteFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the button is clicked, start FriendRequestDarkActivity
                Intent intent = new Intent(FriendArtistCompDarkActivity.this, FriendRequestDarkActivity.class);
                startActivity(intent);
            }
        });
    }
}
