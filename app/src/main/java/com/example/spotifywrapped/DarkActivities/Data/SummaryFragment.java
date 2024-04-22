package com.example.spotifywrapped.DarkActivities.Data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.spotifywrapped.R;

public class SummaryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.summary, container, false);
        TextView topSong = rootView.findViewById(R.id.textSongTitle);
        topSong.setText("Top Song: \n\n" + WrapSwipeActivity.wrapped.getTopTrack().getName());
        TextView topArtist = rootView.findViewById(R.id.textArtistTitle);
        topArtist.setText("Top Artist: \n\n" + WrapSwipeActivity.wrapped.getTopArtist().getName());
        TextView songList = rootView.findViewById(R.id.textViewLeadingTopSongs);
        songList.setText("1. " + WrapSwipeActivity.wrapped.getTrackList().get(0).getName()+
                "\n2. " + WrapSwipeActivity.wrapped.getTrackList().get(1).getName() +
                "\n3. " + WrapSwipeActivity.wrapped.getTrackList().get(2).getName() +
                "\n4. " + WrapSwipeActivity.wrapped.getTrackList().get(3).getName() +
                "\n5. " + WrapSwipeActivity.wrapped.getTrackList().get(4).getName());
        TextView artistList = rootView.findViewById(R.id.textViewLeadingTopArtists);
        artistList.setText("1. " + WrapSwipeActivity.wrapped.getArtistList().get(0).getName()+
                "\n2. " + WrapSwipeActivity.wrapped.getArtistList().get(1).getName() +
                "\n3. " + WrapSwipeActivity.wrapped.getArtistList().get(2).getName() +
                "\n4. " + WrapSwipeActivity.wrapped.getArtistList().get(3).getName() +
                "\n5. " + WrapSwipeActivity.wrapped.getArtistList().get(4).getName());

        return rootView;
    }
}