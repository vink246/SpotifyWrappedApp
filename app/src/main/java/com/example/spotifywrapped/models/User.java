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
    public List<Wrap> savedWraps;

    /**
     * Default constructor with no parameters purely for Firebase.
     */
    public User() {
        // Initialize lists to prevent NullPointerException
        this.friends = new ArrayList<>();
        this.outgoingFriendRequests = new ArrayList<>();
        this.incomingFriendRequests = new ArrayList<>();
        this.savedWraps = new ArrayList<>();
    }


    /**
     * Construtor for Users with the listed parameters below.
     *
     * @param username Username of the user.
     * @param email Email address of the user.
     * @param isPublic boolean for whether the user's account is public.
     */
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

    /**
     * A getter for profile's username.
     * @return username Profile's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * A setter for  profile's username.
     * @param username Sets the username to whatever is inputted.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * A getter for user's email.
     * @return email Email of user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * A setter for email.
     * @param email Sets email to whatever is inputted.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * A getter for friend's list.
     * @return friends List of friends.
     */
    public List<String> getFriends() {
        return friends;
    }

    /**
     * A setter for friend's list.
     * @param friends Sets friendList to whatever is inputted.
     */
    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    /**
     * A getter for outgoingFriendRequests.
     * @return outgoingFriendRequest Returns list of outgoingFriendRequests.
     */
    public List<String> getOutgoingFriendRequests() {
        return outgoingFriendRequests;
    }

    /**
     * A setterf or outgoingFriendRequests.
     * @param outgoingFriendRequests Sets new list for outgoingFriendRequests.
     */
    public void setOutgoingFriendRequests(List<String> outgoingFriendRequests) {
        this.outgoingFriendRequests = outgoingFriendRequests;
    }

    /**
     * A getter for incomingFriendRequests.
     * @return incomingFriendRequests List of incomingFriendRequests.
     */
    public List<String> getIncomingFriendRequests() {
        return incomingFriendRequests;
    }

    /**
     * A setter for incomingFriendRequests.
     * @param incomingFriendRequests Sets new list for incomingFriendRequests.
     */
    public void setIncomingFriendRequests(List<String> incomingFriendRequests) {
        this.incomingFriendRequests = incomingFriendRequests;
    }

    /**
     * A method that returns boolean if profile is public.
     * @return isPublic Returns whether profile is public.
     */
    public boolean isPublic() {
        return isPublic;
    }

    /**
     * Setter to set boolean public.
     * @param isPublic boolean that
     */
    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * A getter for savedWraps.
     * @return savedWraps Returns user's savedWraps.
     */
    public List<Wrap> getSavedWraps() {
        return savedWraps;
    }

    /**
     * A setter for savedWraps.
     * @param savedWraps Sets a new savedWraps.
     */
    public void setSavedWraps(List<Wrap> savedWraps) {
        this.savedWraps = savedWraps;
    }
}
