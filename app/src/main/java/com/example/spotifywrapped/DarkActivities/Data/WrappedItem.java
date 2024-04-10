package com.example.spotifywrapped.DarkActivities.Data;

import java.util.List;

public class WrappedItem {
    private String dateRange;
    private List<String> topArtists;
    private List<String> topSongs;

    public WrappedItem(String dateRange, List<String> topArtists, List<String> topSongs) {
        this.dateRange = dateRange;
        this.topArtists = topArtists;
        this.topSongs = topSongs;
    }

    // Getters
    public String getDateRange() { return dateRange; }
    public List<String> getTopArtists() { return topArtists; }
    public List<String> getTopSongs() { return topSongs; }

    // Setters, if needed
    // ...
}
