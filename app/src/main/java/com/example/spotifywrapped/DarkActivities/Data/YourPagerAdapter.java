package com.example.spotifywrapped.DarkActivities.Data;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.spotifywrapped.DarkActivities.Data.WrappedDarkActivity;

public class YourPagerAdapter extends FragmentPagerAdapter {
    public YourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new WrappedDarkFragment();
            case 1:
                return new TopSongListFragment();
            case 2:
                return new TopSongFragment();
            case 3:
                return new TopArtistListFragment();
            case 4:
                return new TopArtistFragment();
            case 5:
                return new TopGenreFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 6; // Total number of fragments
    }
}