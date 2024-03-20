package com.example.spotifywrapped.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * Represents a track in the music library.
 */
public class Track {
    private ArrayList<String> artists;
    private long length;
    private String uri;
    private String name;
    private int popularity;
    private String image;
    private String preview_url;

    /**
     * Default constructor for Track.
     */
    public Track() {
        this.artists = new ArrayList<>();
        this.length = 0;
        this.uri = "";
        this.name = "";
        this.popularity = 0;
        this.image = "";
        this.preview_url = "";
    }

    /**
     * Constructor for Track with parameters.
     *
     * @param artists    The list of artists associated with the track.
     * @param length     The length of the track in milliseconds.
     * @param uri        The URI of the track.
     * @param name       The name of the track.
     * @param popularity The popularity of the track.
     * @param image      The URL of the image associated with the track.
     * @param preview_url The preview URL of the track.
     */
    public Track(ArrayList<String> artists, long length, String uri, String name, int popularity, String image, String preview_url) {
        this.artists = artists;
        this.length = length;
        this.uri = uri;
        this.name = name;
        this.popularity = popularity;
        this.image = image;
        this.preview_url = preview_url;
    }

    /**
     * Get the list of artists associated with the track.
     *
     * @return The list of artists.
     */
    public ArrayList<String> getArtists() {
        return artists;
    }

    /**
     * Set the list of artists associated with the track.
     *
     * @param artists The list of artists to set.
     */
    public void setArtists(ArrayList<String> artists) {
        this.artists = artists;
    }

    /**
     * Get the length of the track in milliseconds.
     *
     * @return The length of the track.
     */
    public long getLength() {
        return length;
    }

    /**
     * Set the length of the track in milliseconds.
     *
     * @param length The length of the track to set.
     */
    public void setLength(long length) {
        this.length = length;
    }

    /**
     * Get the URI of the track.
     *
     * @return The URI of the track.
     */
    public String getUri() {
        return uri;
    }

    /**
     * Set the URI of the track.
     *
     * @param uri The URI of the track to set.
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Get the name of the track.
     *
     * @return The name of the track.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the track.
     *
     * @param name The name of the track to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the popularity of the track.
     *
     * @return The popularity of the track.
     */
    public int getPopularity() {
        return popularity;
    }

    /**
     * Set the popularity of the track.
     *
     * @param popularity The popularity of the track to set.
     */
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    /**
     * Get the URL of the image associated with the track.
     *
     * @return The URL of the image.
     */
    public String getImage() {
        return image;
    }

    /**
     * Set the URL of the image associated with the track.
     *
     * @param image The URL of the image to set.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Get the preview URL of the track.
     *
     * @return The preview URL of the track.
     */
    public String getPreviewUrl() {
        return preview_url;
    }

    /**
     * Set the preview URL of the track.
     *
     * @param preview_url The preview URL of the track to set.
     */
    public void setPreviewUrl(String preview_url) {
        this.preview_url = preview_url;
    }

    /**
     * Generates a string representation of the Track object.
     *
     * @return A string representing the Track object.
     */
    @NonNull
    @Override
    public String toString() {
        return "Track{" + System.lineSeparator() +
                "artists=" + artists + System.lineSeparator() +
                "length=" + length + System.lineSeparator() +
                "uri='" + uri + '\'' + System.lineSeparator() +
                "name='" + name + '\'' + System.lineSeparator() +
                "popularity=" + popularity + System.lineSeparator() +
                "image='" + image + '\'' + System.lineSeparator() +
                "preview_url='" + preview_url + '\'' + System.lineSeparator() +
                '}';
    }
}