package com.example.spotifywrapped.DarkActivities.Data;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.spotifyServices.SpotifyProvider;

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

    }
}
