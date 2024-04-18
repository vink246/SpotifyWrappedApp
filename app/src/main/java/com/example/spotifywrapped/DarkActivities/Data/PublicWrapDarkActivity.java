package com.example.spotifywrapped.DarkActivities.Data;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_wrap_dark);
        recyclerViewWrappedItems = findViewById(R.id.recycler_view_wrapped_items_two);
        recyclerViewWrappedItems.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WrappedAdapterPublic(items);
        recyclerViewWrappedItems.setAdapter(adapter);
        // Fetch public wraps from Firebase
        FirebaseProvider.getInstance().getPublicWraps(wraps -> {
            if (wraps != null) {
                for (Wrap wrap : wraps) {
                    // Parsing Summary IDs for usernames
                    String[] idParts = wrap.getSummaryId().split(" ");
                    // Extract username from summary ID
                    String username = idParts[0];
                    Log.d("PublicWrapDarkActivity", "Username extracted: " + username);
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
                    // Create a new PublicWrappedItem and add it to the list.
                    items.add(new PublicWrappedItem(username, artistNames, trackNames));
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