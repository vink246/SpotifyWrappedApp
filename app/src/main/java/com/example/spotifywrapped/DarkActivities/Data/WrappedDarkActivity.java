package com.example.spotifywrapped.DarkActivities.Data;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SummaryAdapter;
import com.example.spotifywrapped.models.User;
import com.example.spotifywrapped.firebaseServices.FirebaseProvider;
import com.example.spotifywrapped.spotifyServices.SpotifyProvider;
import com.example.spotifywrapped.DarkActivities.Settings.SettingsDarkOneActivity;
import com.example.spotifywrapped.spotifyServices.SpotifyWrapped;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class WrappedDarkActivity extends AppCompatActivity {

    SpotifyProvider provider;
    SpotifyWrapped wrapped;
    SummaryAdapter summaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        provider = SpotifyProvider.getInstance();

        provider.getTopTracks(5, 0, SpotifyProvider.WrappedTerm.medium_term, topTracks -> {
            if (topTracks != null) {
                // populate tracks
                provider.getTopArtists(5, 0, SpotifyProvider.WrappedTerm.medium_term, topArtists -> {
                    if (topArtists != null) {
                        // populate artists and genre
                        initRecyclerView();
                        setContentView(R.layout.wrappeddark);

                    }
                });


            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleBottomNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, android.view.View selectedItemView, int position, long id) {
                // Handle spinner selection
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle no selection
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.summaryText);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        summaryAdapter = new SummaryAdapter(wrapped, new SummaryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // do nothing?
            }
        });
        recyclerView.setAdapter(summaryAdapter);
    }

    private boolean handleBottomNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_home) {
            // Do nothing as we are already in WrappedDarkActivity
        } else if (itemId == R.id.navigation_group) {
            startActivity(new Intent(this, FriendArtistCompDarkActivity.class));
            finish();
        } else if (itemId == R.id.navigation_history) {
            startActivity(new Intent(this, PastWrapDarkActivity.class));
            finish();
        } else if (itemId == R.id.navigation_language) {
            startActivity(new Intent(this, PublicWrapDarkActivity.class));
            finish();
        } else if (itemId == R.id.navigation_settings) {
            startActivity(new Intent(this, SettingsDarkOneActivity.class));
            finish();
        }
        return true;
    }
}