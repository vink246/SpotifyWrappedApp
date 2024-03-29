package com.example.spotifywrapped.models;

import java.io.Serializable;

/**
 * Represents a Spotify user with basic information.
 */
public class SpotifyUser implements Serializable {
    private String username;
    private String email;
    private String uri;

    /**
     * Constructs a SpotifyUser object with the specified attributes.
     *
     * @param username The username of the Spotify user.
     * @param email    The email address of the Spotify user.
     * @param uri      The URI of the Spotify user.
     */
    public SpotifyUser(String username, String email, String uri) {
        this.username = username;
        this.email = email;
        this.uri = uri;
    }

    /**
     * Gets the username of the Spotify user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the Spotify user.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email address of the Spotify user.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the Spotify user.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the URI of the Spotify user.
     *
     * @return The URI.
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the URI of the Spotify user.
     *
     * @param uri The URI to set.
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "SpotifyUser{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
