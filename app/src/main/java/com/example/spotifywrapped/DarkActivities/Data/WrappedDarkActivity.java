package com.example.spotifywrapped.DarkActivities.Data;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.spotifyServices.SpotifyProvider;
import com.example.spotifywrapped.DarkActivities.Settings.SettingsDarkOneActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WrappedDarkActivity extends AppCompatActivity {

    SpotifyProvider provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wrappeddark);

        // provider tests - can remove later
        provider = SpotifyProvider.getInstance();

        provider.getTopTracks(10, 0, SpotifyProvider.WrappedTerm.medium_term, topTracks -> {
            Log.d("WrappedDarkActivity", topTracks.toString());
            //provider.playTrack(topTracks.get(0));
        });
        provider.getTopArtists(10, 0, SpotifyProvider.WrappedTerm.medium_term, topArtists -> {
            Log.d("WrappedDarkActivity", topArtists.toString());
        });
        provider.getMyUserInfo(info -> {
            Log.d("WrappedDarkActivity", info.toString());
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleBottomNavigationItemSelected);

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, android.view.View selectedItemView, int position, long id) {
                String selectedItem = parentView.getItemAtPosition(position).toString();
                // Handle the selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });
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
