package com.example.spotifywrapped.models;

import com.google.firebase.firestore.PropertyName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    @PropertyName("username")
    public String username;

    @PropertyName("email")
    public String email;

    @PropertyName("friends")
    public List<String> friends;

    @PropertyName("outgoingFriendRequests")
    public List<String> outgoingFriendRequests;

    @PropertyName("incomingFriendRequests")
    public List<String> incomingFriendRequests;

    @PropertyName("isPublic")
    public boolean isPublic;

    @PropertyName("savedWraps")
    public List<String> savedWraps;

    // Default constructor required for Firebase
    public User() {
        // Initialize lists to prevent NullPointerException
        this.friends = new ArrayList<>();
        this.outgoingFriendRequests = new ArrayList<>();
        this.incomingFriendRequests = new ArrayList<>();
        this.savedWraps = new ArrayList<>();
    }

    // Constructor with parameters
    public User(String username, String email, boolean isPublic) {
        this.username = username;
        this.email = email;
        this.isPublic = isPublic;
        // Initialize lists
        this.friends = new ArrayList<>();
        this.outgoingFriendRequests = new ArrayList<>();
        this.incomingFriendRequests = new ArrayList<>();
        this.savedWraps = new ArrayList<>();
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List<String> getOutgoingFriendRequests() {
        return outgoingFriendRequests;
    }

    public void setOutgoingFriendRequests(List<String> outgoingFriendRequests) {
        this.outgoingFriendRequests = outgoingFriendRequests;
    }

    public List<String> getIncomingFriendRequests() {
        return incomingFriendRequests;
    }

    public void setIncomingFriendRequests(List<String> incomingFriendRequests) {
        this.incomingFriendRequests = incomingFriendRequests;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public List<String> getSavedWraps() {
        return savedWraps;
    }

    public void setSavedWraps(List<String> savedWraps) {
        this.savedWraps = savedWraps;
    }
}
