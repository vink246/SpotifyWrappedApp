package com.example.spotifywrapped.DarkActivities.Data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.spotifywrapped.DarkActivities.Settings.SettingsDarkOneActivity;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.models.Artist;
import com.example.spotifywrapped.models.Track;
import com.example.spotifywrapped.spotifyServices.SpotifyProvider;
import com.example.spotifywrapped.spotifyServices.SpotifyWrapped;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WrapSwipeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    SpotifyProvider provider;
    public static SpotifyWrapped wrapped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrap_swipe);

        provider = SpotifyProvider.getInstance();

        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::handleBottomNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        wrapped = (SpotifyWrapped) getIntent().getSerializableExtra("wrapped");

        setupViewPager();
    }

    private void setupViewPager() {
        FragmentStateAdapter adapter = new FragmentStateAdapter(this) {
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        provider.playTrack(wrapped.getTrackList().get(1));
                        return new TopSongListFragment();
                    case 1:
                        provider.playTrack(wrapped.getTopTrack());
                        return new TopSongFragment();
                    case 2:
                        Log.d("WrapSwipeActivity", getSongFromArtist(wrapped.getArtistList().get(1)).toString());
                        provider.playTrack(getSongFromArtist(wrapped.getArtistList().get(1)));
                        return new TopArtistListFragment();
                    case 3:
                        Log.d("WrapSwipeActivity", getSongFromArtist(wrapped.getTopArtist()).toString());
                        provider.playTrack(getSongFromArtist(wrapped.getTopArtist()));
                        return new TopArtistFragment();
                    case 4:
                        Track toPlay = getTrackByGenre(wrapped.getGenre());
                        if (toPlay != null) {
                            provider.playTrack(toPlay);
                        }
                        return new TopGenreFragment();
                    default:
                        provider.playTrack(wrapped.getTopTrack());
                        return new SummaryFragment();
                }
            }

            @Override
            public int getItemCount() {
                return 6;
            }
        };
        viewPager.setAdapter(adapter);
    }

    private Track getSongFromArtist(Artist artist) {
        for (Track track: wrapped.getTrackList()) {
            if (track.getArtists().contains(artist.getName())) {
                return track;
            }
        }
        return wrapped.getTopTrack();
    }

    private Track getTrackByGenre(String genre) {
        for (Track track : wrapped.getTrackList()) {
            for (String artistName : track.getArtists()) {
                for (Artist artist : wrapped.getArtistList()) {
                    if (artist.getName().equals(artistName) && artist.getGenres().contains(genre)) {
                        return track;
                    }
                }
            }
        }
        return null; // Genre not found in the SpotifyWrapped object
    }

    private boolean handleBottomNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_home) {

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