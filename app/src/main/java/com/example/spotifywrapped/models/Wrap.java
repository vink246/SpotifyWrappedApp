package com.example.spotifywrapped.models;

import com.example.spotifywrapped.spotifyServices.SpotifyProvider;
import com.google.firebase.firestore.PropertyName;

import org.w3c.dom.Comment;

import java.io.Serializable;
import java.util.List;

public class Wrap implements Serializable {
    @PropertyName("summaryId")
    private String summaryId;

    @PropertyName("username")
    private String username;

    @PropertyName("timespan")
    private SpotifyProvider.WrappedTerm timespan;

    @PropertyName("tracks")
    private List<Track> tracks;

    @PropertyName("artists")
    private List<Artist> artists;

    @PropertyName("topGenre")
    private String topGenre;

    @PropertyName("comments")
    private List<Comment> comments;

    @PropertyName("likesCount")
    private int likesCount;

    @PropertyName("likedBy")
    private List<String> likedBy;

    @PropertyName("isPublic")
    private boolean isPublic;

    public Wrap() {
    }

    public Wrap(String summaryId, String username, SpotifyProvider.WrappedTerm timespan, List<Track> tracks, List<Artist> artists, String topGenre, boolean isPublic) {
        this.summaryId = summaryId;
        this.username = username;
        this.timespan = timespan;
        this.tracks = tracks;
        this.artists = artists;
        this.topGenre = topGenre;
        this.isPublic = isPublic;
    }

    // Getters and setters
    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SpotifyProvider.WrappedTerm getTimespan() {
        return timespan;
    }

    public void setTimespan(SpotifyProvider.WrappedTerm timespan) {
        this.timespan = timespan;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getTopGenre() {
        return topGenre;
    }

    public void setTopGenre(String topGenre) {
        this.topGenre = topGenre;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public List<String> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<String> likedBy) {
        this.likedBy = likedBy;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}
