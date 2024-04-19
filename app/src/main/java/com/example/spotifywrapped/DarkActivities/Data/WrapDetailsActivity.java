package com.example.spotifywrapped.DarkActivities.Data;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.models.Artist;
import com.example.spotifywrapped.models.Track;
import com.example.spotifywrapped.models.Wrap;

import java.util.List;

public class WrapDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_wrap_dark);

        // Retrieve wrap object from intent
        Wrap wrap = (Wrap) getIntent().getSerializableExtra("wrap");

        // Populate views with wrap details
        populateWrapDetails(wrap);
    }

    private void populateWrapDetails(Wrap wrap) {
        TextView textViewDateRange = findViewById(R.id.textViewDateRange);
        LinearLayout layoutTopArtists = findViewById(R.id.topArtists);
        LinearLayout layoutTopSongs = findViewById(R.id.topSongs);

        // Set date range
        String dateRange = String.valueOf(wrap.getTimespan());
        textViewDateRange.setText(dateRange);

        // Set top artists
        List<Artist> artists = wrap.getArtists();
        for (Artist artist : artists) {
            TextView textViewArtist = new TextView(this);
            textViewArtist.setText(artist.getName());
            layoutTopArtists.addView(textViewArtist);
        }

        // Set top songs
        List<Track> tracks = wrap.getTracks();
        for (Track track : tracks) {
            TextView textViewTrack = new TextView(this);
            textViewTrack.setText(track.getName());
            layoutTopSongs.addView(textViewTrack);
        }
    }
}
