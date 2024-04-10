package com.example.spotifywrapped;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifywrapped.spotifyServices.SpotifyWrapped;

import java.util.ArrayList;
import java.util.List;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.ItemViewHolder> {

    private List<String> wrapped; //[0]: top songs, [1]: top artists, [2]: top genre
    private SpotifyWrapped wrappedObject;
    private OnItemClickListener onItemClickListener;

    public SummaryAdapter(SpotifyWrapped wrapped, OnItemClickListener onItemClickListener) {
        wrappedObject = wrapped;
        this.wrapped = new ArrayList<>();
        this.wrapped.add(wrapped.getTrackString());
        this.wrapped.add(wrapped.getArtistString());
        this.wrapped.add("Top Genre: " + wrapped.getGenre());
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wrappeddark, parent, false);
        return new ItemViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String currentInfo = wrapped.get(position);
        holder.bind(wrappedObject);
    }

    @Override
    public int getItemCount() {
        return wrapped.size();
    } // should always return 3

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTracks;
        private TextView textViewArtists;
        private TextView textViewGenre;
        private OnItemClickListener onItemClickListener;

        public ItemViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            textViewTracks = itemView.findViewById(R.id.textViewTracks);
            textViewArtists = itemView.findViewById(R.id.textViewArtists);
            textViewGenre = itemView.findViewById(R.id.textViewGenre);
            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void bind(SpotifyWrapped wrapped) {
            String tracks = wrapped.getTrackString();
            String artists = wrapped.getArtistString();
            String genre = wrapped.getGenre();

            textViewTracks.setTextColor(Color.WHITE);
            textViewArtists.setTextColor(Color.WHITE);
            textViewGenre.setTextColor(Color.WHITE);
            textViewTracks.setText(tracks);
            textViewArtists.setText(artists);
            textViewGenre.setText(genre);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}