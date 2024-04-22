package com.example.spotifywrapped.DarkActivities.Data;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.DarkActivities.Settings.SettingsDarkOneActivity;
import com.example.spotifywrapped.spotifyServices.SpotifyProvider;
import com.example.spotifywrapped.spotifyServices.SpotifyWrapped;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class LandingPageActivity extends AppCompatActivity {

    private Spinner spinnerTerms;
    private Button buttonProceed;
    private ProgressBar loadingProgressBar;

    SpotifyProvider provider;
    SpotifyWrapped wrapped;
    SpotifyProvider.WrappedTerm selectedItem = SpotifyProvider.WrappedTerm.short_term;

    private static boolean snackbarPrevShown = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landingpage);

        provider = SpotifyProvider.getInstance();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleBottomNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        // Initialize views
        spinnerTerms = findViewById(R.id.spinner);
        buttonProceed = findViewById(R.id.generateButton);
        loadingProgressBar = findViewById(R.id.loadingProgressBar); // Initialize ProgressBar

        // Populate spinner with term options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTerms.setAdapter(adapter);

        spinnerTerms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, android.view.View selectedItemView, int position, long id) {
                switch ((String) parentView.getItemAtPosition(position)) {
                    case "Short Term":
                        selectedItem = SpotifyProvider.WrappedTerm.short_term;
                        break;
                    case "Medium Term":
                        selectedItem = SpotifyProvider.WrappedTerm.medium_term;
                        break;
                    case "Long Term":
                        selectedItem = SpotifyProvider.WrappedTerm.long_term;
                        break;
                }
                // Now 'selectedItem' contains the value currently selected in the spinner
                Log.d("SpinnerSelection", "Selected item: " + selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle no selection
            }
        });

        // Retrieve the selected term from the intent extras
        String selectedTerm = getIntent().getStringExtra("selected_term");
        // Set the spinner selection based on the selected term
        if (selectedTerm != null) {
            int position = adapter.getPosition(selectedTerm);
            if (position != -1) {
                spinnerTerms.setSelection(position);
            }
        }

        // Set click listener for the button
        buttonProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show loading progress
                showLoading();

                // Proceed to WrappedDarkActivity regardless of term selection
                getWrapped();
            }
        });

        provider.getMyUserInfo(info -> {
            if (!info.isPremium() && !snackbarPrevShown) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "You don't have premium. Audio may be erratic", Snackbar.LENGTH_INDEFINITE);

                snackbar.setAction("Dismiss", v -> snackbar.dismiss()); // Add an action to dismiss the Snackbar when clicked
                snackbar.show();
                snackbarPrevShown = true;
            }
        });
    }

    private void showLoading() {
        loadingProgressBar.setVisibility(View.VISIBLE); // Show loading ProgressBar
        buttonProceed.setEnabled(false); // Disable the button during loading
    }

    private void hideLoading() {
        loadingProgressBar.setVisibility(View.GONE); // Hide loading ProgressBar
        buttonProceed.setEnabled(true); // Enable the button after loading is complete
    }

    private synchronized void getWrapped() {
        provider.getTopTracks(5, 0, selectedItem, topTracks -> {
            if (topTracks != null) {
                // populate tracks
                provider.getTopArtists(5, 0, selectedItem, topArtists -> {
                    if (topArtists != null) {
                        provider.getMyUserInfo(info -> {
                            if (info != null) {
                                if (topTracks.size() < 5 || topArtists.size() < 5) {
                                    Toast.makeText(getApplicationContext(), "You haven't listened to enough songs for this term!",
                                            Toast.LENGTH_SHORT).show();
                                    hideLoading();
                                } else {
                                    // by the time we are here, we should have top tracks, artists, and user info
                                    wrapped = new SpotifyWrapped(
                                            topTracks,
                                            topArtists,
                                            selectedItem,
                                            info.getUsername()
                                    );
                                    hideLoading(); // Hide loading after data is ready
                                    startActivityWithSelectedTerm();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    // Start WrappedDarkActivity with the selected term
    private void startActivityWithSelectedTerm() {
        Intent intent = new Intent(LandingPageActivity.this, WrapSwipeActivity.class);
        // Pass the selected term to WrappedDarkActivity
        intent.putExtra("selected_term", spinnerTerms.getSelectedItem().toString());
        intent.putExtra("wrapped", wrapped);
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
