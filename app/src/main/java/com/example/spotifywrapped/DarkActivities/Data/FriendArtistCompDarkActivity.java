package com.example.spotifywrapped.DarkActivities.Data;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.DarkActivities.Settings.FriendRequestDarkActivity;
import com.example.spotifywrapped.DarkActivities.Settings.SettingsDarkOneActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleBottomNavigationItemSelected);

        // Set the selected item in the bottom navigation view
        bottomNavigationView.setSelectedItemId(R.id.navigation_group);
    }

    private boolean handleBottomNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_group) {
            // Do nothing as we are already in FriendArtistCompDarkActivity
            return true;
        } else if (itemId == R.id.navigation_home) {
            startActivity(new Intent(this, WrappedDarkActivity.class));
            finish();
            return true;
        } else if (itemId == R.id.navigation_history) {
            startActivity(new Intent(this, PastWrapDarkActivity.class));
            finish();
            return true;
        } else if (itemId == R.id.navigation_language) {
            startActivity(new Intent(this, PublicWrapDarkActivity.class));
            finish();
            return true;
        } else if (itemId == R.id.navigation_settings) {
            startActivity(new Intent(this, SettingsDarkOneActivity.class));
            finish();
            return true;
        }
        return false;
    }
}
