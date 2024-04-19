package com.example.spotifywrapped.DarkActivities.Data;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

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

import java.util.ArrayList;
import java.util.List;

public class PublicWrapDarkActivity extends AppCompatActivity {

    private RecyclerView recyclerViewWrappedItems;
    private WrappedAdapterPublic adapter;
    private List<PublicWrappedItem> items = new ArrayList<>();
    private EditText searchbar;

    private List<Wrap> allWraps;

    private String searchTerm = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_wrap_dark);
        recyclerViewWrappedItems = findViewById(R.id.recycler_view_wrapped_items_two);
        recyclerViewWrappedItems.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WrappedAdapterPublic(items);
        recyclerViewWrappedItems.setAdapter(adapter);
        searchbar = findViewById(R.id.searchEditText);
        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Placeholder
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Placeholder
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This is where you get the text value after any changes
                searchTerm = s.toString();
                Log.d("PublicWrapDarkActivity", "Search term updated: " + searchTerm);
                items.clear();
                for (Wrap wrap : allWraps) {
                    // Parsing Summary IDs for usernames
                    String[] idParts = wrap.getSummaryId().split(" ");
                    // Extract username from summary ID
                    String username = idParts[0];
                    Log.d("PublicWrapDarkActivity", "Username extracted: " + username);
                    String date = idParts[1];
                    // Get track names
                    List<String> trackNames = new ArrayList<>();
                    for (Track track : wrap.getTracks()) {
                        trackNames.add(track.getName());
                    }
                    // Get artist names
                    List<String> artistNames = new ArrayList<>();
                    for (Artist artist : wrap.getArtists()) {
                        artistNames.add(artist.getName());
                    }
                    // Add item if searchTerm is contained in username or date, or if searchTerm is empty
                    if (searchTerm.isEmpty() || username.toLowerCase().contains(searchTerm.toLowerCase()) || date.contains(searchTerm.toLowerCase())) {
                        items.add(new PublicWrappedItem(username, artistNames, trackNames));
                    }
                }
                // Notify adapter of data change
                adapter.notifyDataSetChanged();
                // Here you can also add code to filter your adapter based on the searchTerm
            }
        });
        // Fetch public wraps from Firebase
        FirebaseProvider.getInstance().getPublicWraps(wraps -> {
            if (wraps != null) {
                allWraps = wraps;
                for (Wrap wrap : wraps) {
                    // Parsing Summary IDs for usernames
                    String[] idParts = wrap.getSummaryId().split(" ");
                    // Extract username from summary ID
                    String username = idParts[0];
                    Log.d("PublicWrapDarkActivity", "Username extracted: " + username);
                    String date = idParts[1];
                    // Get track names
                    List<String> trackNames = new ArrayList<>();
                    for (Track track : wrap.getTracks()) {
                        trackNames.add(track.getName());
                    }
                    // Get artist names
                    List<String> artistNames = new ArrayList<>();
                    for (Artist artist : wrap.getArtists()) {
                        artistNames.add(artist.getName());
                    }
                    // Add item if searchTerm is contained in username or date, or if searchTerm is empty
                    if (searchTerm.isEmpty() || username.contains(searchTerm) || date.contains(searchTerm)) {
                        items.add(new PublicWrappedItem(username, artistNames, trackNames));
                    }
                }
                // Notify adapter of data change
                adapter.notifyDataSetChanged();
            } else {
                Log.d("PublicWrapDarkActivity", "Failed to fetch public wraps");
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleBottomNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.navigation_language);
    }

    /**
     * Handles navigation item selection in the bottom navigation view.
     *
     * @param item The selected menu item.
     * @return True if the navigation item selection was handled successfully, else  false.
     */
    private boolean handleBottomNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_language) {
            return true;
        } else if (itemId == R.id.navigation_home) {
            // Navigates to WrappedDarkActivity
            startActivity(new Intent(this, WrappedDarkActivity.class));
            finish();
            return true;
        } else if (itemId == R.id.navigation_history) {
            // Navigates to PastWrapDarkActivity
            startActivity(new Intent(this, PastWrapDarkActivity.class));
            finish();
            return true;
        } else if (itemId == R.id.navigation_settings) {
            // Navigates to SettingsDarkOneActivity
            startActivity(new Intent(this, SettingsDarkOneActivity.class));
            finish();
            return true;
        }
        return false;
    }
}