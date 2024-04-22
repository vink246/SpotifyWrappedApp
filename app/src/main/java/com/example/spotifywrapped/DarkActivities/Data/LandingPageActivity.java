package com.example.spotifywrapped.DarkActivities.Data;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.DarkActivities.Settings.SettingsDarkOneActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LandingPageActivity extends AppCompatActivity {

    private Spinner spinnerTerms;
    private Button buttonProceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landingpage);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleBottomNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        // Initialize views
        spinnerTerms = findViewById(R.id.spinner);
        buttonProceed = findViewById(R.id.generateButton);

        // Populate spinner with term options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTerms.setAdapter(adapter);

        // Set click listener for the button
        buttonProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Proceed to WrappedDarkActivity regardless of term selection
                startActivityWithSelectedTerm();
            }
        });
    }

    // Start WrappedDarkActivity with the selected term
    private void startActivityWithSelectedTerm() {
        Intent intent = new Intent(LandingPageActivity.this, WrappedDarkActivity.class);
        // Pass the selected term to WrappedDarkActivity
        intent.putExtra("selected_term", spinnerTerms.getSelectedItem().toString());
        startActivity(intent);
        finish(); // Finish this activity to prevent returning to it when pressing back
    }

    private boolean handleBottomNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_home) {
            // Do nothing as we are already in WrappedDarkActivity
        } else if (itemId == R.id.navigation_history) {
            //provider.pauseCurrentTrack();
            startActivity(new Intent(this, PastWrapDarkActivity.class));
            finish();
        } else if (itemId == R.id.navigation_language) {
            //provider.pauseCurrentTrack();
            startActivity(new Intent(this, PublicWrapDarkActivity.class));
            finish();
        } else if (itemId == R.id.navigation_settings) {
            //provider.pauseCurrentTrack();
            startActivity(new Intent(this, SettingsDarkOneActivity.class));
            finish();
        }
        return true;
    }
}
