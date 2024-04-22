package com.example.spotifywrapped.DarkActivities.Data;

import java.util.List;

public class PublicWrappedItem {
    private String username;
    private List<String> topArtists;
    private List<String> topSongs;

    /**
     * Default constructor for public wraps with topArtist and topSong lists and username of user.
     * @param username User's username
     * @param topArtists List of topArtists
     * @param topSongs List of topSongs
     */
    public PublicWrappedItem(String username, List<String> topArtists, List<String> topSongs) {
        this.topArtists = topArtists;
        this.topSongs = topSongs;
        this.username = username;
    }

    // Getters
    /**
     * A getter for user's username.
     * @return username User's username.
     */
    public String getUsername() { return username; }

    /**
     * A getter for topArtists
     * @return topArtists List of topArtists.
     */
    public List<String> getTopArtists() { return topArtists; }
    /**
     * A getter for topSongs
     * @return topSongs List of topSongs.
     */
    public List<String> getTopSongs() { return topSongs; }
}
