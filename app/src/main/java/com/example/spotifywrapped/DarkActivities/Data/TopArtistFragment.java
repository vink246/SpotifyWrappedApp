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

public class TopArtistFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.top_artist);
        final View rootView = inflater.inflate(R.layout.top_artist, container, false);
        TextView name = rootView.findViewById(R.id.artistName);
        name.setText(WrapSwipeActivity.wrapped.getTopArtist().getName());
        ImageView image = rootView.findViewById(R.id.imageView2);
        Picasso.get().load(WrapSwipeActivity.wrapped.getTopArtist().getImage()).into(image);

        return rootView;
    }
}