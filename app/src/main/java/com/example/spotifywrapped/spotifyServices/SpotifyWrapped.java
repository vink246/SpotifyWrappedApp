package com.example.spotifywrapped.spotifyServices;

import android.util.Log;
import android.widget.Switch;

import com.example.spotifywrapped.models.Artist;
import com.example.spotifywrapped.models.SpotifyUser;
import com.example.spotifywrapped.models.Track;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SpotifyWrapped {

    private SpotifyProvider spotifyProvider = SpotifyProvider.getInstance();

    public String summaryId;
    public String username;
    private SpotifyProvider.WrappedTerm timespan;
    private ArrayList<Track> tracks;
    private ArrayList<Artist> artists;
    private String topGenre;
    //private int totalMinutes;

    public SpotifyWrapped(ArrayList<Track> tracks, ArrayList<Artist> artists, SpotifyProvider.WrappedTerm timespan, String username) {
        this.timespan = timespan;
        this.username = username;
        this.tracks = tracks;
        this.artists = artists;
        this.topGenre = findTopGenre();
        setSummaryId();
        //setUsername(username);
        //setTimespan(timespan);
    }

    public Track getTopTrack() {
        return tracks.get(0);
    }

    public ArrayList<Track> getTrackList() {
        return tracks;
    }
    public String getTrackString() {
        return "Top Songs:\n1. " +
                tracks.get(0).getName() + "\n2. " +
                tracks.get(1).getName() + "\n3. " +
                tracks.get(2).getName() + "\n4. " +
                tracks.get(3).getName() + "\n5. " +
                tracks.get(4).getName();
    }

    public Artist getTopArtist() {
        return artists.get(0);
    }
    public ArrayList<Artist> getArtistList() {
        return artists;
    }
    public String getArtistString() {
        return "Top Artists:\n1. " +
                artists.get(0).getName() + "\n2. " +
                artists.get(1).getName() + "\n3. " +
                artists.get(2).getName() + "\n4. " +
                artists.get(3).getName() + "\n5. " +
                artists.get(4).getName();
    }
    public String getGenre() {
        return topGenre;
    }
    public String getTimespan() {
        switch (timespan) {
            case long_term:
                return "longterm";
            case medium_term:
                return "mediumterm";
            case short_term:
                return "shortterm";
        }
        return "mediumterm";
    }
    public void setUsername(String username) {
        spotifyProvider.getMyUserInfo(info -> {
            this.username = info.toString();
        });
    }

    public void setSummaryId() {
        summaryId = username + new Date().toString() + getTimespan();
    }

    public void setTimespan(SpotifyProvider.WrappedTerm timespan) {
        this.timespan = timespan;

        spotifyProvider.getTopTracks(5, 0, this.timespan, topTracks -> {
            tracks = topTracks;
            // add code to refresh the activity here
        });
        spotifyProvider.getTopArtists(5, 0, this.timespan, topArtists -> {
            artists = topArtists;
            // add code to refresh the activity here
        });

        this.topGenre = findTopGenre();
    }

    public String getImage() {
        return artists.get(0).getImage();
    }

    private String findTopGenre() {
        ArrayList<String> genres = new ArrayList<>();
        for (Artist artist: artists) {
            genres.addAll(artist.getGenres());
        }
        Map<String, Integer> frequencyMap = new HashMap<>();
        // Count the frequency of each element
        for (String item : genres) {
            frequencyMap.put(item, frequencyMap.getOrDefault(item, 0) + 1);
        }

        // Find the mode (element with highest frequency)
        int maxFrequency = Collections.max(frequencyMap.values());
        String mode = "";
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                mode = entry.getKey();
                break; // Stop after finding the first mode
            }
        }
        return mode;
    }

    public String toSting() {
        return "Top Songs:\n1. " +
                tracks.get(0).getName() + "\n2. " +
                tracks.get(1).getName() + "\n3. " +
                tracks.get(2).getName() + "\n4. " +
                tracks.get(3).getName() + "\n5. " +
                tracks.get(4).getName() + "\nTop Artists:\n1. " +
                artists.get(0).getName() + "\n2. " +
                artists.get(1).getName() + "\n3. " +
                artists.get(2).getName() + "\n4. " +
                artists.get(3).getName() + "\n5. " +
                artists.get(4).getName() + "\nTop Genre:\n" +
                topGenre;


    }
}
