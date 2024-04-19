package com.example.spotifywrapped.DarkActivities.Data;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifywrapped.DarkActivities.Settings.SettingsDarkOneActivity;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.firebaseServices.FirebaseProvider;
import com.example.spotifywrapped.models.Artist;
import com.example.spotifywrapped.models.Track;
import com.example.spotifywrapped.models.Wrap;
import com.example.spotifywrapped.spotifyServices.SpotifyProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PastWrapDarkActivity extends AppCompatActivity implements DateBlockAdapter.OnDateBlockClickListener {

    private RecyclerView recyclerViewDateBlocks;
    private DateBlockAdapter adapter;
    private List<String> dateRanges = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_wrap_dark);

        // Initialize RecyclerView for date blocks
        recyclerViewDateBlocks = findViewById(R.id.recycler_view_date_blocks);
        recyclerViewDateBlocks.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve and populate date ranges
        retrieveAndPopulateDateRanges();

        // Setup bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleBottomNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.navigation_history);
    }

    // Method to retrieve and populate date ranges
    private void retrieveAndPopulateDateRanges() {
        SpotifyProvider.getInstance().getMyUserInfo(info -> {
            FirebaseProvider.getInstance().getSavedWraps(info.getUsername(), wraps -> {
                for (Wrap wrap : wraps.getResult()) {
                    // Extract date from Wrap
                    String[] idParts = wrap.getSummaryId().split(" ");
                    String date = idParts[1];
                    LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
                    String startDate = "unknown";
                    // Determine start date based on timespan
                    switch (wrap.getTimespan()) {
                        case long_term:
                            startDate = parsedDate.minusYears(1).format(DateTimeFormatter.ISO_DATE);
                            break;
                        case medium_term:
                            startDate = parsedDate.minusMonths(6).format(DateTimeFormatter.ISO_DATE);
                            break;
                        case short_term:
                            startDate = parsedDate.minusWeeks(4).format(DateTimeFormatter.ISO_DATE);
                            break;
                    }
                    String dateRange = startDate + " to " + date;

                    // Check if the date range is already in the list
                    if (!dateRanges.contains(dateRange)) {
                        dateRanges.add(dateRange);
                    }
                }

                // Initialize and set adapter for RecyclerView
                adapter = new DateBlockAdapter(dateRanges);
                adapter.setOnDateBlockClickListener(this);
                recyclerViewDateBlocks.setAdapter(adapter);
            });
        });
    }

    // Method to handle bottom navigation item selection
    private boolean handleBottomNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_history) {
            return true; // Already in this activity, do nothing
        } else if (itemId == R.id.navigation_home) {
            startActivity(new Intent(this, WrappedDarkActivity.class));
            finish();
            return true;
        } else if (itemId == R.id.navigation_group) {
            startActivity(new Intent(this, FriendArtistCompDarkActivity.class));
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

    // Method to handle click on date block
    @Override
    public void onDateBlockClick(String dateRange) {
        // Retrieve the data for the selected date range
        SpotifyProvider.getInstance().getMyUserInfo(info -> {
            FirebaseProvider.getInstance().getSavedWraps(info.getUsername(), wraps -> {
                for (Wrap wrap : wraps.getResult()) {
                    String[] idParts = wrap.getSummaryId().split(" ");
                    String wrapDateRange = idParts[1];
                    LocalDate parsedDate = LocalDate.parse(wrapDateRange, DateTimeFormatter.ISO_DATE);
                    String startDate = "unknown";
                    switch (wrap.getTimespan()) {
                        case long_term:
                            startDate = parsedDate.minusYears(1).format(DateTimeFormatter.ISO_DATE);
                            break;
                        case medium_term:
                            startDate = parsedDate.minusMonths(6).format(DateTimeFormatter.ISO_DATE);
                            break;
                        case short_term:
                            startDate = parsedDate.minusWeeks(4).format(DateTimeFormatter.ISO_DATE);
                            break;
                    }
                    String fullDateRange = startDate + " to " + wrapDateRange;

                    // Check if the retrieved wrap corresponds to the selected date range
                    if (dateRange.equals(fullDateRange)) {
                        // Extract artist names from Artist objects
                        List<String> artistNames = new ArrayList<>();
                        for (Artist artist : wrap.getArtists()) {
                            artistNames.add(artist.getName());
                        }

                        // Extract track names from Track objects
                        List<String> trackNames = new ArrayList<>();
                        for (Track track : wrap.getTracks()) {
                            trackNames.add(track.getName());
                        }

                        // Populate the popup with data
                        showPopup(fullDateRange, artistNames, trackNames);
                        break; // Stop looping once the data for the selected date range is found
                    }
                }
            });
        });
    }

    // Method to show the popup with wrap details
    private void showPopup(String dateRange, List<String> artistNames, List<String> trackNames) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.wrapped_item, null);
        builder.setView(dialogView);

        // Access views from the inflated layout
        TextView textViewDateRange = dialogView.findViewById(R.id.textViewDateRange);
        LinearLayout layoutTopArtists = dialogView.findViewById(R.id.topArtists);
        LinearLayout layoutTopSongs = dialogView.findViewById(R.id.topSongs);

        // Populate the layout with data
        textViewDateRange.setText(dateRange);

        // Populate artists
        for (String artistName : artistNames) {
            TextView textViewArtist = new TextView(this);
            textViewArtist.setText(artistName);
            layoutTopArtists.addView(textViewArtist);
        }

        // Populate songs
        for (String trackName : trackNames) {
            TextView textViewTrack = new TextView(this);
            textViewTrack.setText(trackName);
            layoutTopSongs.addView(textViewTrack);
        }

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        // Allow dismissal by touching outside
        alertDialog.setCanceledOnTouchOutside(true);

        // Add close button programmatically
        Button buttonClose = new Button(this);
        buttonClose.setText("Close");
        buttonClose.setTextColor(getResources().getColor(android.R.color.white));
        buttonClose.setBackgroundResource(R.color.purple);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 16, 0, 0);
        buttonClose.setTypeface(getResources().getFont(R.font.univers));
        buttonClose.setLayoutParams(layoutParams);
        buttonClose.setOnClickListener(view -> alertDialog.dismiss());
        ((LinearLayout) dialogView).addView(buttonClose);

        alertDialog.show();
    }

}
