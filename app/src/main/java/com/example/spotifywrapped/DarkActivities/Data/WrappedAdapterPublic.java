package com.example.spotifywrapped.DarkActivities.Data;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.spotifywrapped.R;
import java.util.List;
public class WrappedAdapterPublic extends RecyclerView.Adapter<WrappedAdapterPublic.ViewHolder> {

    private final List<PublicWrappedItem> mData;

    /**
     * Constructor for the WrappedAdapterPublic class.
     * @param data List of public wrapped items to be displayed.
     */
    public WrappedAdapterPublic(List<PublicWrappedItem> data) {
        mData = data;
    }

    /**
     * Creates ViewHolder objects for the RecyclerView.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wrapped_item_public, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Binds data to the ViewHolder objects.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PublicWrappedItem item = mData.get(position);
        Log.d("WrappedAdapter", "Item at position " + position + ": " + item.getTopArtists() + ", " + item.getTopSongs());
        holder.username.setText(item.getUsername());

        // Convert top artists/songs list to appear on a near lie for every element
        String formattedArtists = joinWithNewLine(item.getTopArtists());
        String formattedSongs = joinWithNewLine(item.getTopSongs());

        // Set formatted text to TextViews
        holder.topArtists.setText(formattedArtists);
        holder.topSongs.setText(formattedSongs);
    }

    /**
     * Gets the total number of items in the data set.
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * ViewHolder class for holding references to views within the RecyclerView items.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username, topArtists, topSongs;

        ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.textViewDateRange2);
            topArtists = itemView.findViewById(R.id.topArtists);
            topSongs = itemView.findViewById(R.id.topSongs);
        }
    }

    /**
     * Helper method to to make elements appear on a new line.
     * @param items List of items.
     * @return String containing each item separated by a new line.
     */
    private String joinWithNewLine(List<String> items) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : items) {
            // Append each item followed by a new line character
            stringBuilder.append(item).append("\n");
        }
        // Remove the last new line character if it exists
        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}