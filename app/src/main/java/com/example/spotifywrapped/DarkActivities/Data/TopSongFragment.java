package com.example.spotifywrapped.DarkActivities.Data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.spotifywrapped.R;
import com.squareup.picasso.Picasso;

public class TopSongFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.top_song, container, false);
        TextView name = rootView.findViewById(R.id.songName);
        name.setText(WrapSwipeActivity.wrapped.getTopTrack().getName());
        ImageView image = rootView.findViewById(R.id.imageView3);
        Picasso.get().load(WrapSwipeActivity.wrapped.getTopTrack().getImage()).into(image);
        return rootView;    }
}