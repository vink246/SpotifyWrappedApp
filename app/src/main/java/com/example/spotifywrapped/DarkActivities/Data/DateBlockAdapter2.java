package com.example.spotifywrapped.DarkActivities.Data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifywrapped.R;

import java.util.List;

public class DateBlockAdapter2 extends RecyclerView.Adapter<DateBlockAdapter2.DateBlockViewHolder> {

    // List of date ranges
    private List<String> dateRanges;
    // List of user names.
    private List<String> userNames;
    // Listener for date block clicks
    private OnDateBlockClickListener listener;
    // Constructor to initialize the adapter with a list of date ranges
    public DateBlockAdapter2(List<String> dateRanges, List<String> userNames) {
        this.dateRanges = dateRanges;
        this.userNames = userNames;
    }
    // Setter method to set the listener for date block clicks
    public void setOnDateBlockClickListener(OnDateBlockClickListener listener) {
        this.listener = listener;
    }
    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public DateBlockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.date_block2, parent, false);
        return new DateBlockViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull DateBlockViewHolder holder, int position) {
        // Check if the position is valid for both lists
        if (position < dateRanges.size() && position < userNames.size()) {
            // Get the date range and username at the specified position
            String dateRange = dateRanges.get(position);
            String userName = userNames.get(position);
            // Bind the date range and username to the view holder
            holder.bind(dateRange, userName);
        }
    }

    // Return the size of the dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dateRanges.size();
    }

    // Interface to handle date block clicks
    public interface OnDateBlockClickListener {
        void onDateBlockClick(String dateRange);
    }

    // ViewHolder class to hold references to the views for each item in the RecyclerView
    class DateBlockViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewDateRange;
        private TextView textViewUser;

        // Constructor to initialize the views
        public DateBlockViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find and initialize the TextView for the date range
            textViewDateRange = itemView.findViewById(R.id.textViewDateRange);
            textViewUser = itemView.findViewById(R.id.textViewUser);
            // Set click listener for the itemView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Check if the listener is set and call the listener's method
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            String dateRange = dateRanges.get(position);
                            String userName = userNames.get(position);
                            listener.onDateBlockClick(dateRange);
                            listener.onDateBlockClick(userName);
                        }
                    }
                }
            });
        }

        // Method to bind data to the views
        public void bind(String dateRange, String userName) {
            textViewDateRange.setText(dateRange);
            textViewUser.setText(userName);

        }
    }
}