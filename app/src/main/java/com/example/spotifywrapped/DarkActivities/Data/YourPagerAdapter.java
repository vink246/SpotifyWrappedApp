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
                return new PublicWrapDarkFragment();
            case 2:
                return new PastWrappedDarkFragment();
            case 3:
                return new SettingsDark1Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5; // Total number of fragments
    }
}