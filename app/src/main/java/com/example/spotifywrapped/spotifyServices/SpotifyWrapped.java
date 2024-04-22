package com.example.spotifywrapped.spotifyServices;

import com.example.spotifywrapped.models.Artist;
import com.example.spotifywrapped.models.Track;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generates and maintains a SpotifyWrapped object.
 */
public class SpotifyWrapped implements Serializable {

    /** Instance of SpotifyProvider (works with Spotify API) */
    private transient SpotifyProvider spotifyProvider = SpotifyProvider.getInstance();

    /** Unique id of wrapped */
    public String summaryId;

    /** Username of user */
    public String username;

    /** Timespan of wrapped data (short, medium, long */
    public SpotifyProvider.WrappedTerm timespan;

    /** List of user's top tracks */
    private ArrayList<Track> tracks;

    /** List of user's top artists */
    private ArrayList<Artist> artists;

    /** User's top genre */
    private String topGenre;

    /**
     * Constructs a new SpotifyWrapped object by generating a summary id, setting username and timespan,
     * and populating tracks, artists, and topGenre
     *
     * @param tracks list of top tracks
     * @param artists list of top artists
     * @param timespan timespan of data
     * @param username username of user
     */
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

    /**
     * Returns the user's top track
     *
     * @return user's top track
     */
    public Track getTopTrack() {
        return tracks.get(0);
    }

    /**
     * Returns a sorted list of the user's top tracks
     * Sorted in order of most listened by user with top track first
     *
     * @return list of user's top tracks
     */
    public ArrayList<Track> getTrackList() {
        return tracks;
    }

    /**
     * Returns a formatted string of user's top tracks
     *
     * @return string of user's top tracks
     */
    public String getTrackString() {
        return "Top Songs:\n1. " +
                tracks.get(0).getName() + "\n2. " +
                tracks.get(1).getName() + "\n3. " +
                tracks.get(2).getName() + "\n4. " +
                tracks.get(3).getName() + "\n5. " +
                tracks.get(4).getName();
    }

    /**
     * Returns user's top artist
     *
     * @return user's top artist
     */
    public Artist getTopArtist() {
        return artists.get(0);
    }

    /**
     * Returns a sorted list of the user's top artists
     * Sorted in order of most listened by user with top artist first
     *
     * @return list of user's top artists
     */
    public ArrayList<Artist> getArtistList() {
        return artists;
    }

    /**
     * Returns a formatted string of user's top artists
     *
     * @return string of user's top artists
     */
    public String getArtistString() {
        return "Top Artists:\n1. " +
                artists.get(0).getName() + "\n2. " +
                artists.get(1).getName() + "\n3. " +
                artists.get(2).getName() + "\n4. " +
                artists.get(3).getName() + "\n5. " +
                artists.get(4).getName();
    }

    /**
     * Returns user's top genre
     *
     * @return user's top genre
     */
    public String getGenre() {
        return topGenre;
    }

    /**
     * Returns timespan of wrapped data in string format
     * Will return longterm, mediumterm, or shortterm
     *
     * @return timespan of wrapped data
     */
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

    /**
     * Sets username based on parameter
     *
     * @param username username of user
     */
    public void setUsername(String username) {
        spotifyProvider.getMyUserInfo(info -> {
            this.username = info.toString();
        });
    }

    /**
     * Generates summary id
     * summaryId: username yyyy-mm-dd timespan
     */
    public void setSummaryId() {
        summaryId = username + " " + LocalDate.now() + " " + getTimespan();
    }

    /**
     * Populates top tracks, top artists, and top genre based on data from SpotifyProvider
     * Note: this is no longer used in our app due to issues with async tasks
     *
     * @param timespan timespan of wrapped
     */
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

    /**
     * Returns an image string of the top artist's profile image provided by Spotify API
     *
     * @return image string of top artist
     */
    public String getImage() {
        return artists.get(0).getImage();
    }

    /**
     * Finds the user's top genre
     *
     * @return string of top genre
     */
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

    /**
     * Formats wrapped data into string to be used for debugging purposes
     *
     * @return string of wrapped data
     */
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
