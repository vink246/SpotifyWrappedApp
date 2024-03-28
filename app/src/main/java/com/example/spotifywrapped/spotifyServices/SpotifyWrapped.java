package com.example.spotifywrapped.spotifyServices;

import com.example.spotifywrapped.models.Artist;
import com.example.spotifywrapped.models.Track;

import java.util.ArrayList;

public class SpotifyWrapped {

    private SpotifyProvider spotifyProvider = SpotifyProvider.getInstance();

    public String summaryId;
    public String username;
    private SpotifyProvider.WrappedTerm timespan;
    private ArrayList<Track> tracks;
    private ArrayList<Artist> artists;
    private String topGenre;
    private int totalMinutes;

    public SpotifyWrapped(String summaryId, String username, String timespan) {
       setSummaryId(summaryId);
       setUsername(username);
       setTimespan(timespan);
       // populating tracks, artists, and topGenre is handled in setTimespan()
    }

    public Track getTopTrack() {
        return tracks.get(0);
    }

    public ArrayList<Track> getTrackList() {
        return tracks;
    }

    public Artist getTopArtist() {
        return artists.get(0);
    }

    public ArrayList<Artist> getArtistList() {
        return artists;
    }

    public String getGenre() {
        return topGenre;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }

    public void setTimespan(String timespan) {
        switch (timespan) {
            case "short_term":
                this.timespan = SpotifyProvider.WrappedTerm.short_term;
                break;
            case "medium_term":
                this.timespan = SpotifyProvider.WrappedTerm.medium_term;
                break;
            case "long_term":
            default:
                this.timespan = SpotifyProvider.WrappedTerm.long_term;
                break;
        }

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
        ArrayList<String> tempGenres;
        int genreIndex = 0;
        int topCount = 0;
        ArrayList<Integer> genreCount = new ArrayList<>();
        for (Artist artist : artists) {
            tempGenres = artist.getGenres();
            for (int i = 0; i < tempGenres.size(); i++) {
                genreIndex = genres.indexOf(tempGenres.get(i));
                if (genreIndex < 0) {
                    genreCount.set(genreIndex, 1);
                    genres.add(tempGenres.get(i));
                } else {
                    genreCount.set(genreIndex, genreCount.get(genreIndex) + 1);
                }
            }
        }
        for (int count : genreCount) {
            if (count > topCount) {
                genreIndex = genreCount.indexOf(count);
                topCount = count;
            }
        }
        return genres.get(genreIndex);
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
                topGenre + "\nTotal Minutes Listened:\n" + totalMinutes;


    }
}
