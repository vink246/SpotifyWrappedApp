package com.example.spotifywrapped;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.spotifywrapped.DarkActivities.Data.WrappedDarkActivity;
import com.example.spotifywrapped.spotifyServices.SpotifyProvider;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

public class MainActivity extends AppCompatActivity implements SpotifyProvider.SpotifyProviderListener {

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
            "user-library-read",
            "user-read-email"
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

                    // initializing the provider
                    SpotifyProvider.initialize(CLIENT_ID, REDIRECT_URI, response.getAccessToken(),this, this);
                    break;

                // Auth flow returned an error
                case ERROR:
                    Toast.makeText(getApplicationContext(), "Error authenticating! Try restarting the app.",
                            Toast.LENGTH_SHORT).show();
                    Log.d("MainActivity", response.getError());
                    // Handle error response
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases (nothing for now)
            }
        }
    }

    @Override
    public void onSpotifyProviderInitialized(SpotifyProvider spotifyProvider) {
        // Check if provider is valid (if not valid we need to redirect to install spotify)
        if (!spotifyProvider.isValid()) {
            try {
                Toast.makeText(getApplicationContext(), "You need to install Spotify on your device!",
                        Toast.LENGTH_LONG).show();
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://play.google.com/store/apps/details?id=com.spotify.music"));
                startActivity(viewIntent);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Unable to Connect",
                        Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return;
        }
        // Once the provider is initialized, we navigate (only if it is valid)
        Intent homeIntent = new Intent(this, WrappedDarkActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(homeIntent);
    }
}