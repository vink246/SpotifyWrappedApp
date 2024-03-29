package com.example.spotifywrapped;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.DarkActivities.Data.FriendArtistCompDarkActivity;
import com.example.spotifywrapped.DarkActivities.Data.PastWrapDarkActivity;
import com.example.spotifywrapped.DarkActivities.Data.PublicWrapDarkActivity;
import com.example.spotifywrapped.DarkActivities.Data.WrappedDarkActivity;
import com.example.spotifywrapped.DarkActivities.Settings.SettingsDarkOneActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BottomNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_bar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(this, WrappedDarkActivity.class));
                return true;
            } else if (itemId == R.id.navigation_group) {
                startActivity(new Intent(this, FriendArtistCompDarkActivity.class));
                return true;
            } else if (itemId == R.id.navigation_history) {
                startActivity(new Intent(this, PastWrapDarkActivity.class));
                return true;
            } else if (itemId == R.id.navigation_language) {
                startActivity(new Intent(this, PublicWrapDarkActivity.class));
                return true;
            } else if (itemId == R.id.navigation_settings) {
                startActivity(new Intent(this, SettingsDarkOneActivity.class));
                return true;
            }
            return false;
        });
    }

    protected abstract void handleBottomNavigationItemSelected(MenuItem item);
}
