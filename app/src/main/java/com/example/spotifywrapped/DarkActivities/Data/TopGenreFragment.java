package com.example.spotifywrapped.DarkActivities.Data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.spotifywrapped.R;

public class TopGenreFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.top_genre, container, false);
        TextView genre = rootView.findViewById(R.id.textView5);
        genre.setText(WrapSwipeActivity.wrapped.getGenre());

        return rootView;    }
}