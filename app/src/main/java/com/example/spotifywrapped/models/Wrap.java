package com.example.spotifywrapped.models;

import com.example.spotifywrapped.spotifyServices.SpotifyProvider;
import com.google.firebase.firestore.PropertyName;

import org.w3c.dom.Comment;

import java.io.Serializable;
import java.util.ArrayList;
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

    /**
     * Default constructor of Wrap.
     */
    public Wrap() {
        tracks = new ArrayList<>();
        artists = new ArrayList<>();
        comments = new ArrayList<>();
        likedBy = new ArrayList<>();
    }

    /**
     * Constructor with summaryId, username, time-span, tracks, artists, topGenre, and isPublic as
     * parameters.
     *
     * @param summaryId The summaryId of a wrap.
     * @param username User's username.
     * @param timespan The timespan of the wrap i.e short, medium, long.
     * @param tracks Tracks within the wrap.
     * @param artists Artists within the wrap.
     * @param topGenre The user's top genre for the wrap.
     * @param isPublic The boolean that tells us if wrap is public or not.
     */
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

    /**
     * A getter for summaryId.
     * @return summaryID SummaryId for the wrap.
     */
    public String getSummaryId() {
        return summaryId;
    }

    /**
     * A setter for summaryId.
     * @param summaryId Sets the summaryId to whatever is inputted.
     */
    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }

    /**
     * A getter for user's username.
     * @return username User's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * A setter for user's username.
     * @param username Sets user's username to whatever is inputted.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * A getter for Wrap's timespan.
     * @return timespan Returns the wrap's timespan.
     */
    public SpotifyProvider.WrappedTerm getTimespan() {
        return timespan;
    }

    /**
     * A setter for Wrap's timespan.
     * @param timespan Sets the wrap's timespan to whatever is inputted..
     */
    public void setTimespan(SpotifyProvider.WrappedTerm timespan) {
        this.timespan = timespan;
    }

    /**
     * A getter for Wrap's tracks.
     * @return Tracks in the Wrap.
     */
    public List<Track> getTracks() {
        return tracks;
    }

    /**
     * A setter for tracks.
     * @param tracks Sets Wrap's tracks to whatever is inputted.
     */
    public void  setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    /**
     * A getter for Wrap's artists.
     * @return artists Returns artists in the wrap.
     */
    public List<Artist> getArtists() {
        return artists;
    }

    /**
     * A setter for Wrap's artists.
     * @param artists Sets the wrap's artists to whatever is inputted.
     */
    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    /**
     * A getter for Wrap's topGenre.
     * @return topGenre Return Wrap's top genre.
     */
    public String getTopGenre() {
        return topGenre;
    }

    /**
     * A setter for topGenre
     * @param topGenre Sets Wrap's topGenre to whatever is inputted.
     */
    public void setTopGenre(String topGenre) {
        this.topGenre = topGenre;
    }

    /**
     * Gets comments from Wrap.
     * @return comments Returns comments from Wrap.
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * A setter for Wrap's comments.
     * @param comments Sets Wrap's comments to whatever is inputted.
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * A getter for likesCount for Wrap.
     * @return likesCount Returns the likesCount of a Wrap.
     */
    public int getLikesCount() {
        return likesCount;
    }

    /**
     * A setter for likesCount.
     * @param likesCount Sets likesCount to whatever is inputted.
     */
    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    /**
     * A getter for likedBy.
     * @return likedBy Returns a list of who liked Wrap.
     */
    public List<String> getLikedBy() {
        return likedBy;
    }

    /**
     * A setter for likedBy.
     * @param likedBy Set list of likedBy for Wrap.
     */
    public void setLikedBy(List<String> likedBy) {
        this.likedBy = likedBy;
    }

    /**
     * A getter for isPublic for wraps.
     * @return isPublic A boolean that shows whether a wrap is public.
     */
    public boolean isPublic() {
        return isPublic;
    }

    /**
     * Setter for wrap public boolean.
     * @param isPublic Sets boolean to public.
     */
    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}