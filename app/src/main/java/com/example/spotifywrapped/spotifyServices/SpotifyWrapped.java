package com.example.spotifywrapped.spotifyServices;

import com.example.spotifywrapped.models.Artist;
import com.example.spotifywrapped.models.Track;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SpotifyWrapped {

    private SpotifyProvider spotifyProvider = SpotifyProvider.getInstance();

    public String summaryId;
    public String username;
    private String timespan;
    private ArrayList<Track> tracks;
    private ArrayList<Artist> artists;
    private String topGenre;
    private int totalMinutes;

    public SpotifyWrapped(String summaryId, String username, String timeSpan) {
       this.summaryId = summaryId;
       this.username = username;
       this.timespan = timespan;
       //TODO populate topTracks, topArtists, topGenre, and totalMinutes based on timespan

        spotifyProvider.getTopTracks(5, 0, topTracks -> {
            tracks = topTracks;
            // add code to refresh the activity here
        });
        spotifyProvider.getTopArtists(5, 0, topArtists -> {
            artists = topArtists;
            // add code to refresh the activity here
        });

        topGenre = findTopGenre();

        //TODO find way to get total minutes listened
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
}
