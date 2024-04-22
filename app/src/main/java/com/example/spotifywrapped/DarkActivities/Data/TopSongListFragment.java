package com.example.spotifywrapped.DarkActivities.Data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.spotifywrapped.R;
public class TopSongListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.top_songlist, container, false);
        TextView song2 = rootView.findViewById(R.id.song2);
        song2.setText(WrapSwipeActivity.wrapped.getTrackList().get(1).getName());
        TextView song3 = rootView.findViewById(R.id.song3);
        song3.setText(WrapSwipeActivity.wrapped.getTrackList().get(2).getName());
        TextView song4 = rootView.findViewById(R.id.song4);
        song4.setText(WrapSwipeActivity.wrapped.getTrackList().get(3).getName());
        TextView song5 = rootView.findViewById(R.id.song5);
        song5.setText(WrapSwipeActivity.wrapped.getTrackList().get(4).getName());

        return rootView;    }
}