package com.example.spotifywrapped.DarkActivities.Data;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.firebaseServices.FirebaseProvider;
import com.example.spotifywrapped.models.Artist;
import com.example.spotifywrapped.models.Track;
import com.example.spotifywrapped.models.Wrap;
import com.example.spotifywrapped.spotifyServices.SpotifyProvider;
import com.example.spotifywrapped.DarkActivities.Settings.SettingsDarkOneActivity;
import com.example.spotifywrapped.spotifyServices.SpotifyWrapped;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WrappedDarkActivity extends AppCompatActivity {

    SpotifyProvider provider;
    SpotifyWrapped wrapped;
    SpotifyProvider.WrappedTerm selectedItem = SpotifyProvider.WrappedTerm.short_term;
    Spinner spinner;
    TextView textViewTopSong;
    TextView textViewTopArtist;
    TextView textViewTopGenre;
    TextView textViewLeadingSongs;
    TextView textViewLeadingArtists;
    ImageView artistImage;

    private static boolean snackbarPrevShown = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wrappeddark);

        provider = SpotifyProvider.getInstance();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleBottomNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        textViewTopSong = findViewById(R.id.textViewTopSong);
        textViewTopArtist = findViewById(R.id.textViewTopArtist);
        textViewTopGenre = findViewById(R.id.textViewTopGenre);
        textViewLeadingSongs = findViewById(R.id.textViewLeadingTopSongs);
        textViewLeadingArtists = findViewById(R.id.textViewLeadingTopArtists);
        artistImage = findViewById(R.id.imageView);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, android.view.View selectedItemView, int position, long id) {
                switch ((String) parentView.getItemAtPosition(position)) {
                    case "Short Term":
                        selectedItem = SpotifyProvider.WrappedTerm.short_term;
                        break;
                    case "Medium Term":
                        selectedItem = SpotifyProvider.WrappedTerm.medium_term;
                        break;
                    case "Long Term":
                        selectedItem = SpotifyProvider.WrappedTerm.long_term;
                        break;
                }
                // Now 'selectedItem' contains the value currently selected in the spinner
                Log.d("SpinnerSelection", "Selected item: " + selectedItem);
                loadPage();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle no selection
            }
        });

        // Retrieve the selected term from the intent extras
        String selectedTerm = getIntent().getStringExtra("selected_term");
        // Set the spinner selection based on the selected term
        if (selectedTerm != null) {
            int position = adapter.getPosition(selectedTerm);
            if (position != -1) {
                spinner.setSelection(position);
            }
        }

        // Find the button by its ID
        Button yourButton = findViewById(R.id.saveButton);

        // Set an OnClickListener for the button
        yourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wrapped != null) {
                    FirebaseProvider.getInstance().saveWrap(wrapped.username, new Wrap(
                            wrapped.summaryId,
                            wrapped.username,
                            wrapped.timespan,
                            wrapped.getTrackList(),
                            wrapped.getArtistList(),
                            wrapped.getGenre(),
                            false
                    ), getApplicationContext());
                }
            }
        });
        provider.getMyUserInfo(info -> {
            if (!info.isPremium() && !snackbarPrevShown) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "You don't have premium. Audio may be erratic", Snackbar.LENGTH_INDEFINITE);

                snackbar.setAction("Dismiss", v -> snackbar.dismiss()); // Add an action to dismiss the Snackbar when clicked
                snackbar.show();
                snackbarPrevShown = true;
            }
        });
    }

    private synchronized void loadPage() {
        provider.getTopTracks(5, 0, selectedItem, topTracks -> {
            if (topTracks != null) {
                // populate tracks
                provider.getTopArtists(5, 0, selectedItem, topArtists -> {
                    if (topArtists != null) {
                        provider.getMyUserInfo(info -> {
                            if (info != null) {
                                if (topTracks.size() < 5 || topArtists.size() < 5) {
                                    Toast.makeText(getApplicationContext(), "You haven't listened to enough songs for this term!",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // by the time we are here, we should have top tracks, artists, and user info
                                    wrapped = new SpotifyWrapped(
                                            topTracks,
                                            topArtists,
                                            selectedItem,
                                            info.getUsername()
                                    );
                                    updateUI();
                                }
                            }
                        });
                    }
                });


            }
        });
    }

    private void updateUI() {
        if (wrapped != null) {
            provider.playTrack(wrapped.getTopTrack());
            // Update text views with wrapped data
            textViewTopSong.setText("Top Song: "+wrapped.getTopTrack().getName());
            textViewTopArtist.setText("Top Artist: "+wrapped.getTopArtist().getName());
            textViewTopGenre.setText("Top Genre: "+wrapped.getGenre());
            textViewLeadingSongs.setText(formatLeadingList(wrapped.getTrackList()));
            textViewLeadingArtists.setText(formatLeadingList(wrapped.getArtistList()));
            String imageUrl = wrapped.getImage(); // Replace this with your actual image URL
            Picasso.get().load(imageUrl).into(artistImage);
        }
    }

    private String formatLeadingList(ArrayList<?> list) {
        StringBuilder builder = new StringBuilder();
        for (Object item : list) {
            if (item instanceof Track) {
                builder.append(((Track) item).getName()).append("\n");
            } else if (item instanceof Artist) {
                builder.append(((Artist) item).getName()).append("\n");
            }
        }
        return builder.toString();
    }

    private boolean handleBottomNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_home) {
            // Do nothing as we are already in WrappedDarkActivity
        } else if (itemId == R.id.navigation_history) {
            provider.pauseCurrentTrack();
            startActivity(new Intent(this, PastWrapDarkActivity.class));
            finish();
        } else if (itemId == R.id.navigation_language) {
            provider.pauseCurrentTrack();
            startActivity(new Intent(this, PublicWrapDarkActivity.class));
            finish();
        } else if (itemId == R.id.navigation_settings) {
            provider.pauseCurrentTrack();
            startActivity(new Intent(this, SettingsDarkOneActivity.class));
            finish();
        }
        return true;
    }
}
