package com.example.spotifywrapped.DarkActivities.Data;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifywrapped.DarkActivities.Settings.SettingsDarkOneActivity;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.DarkActivities.Data.WrappedAdapter;
import com.example.spotifywrapped.DarkActivities.Data.WrappedItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PublicWrapDarkActivity extends AppCompatActivity {
    private RecyclerView recyclerViewWrappedItems;
    private WrappedAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_wrap_dark);


        // Initialize RecyclerView for wrapped items
        recyclerViewWrappedItems = findViewById(R.id.recycler_view_wrapped_items_two);
        recyclerViewWrappedItems.setLayoutManager(new LinearLayoutManager(this));

        // Create sample data
        List<WrappedItem> items = new ArrayList<>();
        items.add(new WrappedItem("Username 1", Arrays.asList("Lil baby\n", "Lil Uzi Vert\n", "NBA Youngboy\n", "Kanye\n", "Drake"), Arrays.asList("Song 1", "Song 2", "Song 3")));
        // Add as many WrappedItem objects to the list as you need
        items.add(new WrappedItem("Username 2", Arrays.asList("Artist A", "Artist B", "Artist C", "Artist D", "Artist E"), Arrays.asList("Song A", "Song B", "Song C")));
        items.add(new WrappedItem("Username 3", Arrays.asList("Artist A", "Artist B", "Artist C"), Arrays.asList("Song A", "Song B", "Song C")));
        items.add(new WrappedItem("Username 4", Arrays.asList("Artist A", "Artist B", "Artist C"), Arrays.asList("Song A", "Song B", "Song C")));

        Log.d("PublicWrapDarkActivity", "Number of items: " + items.size());
        adapter = new WrappedAdapter(items);
        recyclerViewWrappedItems.setAdapter(adapter);

        // BottomNavigationView setup remains unchanged
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleBottomNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.navigation_language);
    }

    private boolean handleBottomNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_language) {
            return true; // Already in this activity, do nothing
        } else if (itemId == R.id.navigation_home) {
            startActivity(new Intent(this, WrappedDarkActivity.class));
            finish();
            return true;
        } else if (itemId == R.id.navigation_history) {
            startActivity(new Intent(this, PastWrapDarkActivity.class));
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
