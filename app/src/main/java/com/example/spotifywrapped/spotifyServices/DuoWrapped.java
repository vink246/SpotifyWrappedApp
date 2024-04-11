package com.example.spotifywrapped.spotifyServices;

import com.example.spotifywrapped.models.Artist;
import com.example.spotifywrapped.models.Track;
import com.example.spotifywrapped.spotifyServices.SpotifyProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DuoWrapped {

    private SpotifyProvider spotifyProvider = SpotifyProvider.getInstance();

    private String user1;
    private String user2;
    private String summaryId;
    private static SpotifyProvider.WrappedTerm timespan = SpotifyProvider.WrappedTerm.long_term;
    private ArrayList<Track> tracks;
    private ArrayList<Artist> artists;
    private ArrayList<String> genres;

    public DuoWrapped(String user1, String user2, String summaryId) {

    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void changeFriend(String newUser) {

    }

    public String toString() {
        return null;
    }

    private ArrayList<Track> findTracks() {
        return null;
    }

    private ArrayList<Artist> findArtists() {
        return null;
    }

    private ArrayList<String> findGenres() {
        return null;
    }

}
