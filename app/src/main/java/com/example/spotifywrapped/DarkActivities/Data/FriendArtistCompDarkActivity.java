package com.example.spotifywrapped.DarkActivities.Data;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.DarkActivities.Settings.SettingsDarkOneActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FriendArtistCompDarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendartcompdark);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleBottomNavigationItemSelected);

        bottomNavigationView.setSelectedItemId(R.id.navigation_group);
    }

    private boolean handleBottomNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_group) {
            return true; // Already in this activity, do nothing
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
