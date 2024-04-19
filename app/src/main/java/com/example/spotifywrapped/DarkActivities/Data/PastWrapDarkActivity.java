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
import com.example.spotifywrapped.spotifyServices.SpotifyProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PastWrapDarkActivity extends AppCompatActivity {

    private RecyclerView recyclerViewWrappedItems;
    private WrappedAdapter adapter;
    private List<WrappedItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_wrap_dark); // Ensure this is the name of your layout file with the RecyclerView

        // Initialize RecyclerView for wrapped items
        recyclerViewWrappedItems = findViewById(R.id.recycler_view_wrapped_items);
        recyclerViewWrappedItems.setLayoutManager(new LinearLayoutManager(this));

        SpotifyProvider.getInstance().getMyUserInfo(info -> {
            FirebaseProvider.getInstance().getSavedWraps(info.getUsername(), wraps -> {
                for (Wrap wrap: wraps.getResult()) {
                    String[] idParts = wrap.getSummaryId().split(" ");
                    String date = idParts[1];
                    List<String> trackNames = new ArrayList<>();
                    for (Track track : wrap.getTracks()) {
                        trackNames.add(track.getName());
                    }
                    List<String> artistNames = new ArrayList<>();
                    for (Artist artist : wrap.getArtists()) {
                        artistNames.add(artist.getName());
                    }
                    LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
                    String startDate = "unknown";
                    switch (wrap.getTimespan()){
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
                    items.add(0, new WrappedItem(startDate+" to "+date, artistNames, trackNames));
                }
                adapter.notifyDataSetChanged();
            });
        });
        // Create sample dat
//        items.add(new WrappedItem("Date Range 1", Arrays.asList("Lil baby\n", "Lil Uzi Vert\n", "NBA Youngboy\n", "Kanye\n", "Drake"), Arrays.asList("Song 1", "Song 2", "Song 3")));
//        // Add as many WrappedItem objects to the list as you need
//        items.add(new WrappedItem("Date Range 2", Arrays.asList("Artist A", "Artist B", "Artist C", "Artist D", "Artist E"), Arrays.asList("Song A", "Song B", "Song C")));
//        items.add(new WrappedItem("Date Range 3", Arrays.asList("Artist A", "Artist B", "Artist C"), Arrays.asList("Song A", "Song B", "Song C")));
//        items.add(new WrappedItem("Date Range 4", Arrays.asList("Artist A", "Artist B", "Artist C"), Arrays.asList("Song A", "Song B", "Song C")));

        Log.d("PastWrapDarkActivity", "Number of items: " + items.size());
        adapter = new WrappedAdapter(items);
        recyclerViewWrappedItems.setAdapter(adapter);

        // BottomNavigationView setup remains unchanged
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleBottomNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.navigation_history);
    }


    private boolean handleBottomNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_history) {
            return true; // Already in this activity, do nothing
        } else if (itemId == R.id.navigation_home) {
            startActivity(new Intent(this, WrappedDarkActivity.class));
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
