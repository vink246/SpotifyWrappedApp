package com.example.spotifywrapped.DarkActivities.Data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.spotifywrapped.R;

public class TopArtistListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.top_artistlist, container, false);
        TextView artist2 = rootView.findViewById(R.id.artist2);
        artist2.setText(WrapSwipeActivity.wrapped.getArtistList().get(1).getName());
        TextView artist3 = rootView.findViewById(R.id.artist3);
        artist3.setText(WrapSwipeActivity.wrapped.getArtistList().get(2).getName());
        TextView artist4 = rootView.findViewById(R.id.artist4);
        artist4.setText(WrapSwipeActivity.wrapped.getArtistList().get(3).getName());
        TextView artist5 = rootView.findViewById(R.id.artist5);
        artist5.setText(WrapSwipeActivity.wrapped.getArtistList().get(4).getName());

        return rootView;
    }
}