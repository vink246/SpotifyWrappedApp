package com.example.spotifywrapped.models;

import java.io.Serializable;

public class Comments implements Serializable {
    private String userId;
    private String text;
    private long timestamp;  // Consider using System.currentTimeMillis() for setting this

    public Comments() {
    }

    public Comments(String userId, String text, long timestamp) {
        this.userId = userId;
        this.text = text;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
