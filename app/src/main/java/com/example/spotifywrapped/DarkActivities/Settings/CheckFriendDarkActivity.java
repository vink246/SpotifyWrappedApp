package com.example.spotifywrapped.DarkActivities.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;

public class CheckFriendDarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkfriendreq);

        // Find the Go Back button
        Button buttonGoBack = findViewById(R.id.buttonGoBack);

        // Set OnClickListener for the Go Back button
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to FriendRequestDarkActivity
                startActivity(new Intent(CheckFriendDarkActivity.this, FriendRequestDarkActivity.class));
                finish(); // Optional: finish the current activity
            }
        });
    }
}
