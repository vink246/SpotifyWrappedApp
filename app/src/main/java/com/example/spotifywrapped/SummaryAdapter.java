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

    private SpotifyWrapped wrappedObject;
    private OnItemClickListener onItemClickListener;

    public SummaryAdapter(SpotifyWrapped wrapped, OnItemClickListener onItemClickListener) {
        wrappedObject = wrapped;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summary, parent, false);
        return new ItemViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 3; // Assuming you always have 3 items: top songs, top artists, and top genre
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textViewTracks;
        private TextView textViewArtists;
        private TextView textViewGenre;

        public ItemViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            textViewTracks = itemView.findViewById(R.id.textViewTracks);
            textViewArtists = itemView.findViewById(R.id.textViewArtists);
            textViewGenre = itemView.findViewById(R.id.textViewGenre);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            switch (position) {
                case 0:
                    textViewTracks.setText(wrappedObject.getTrackString());
                    break;
                case 1:
                    textViewArtists.setText(wrappedObject.getArtistString());
                    break;
                case 2:
                    textViewGenre.setText("Top Genre: " + wrappedObject.getGenre());
                    break;
                default:
                    // Do nothing for other positions
            }
            // Set text color
            textViewTracks.setTextColor(Color.WHITE);
            textViewArtists.setTextColor(Color.WHITE);
            textViewGenre.setTextColor(Color.WHITE);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(position);
                }
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}