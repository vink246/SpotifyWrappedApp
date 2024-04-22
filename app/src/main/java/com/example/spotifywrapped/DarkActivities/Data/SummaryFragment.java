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

public class SummaryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.summary, container, false);
//        TextView topArtist = rootView.findViewByID();
//        TextView topArtist = rootView.findViewByID();
//        TextView topArtist = rootView.findViewByID();

        return rootView;
    }
}