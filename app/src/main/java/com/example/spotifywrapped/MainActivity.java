package com.example.spotifywrapped;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.spotifywrapped.spotifyServices.SpotifyProvider;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

public class MainActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "3c860efb9e6541528afc754b801be36e";

    // Request code will be used to verify if result comes from the login activity. Can be set to any integer.
    private static final int REQUEST_CODE = 1337;
    private static final String REDIRECT_URI = "spotifywrapped://auth";

    // scopes tell the spotify API what services we want to use. Is reflected in permissions request
    private static final String[] SCOPES = new String[]{
            "user-read-playback-state",
            "user-modify-playback-state",
            "user-read-currently-playing",
            "app-remote-control",
            "streaming",
            "playlist-read-private",
            "playlist-read-collaborative",
            "playlist-modify-private",
            "playlist-modify-public",
            "user-follow-modify",
            "user-follow-read",
            "user-read-playback-position",
            "user-top-read",
            "user-read-recently-played",
            "user-library-modify",
            "user-library-read"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingscreen);

        // Creating a new authorization request builder
        AuthorizationRequest.Builder builder =
                new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI);
        // Setting scopes defined above
        builder.setScopes(SCOPES);
        // Building auth request builder
        AuthorizationRequest request = builder.build();

        // Authorizing
        AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, intent);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    Log.d("MainActivity", response.getAccessToken());
                    // Creating a new provider object
                    SpotifyProvider spotifyProvider = new SpotifyProvider(CLIENT_ID, REDIRECT_URI, response.getAccessToken(),this);
                    // TODO: figure out a way to pass the spotifyProvider instance through activities
                    // This is a test to get the top 10 tracks (works but will be removed)
                    spotifyProvider.getTopTracks(10, 0, topTracks -> {
                        Log.d("SpotifyProvider", topTracks.toString());
                    });
                    // This is a test to get the top 10 artists
                    spotifyProvider.getTopArtists(10, 0, topArtists -> {
                        Log.d("SpotifyProvider", topArtists.toString());
                    });
                    // TODO: Navigate to home page activity after
                    break;

                // Auth flow returned an error
                case ERROR:
                    // TODO: Add a toast message for when authentication fails
                    Log.d("MainActivity", response.getError());
                    // Handle error response
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases (nothing for now)
            }
        }
    }
}