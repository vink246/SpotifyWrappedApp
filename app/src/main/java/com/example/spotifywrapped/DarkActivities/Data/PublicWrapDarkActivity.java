package com.example.spotifywrapped.DarkActivities.Data;


import android.content.Intent;
import android.os.Bundle;
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


public class PublicWrapDarkActivity extends AppCompatActivity implements DateBlockAdapter2.OnDateBlockClickListener {
    private RecyclerView recyclerViewDateBlocks;
    private DateBlockAdapter2 adapter;
    private List<String> dateRanges = new ArrayList<>();
    private List<String> userNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_wrap_dark);
        // Initialize RecyclerView for date blocks
        recyclerViewDateBlocks = findViewById(R.id.recycler_view_wrapped_items_two);
        recyclerViewDateBlocks.setLayoutManager(new LinearLayoutManager(this));
        // Fetch public wraps from Firebase
        FirebaseProvider.getInstance().getPublicWraps(wraps -> {
            if (wraps != null) {
                for (Wrap wrap : wraps) {
                    // Parsing Summary IDs for usernames and dates
                    String[] idParts = wrap.getSummaryId().split(" ");
                    String username = idParts[0];
                    String date = idParts[1];
                    // Extract track names
                    List<String> trackNames = new ArrayList<>();
                    for (Track track : wrap.getTracks()) {
                        trackNames.add(track.getName());
                    }
                    // Extract artist names
                    List<String> artistNames = new ArrayList<>();
                    for (Artist artist : wrap.getArtists()) {
                        artistNames.add(artist.getName());
                    }
                    // Add username and date to lists
                    userNames.add(username);
                    dateRanges.add(date);
                }
                // Initialize and set adapter for RecyclerView
                adapter = new DateBlockAdapter2(dateRanges, userNames);
                adapter.setOnDateBlockClickListener(this);
                recyclerViewDateBlocks.setAdapter(adapter);
            } else {
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleBottomNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.navigation_language);
    }

    private boolean handleBottomNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_language) {
            return true;
        } else if (itemId == R.id.navigation_home) {
            // Navigates to WrappedDarkActivity
            startActivity(new Intent(this, WrappedDarkActivity.class));
            finish();
            return true;
        } else if (itemId == R.id.navigation_group) {
            // Navigates to FriendArtistCompDarkActivity
            startActivity(new Intent(this, FriendArtistCompDarkActivity.class));
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

    // try to attempt to implement handle click on date block :(
    @Override
    public void onDateBlockClick(String dateRange) {
        // Gl soldier
    }
}

