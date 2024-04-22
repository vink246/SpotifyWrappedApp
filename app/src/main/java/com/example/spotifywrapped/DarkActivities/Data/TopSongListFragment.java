package com.example.spotifywrapped.DarkActivities.Data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.DarkActivities.Data.YourPagerAdapter;
public class TopSongListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.top_songlist, container, false);
        return rootView;
    }
}