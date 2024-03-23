package com.example.spotifywrapped.spotifyServices;

import com.example.spotifywrapped.models.Artist;
import com.example.spotifywrapped.models.Track;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SpotifyWrapped {

    public String summaryId;
    public String username;
    private String timespan;
    private ArrayList<Track> topTracks;
    private ArrayList<Artist> topArtists;
    private String topGenre;
    private int totalMinutes;

    public SpotifyWrapped(String summaryId, String username, String timeSpan) {
       this.summaryId = summaryId;
       this.username = username;
       this.timespan = timespan;
       //TODO populate topTracks, topArtists, topGenre, and totalMinutes based on timespan

        ArrayList<String> genres = new ArrayList<>();
        ArrayList<String> tempGenres;
        int genreIndex;
        ArrayList<Integer> genreCount = new ArrayList<>();
        for (Artist artist : topArtists) {
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
        //TODO find max genreCount and set topGenre to that one
    }

    //TODO constructor or error checking for new account (<5 songs/artists listened to)

}
