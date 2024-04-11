package com.example.spotifywrapped.models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents an artist in the music library.
 */
public class Artist implements Serializable {
    private String name;
    private ArrayList<String> genres;
    private int popularity;
    private String image;

    /**
     * Default constructor for Artist.
     */
    public Artist() {
        this.name = "";
        this.genres = new ArrayList<>();
        this.popularity = 0;
        this.image = "";
    }

    /**
     * Constructor for Artist with parameters.
     *
     * @param name       The name of the artist.
     * @param genres     The list of genres associated with the artist.
     * @param popularity The popularity of the artist.
     * @param image      The URL of the image associated with the artist.
     */
    public Artist(String name, ArrayList<String> genres, int popularity, String image) {
        this.name = name;
        this.genres = genres;
        this.popularity = popularity;
        this.image = image;
    }

    /**
     * Get the name of the artist.
     *
     * @return The name of the artist.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the artist.
     *
     * @param name The name of the artist to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the genres associated with the artist.
     *
     * @return The list of genres.
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * Set the genres associated with the artist.
     *
     * @param genres The list of genres to set.
     */
    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     * Get the popularity of the artist.
     *
     * @return The popularity of the artist.
     */
    public int getPopularity() {
        return popularity;
    }

    /**
     * Set the popularity of the artist.
     *
     * @param popularity The popularity of the artist to set.
     */
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    /**
     * Get the URL of the image associated with the artist.
     *
     * @return The URL of the image.
     */
    public String getImage() {
        return image;
    }

    /**
     * Set the URL of the image associated with the artist.
     *
     * @param image The URL of the image to set.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Generates a string representation of the Artist object.
     *
     * @return A string representing the Artist object.
     */
    @NonNull
    @Override
    public String toString() {
        return "Artist{" + System.lineSeparator() +
                "name='" + name + '\'' + System.lineSeparator() +
                "genres=" + genres + System.lineSeparator() +
                "popularity=" + popularity + System.lineSeparator() +
                "image='" + image + '\'' + System.lineSeparator() +
                '}';
    }
}
