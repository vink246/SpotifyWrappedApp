package com.example.spotifywrapped.DarkActivities.Data;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PublicWrapDarkActivity extends AppCompatActivity implements DateBlockAdapter2.OnDateBlockClickListener {

    private RecyclerView recyclerViewDateBlocks;
    private DateBlockAdapter2 adapter;
    private List<String> dateRanges = new ArrayList<>();
    private List<String> filteredDateRanges = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_wrap_dark);
        // Initialize RecyclerView for date blocks
        recyclerViewDateBlocks = findViewById(R.id.recycler_view_date_blocks);
        recyclerViewDateBlocks.setLayoutManager(new LinearLayoutManager(this));
        // Retrieve and populate date ranges
        retrieveAndPopulateDateRanges();
        // Setup search functionality
        EditText searchEditText = findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                filterDateRanges(editable.toString());
            }
        });
        // Setup bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleBottomNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.navigation_language);
    }
    // Method to retrieve and populate date ranges
    private void retrieveAndPopulateDateRanges() {
        FirebaseProvider.getInstance().getPublicWraps(wraps -> {
            for (Wrap wrap : wraps) {
                // Extract date and username from Wrap summary ID
                String[] idParts = wrap.getSummaryId().split(" ");
                String username = idParts[0];
                String date = idParts[1];
                LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
                String startDate = "unknown";
                // Determine start date based on time-span.
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                date = parsedDate.format(formatter);
                startDate = LocalDate.parse(startDate).format(formatter);
                String dateRange = username + "\n" + startDate + " - " + date;
                dateRanges.add(dateRange);
            }
            // Set original date ranges to filtered date ranges initially
            filteredDateRanges.addAll(dateRanges);
            // Initialize and set adapter for RecyclerView
            adapter = new DateBlockAdapter2(filteredDateRanges);
            adapter.setOnDateBlockClickListener(this);
            recyclerViewDateBlocks.setAdapter(adapter);
        });
    }
    // Method to filter date ranges based on search query
    private void filterDateRanges(String query) {
        filteredDateRanges.clear();
        if (TextUtils.isEmpty(query)) {
            // If the query is empty, show all date ranges
            filteredDateRanges.addAll(dateRanges);
        } else {
            // Iterate through all date ranges to find matches
            for (String dateRange : dateRanges) {
                if (dateRange.toLowerCase().contains(query.toLowerCase())) {
                    filteredDateRanges.add(dateRange);
                }
            }
        }
        // Update RecyclerView with filtered data
        adapter.notifyDataSetChanged();
    }

    // Method to handle bottom navigation item selection
    private boolean handleBottomNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_history) {
            startActivity(new Intent(this, PastWrapDarkActivity.class));
            finish();
            return true;
        } else if (itemId == R.id.navigation_home) {
            startActivity(new Intent(this, WrappedDarkActivity.class));
            finish();
            return true;
        } else if (itemId == R.id.navigation_group) {
            startActivity(new Intent(this, FriendArtistCompDarkActivity.class));
            finish();
            return true;
        } else if (itemId == R.id.navigation_language) {
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
        // Split the dateRange string to extract only the date range since username is added here for public wraps
        String[] parts = dateRange.split("\n");
        // Extract the date range (remove leading/trailing spaces).
        String selectedDateRange = parts[1].trim();
        String username = parts[0].trim(); // Extract the username
        // Retrieve the data for the selected date range
        FirebaseProvider.getInstance().getPublicWraps(wraps -> {
            for (Wrap wrap : wraps) {
                // Extract date from Wrap
                String[] idParts = wrap.getSummaryId().split(" ");
                //String username = idParts[0];
                String wrapDate = idParts[1];
                LocalDate parsedDate = LocalDate.parse(wrapDate, DateTimeFormatter.ISO_DATE);
                String startDate = "unknown";
                // Determine start date based on time-span.
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                wrapDate = parsedDate.format(formatter);
                startDate = LocalDate.parse(startDate).format(formatter);
                String fullDateRange = startDate + " - " + wrapDate;
                // Check if the retrieved wrap corresponds to the selected date range
                if (selectedDateRange.equals(fullDateRange)) {
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
                    showPopup(fullDateRange, artistNames, trackNames, username);
                    break;
                }
            }
        });
    }


    // Method to show the popup with wrap details
    private void showPopup(String dateRange, List<String> artistNames, List<String> trackNames, String username) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.wrapped_item_public, null);
        builder.setView(dialogView);
        // Access views from the inflated layout
        TextView textViewDateRange = dialogView.findViewById(R.id.textViewDateRange);
        TextView textViewUserName = dialogView.findViewById(R.id.textViewUserName);
        LinearLayout layoutTopArtists = dialogView.findViewById(R.id.topArtists);
        LinearLayout layoutTopSongs = dialogView.findViewById(R.id.topSongs);
        // Populate the layout with data
        textViewDateRange.setText(dateRange);
        textViewUserName.setText(username);
        Typeface fancy = getResources().getFont(R.font.univers);
        textViewDateRange.setTypeface(fancy);
        textViewUserName.setTypeface(fancy);
        // Populate artists
        for (String artistName : artistNames) {
            TextView textViewArtist = new TextView(this);
            textViewArtist.setText(artistName);
            textViewArtist.setTypeface(fancy);
            layoutTopArtists.addView(textViewArtist);
        }
        // Populate songs
        for (String trackName : trackNames) {
            TextView textViewTrack = new TextView(this);
            textViewTrack.setText(trackName);
            textViewTrack.setTypeface(fancy);
            layoutTopSongs.addView(textViewTrack);
        }
        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        // Allow dismissal by touching outside
        alertDialog.setCanceledOnTouchOutside(true);
        // Add close button.
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
