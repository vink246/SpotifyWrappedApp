package com.example.spotifywrapped;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.spotifywrapped.LightActivities.Data.FriendCompLightActivity;
import com.example.spotifywrapped.LightActivities.Data.TopArtistLightActivity;
import com.example.spotifywrapped.spotifyServices.SpotifyProvider;

public class HomeActivity extends AppCompatActivity {

    SpotifyProvider spotifyProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity);

        // Find buttons by their IDs
        Button button1 = findViewById(R.id.button5);
        Button button2 = findViewById(R.id.button6);
        Button button3 = findViewById(R.id.button11);
        Button button4 = findViewById(R.id.button13);

        // Set click listeners for each button
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, TopArtistLightActivity.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, FriendCompLightActivity.class));
            }
        });

        spotifyProvider = SpotifyProvider.getInstance();

        // This is a test to get the top 10 tracks (works but will be removed)
        spotifyProvider.getTopTracks(10, 0, topTracks -> {
            Log.d("SpotifyProvider", topTracks.toString());
        });
        // This is a test to get the top 10 artists
        spotifyProvider.getTopArtists(10, 0, topArtists -> {
            Log.d("SpotifyProvider", topArtists.toString());
        });

    }
}
