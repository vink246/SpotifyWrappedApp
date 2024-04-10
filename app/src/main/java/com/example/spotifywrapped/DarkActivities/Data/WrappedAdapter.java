package com.example.spotifywrapped.DarkActivities.Data;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.DarkActivities.Data.WrappedItem;
import java.util.List;

public class WrappedAdapter extends RecyclerView.Adapter<WrappedAdapter.ViewHolder> {

    private final List<WrappedItem> mData;

    public WrappedAdapter(List<WrappedItem> data) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wrapped_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WrappedItem item = mData.get(position);
        Log.d("WrappedAdapter", "Item at position " + position + ": " + item.getTopArtists() + ", " + item.getTopSongs());
        holder.dateRange.setText(item.getDateRange());
        // You need to convert top artists and songs list to string or handle them appropriately
        holder.topArtists.setText(String.join(", ", item.getTopArtists()));
        holder.topSongs.setText(String.join(", ", item.getTopSongs()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateRange, topArtists, topSongs;

        ViewHolder(View itemView) {
            super(itemView);
            dateRange = itemView.findViewById(R.id.textViewDateRange);
            topArtists = itemView.findViewById(R.id.topArtists);
            topSongs = itemView.findViewById(R.id.topSongs);
        }
    }
}
