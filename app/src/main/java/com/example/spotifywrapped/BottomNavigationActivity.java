package com.example.spotifywrapped;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

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
                // Handle Home selection
                return true;
            } else if (itemId == R.id.navigation_group) {
                // Handle Group selection
                return true;
            } else if (itemId == R.id.navigation_history) {
                // Handle History selection
                return true;
            } else if (itemId == R.id.navigation_language) {
                // Handle Language selection
                return true;
            } else if (itemId == R.id.navigation_settings) {
                // Handle Settings selection
                return true;
            }
            return false;
        });
    }

    protected abstract void handleBottomNavigationItemSelected(MenuItem item);
}

