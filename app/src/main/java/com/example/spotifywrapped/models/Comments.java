package com.example.spotifywrapped.models;

import java.io.Serializable;

public class Comments implements Serializable {
    private String userId;
    private String text;

    /**
     * Default Constructor of comments with no parameters.
     */
    public Comments() {
    }

    /**
     * Constructor for comments with userId, text, and timestamp as parameters.
     * @param userId User's userId.
     * @param text The string of the text for the comment object.
     */
    public Comments(String userId, String text) {
        this.userId = userId;
        this.text = text;
    }

    // Getters and setters

    /**
     * A getter for userId.
     * @return Returns user's userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * A setter for user's userId.
     * @param userId Sets userId to whatever is inputted.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * A getter for text.
     * @return Returns the comment's text.
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for comment's text.
     * @param text Sets comments to whatever is inputted.
     */
    public void setText(String text) {
        this.text = text;
    }
}