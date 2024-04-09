package com.example.spotifywrapped.firebaseServices;

import android.util.Log;

import com.example.spotifywrapped.models.User;
import com.example.spotifywrapped.models.Wrap;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

public class FirebaseProvider {
    private static FirebaseProvider instance; // Singleton instance
    private FirebaseFirestore db;
    private CollectionReference usersCollection;
    private CollectionReference publicWrappedCollection;

    // Private constructor to prevent direct instantiation
    private FirebaseProvider() {
        // Initialize Firestore
        db = FirebaseFirestore.getInstance();
        // Reference to the "users" collection
        usersCollection = db.collection("users");
        // Reference to the "public-wraps" collection
        publicWrappedCollection = db.collection("public-wraps");
    }

    // Static method to get the Singleton instance
    public static synchronized FirebaseProvider getInstance() {
        if (instance == null) {
            instance = new FirebaseProvider();
        }
        return instance;
    }

    // Updated method to add a User object to Firestore
    public void addUser(User user) {
        if (user != null && user.getUsername() != null) {
            Log.d("FirebaseProvider", "Adding User...");

            DocumentReference userRef = usersCollection.document(user.getUsername());
            userRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    Log.d("FirebaseProvider", "User already exists in the database.");
                } else {
                    userRef.set(user, SetOptions.merge())
                            .addOnSuccessListener(aVoid -> Log.d("FirebaseProvider", "User added successfully!"))
                            .addOnFailureListener(e -> Log.e("FirebaseProvider", "Error adding user", e));
                }
            }).addOnFailureListener(e -> Log.e("FirebaseProvider", "Error checking user existence", e));
        } else {
            Log.e("FirebaseProvider", "User object or username is null");
        }
    }

    public void setUserPublic(String username, boolean isPublic) {
        if (username != null) {
            Log.d("FirebaseProvider", "Updating User Public Status...");
            // Get a reference to the user document by username
            DocumentReference userRef = usersCollection.document(username);
            // Update the 'isPublic' field using SetOptions.merge()
            userRef.update("isPublic", isPublic)
                    .addOnSuccessListener(aVoid -> Log.d("FirebaseProvider", "User public status updated successfully!"))
                    .addOnFailureListener(e -> Log.e("FirebaseProvider", "Error updating user public status", e));
        } else {
            Log.e("FirebaseProvider", "Username is null");
        }
    }

    // Method to add a Wrap object to Firestore
    public void addWrap(Wrap wrap) {
        if (wrap != null && wrap.getSummaryId() != null) {
            publicWrappedCollection.document(wrap.getSummaryId())
                    .set(wrap, SetOptions.merge())
                    .addOnSuccessListener(aVoid -> Log.d("FirebaseProvider", "Wrap added successfully!"))
                    .addOnFailureListener(e -> Log.e("FirebaseProvider", "Error adding wrap", e));
        } else {
            Log.e("FirebaseProvider", "Wrap object or summary ID is null");
        }
    }

    // Method to fetch public wraps
    public void getPublicWraps(OnSuccessListener<List<Wrap>> successListener) {
        publicWrappedCollection.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Wrap> publicWraps = new ArrayList<>();
            for (Wrap wrap : queryDocumentSnapshots.toObjects(Wrap.class)) {
                if (wrap.isPublic()) {
                    publicWraps.add(wrap);
                }
            }
            successListener.onSuccess(publicWraps);
        }).addOnFailureListener(e -> Log.e("FirebaseProvider", "Error fetching public wraps", e));
    }

    // Method to send a friend request
    public void sendFriendRequest(String requesterId, String recipientId) {
        DocumentReference requesterRef = usersCollection.document(requesterId);
        requesterRef.update("outgoingFriendRequests", FieldValue.arrayUnion(recipientId));
        DocumentReference recipientRef = usersCollection.document(recipientId);
        recipientRef.update("incomingFriendRequests", FieldValue.arrayUnion(requesterId));
    }

    // Method to accept a friend request
    public void acceptFriendRequest(String requesterId, String accepterId) {
        DocumentReference accepterRef = usersCollection.document(accepterId);
        accepterRef.update("friends", FieldValue.arrayUnion(requesterId));
        accepterRef.update("incomingFriendRequests", FieldValue.arrayRemove(requesterId));
        DocumentReference requesterRef = usersCollection.document(requesterId);
        requesterRef.update("friends", FieldValue.arrayUnion(accepterId));
        requesterRef.update("outgoingFriendRequests", FieldValue.arrayRemove(accepterId));
    }

    // Method to reject a friend request
    public void rejectFriendRequest(String requesterId, String rejecterId) {
        DocumentReference rejecterRef = usersCollection.document(rejecterId);
        rejecterRef.update("incomingFriendRequests", FieldValue.arrayRemove(requesterId));
        DocumentReference requesterRef = usersCollection.document(requesterId);
        requesterRef.update("outgoingFriendRequests", FieldValue.arrayRemove(rejecterId));
    }

    // Method to remove a friend
    public void removeFriend(String userId, String friendId) {
        DocumentReference userRef = usersCollection.document(userId);
        userRef.update("friends", FieldValue.arrayRemove(friendId));
        DocumentReference friendRef = usersCollection.document(friendId);
        friendRef.update("friends", FieldValue.arrayRemove(userId));
    }

    // Method to save a Wrap to a User's profile
    public void saveWrap(String userId, String wrapId) {
        DocumentReference userRef = usersCollection.document(userId);
        userRef.update("savedWraps", FieldValue.arrayUnion(wrapId));
    }

    // Method to remove a saved Wrap from a User's profile
    public void unsaveWrap(String userId, String wrapId) {
        DocumentReference userRef = usersCollection.document(userId);
        userRef.update("savedWraps", FieldValue.arrayRemove(wrapId));
    }

    // If you need to fetch the saved wraps for display:
    public void getSavedWraps(String userId, OnSuccessListener<List<Wrap>> successListener) {
        usersCollection.document(userId).get().addOnSuccessListener(documentSnapshot -> {
            User user = documentSnapshot.toObject(User.class);
            if (user != null && user.getSavedWraps() != null) {
                List<Wrap> savedWraps = new ArrayList<>();
                for (String wrapId : user.getSavedWraps()) {
                    publicWrappedCollection.document(wrapId).get().addOnSuccessListener(wrapSnapshot -> {
                        Wrap wrap = wrapSnapshot.toObject(Wrap.class);
                        if (wrap != null) {
                            savedWraps.add(wrap);
                            if (savedWraps.size() == user.getSavedWraps().size()) {
                                successListener.onSuccess(savedWraps);
                            }
                        }
                    });
                }
            }
        });
    }

    // Comments for Wraps
    public void addCommentToWrap(String wrapId, Comment comment) {
        DocumentReference wrapRef = publicWrappedCollection.document(wrapId);
        wrapRef.update("comments", FieldValue.arrayUnion(comment));
    }

    // Likes for Wraps
    public void likeWrap(String userId, String wrapId) {
        DocumentReference wrapRef = publicWrappedCollection.document(wrapId);
        wrapRef.get().addOnSuccessListener(documentSnapshot -> {
            Wrap wrap = documentSnapshot.toObject(Wrap.class);
            if (wrap != null && !wrap.getLikedBy().contains(userId)) {
                wrapRef.update("likesCount", wrap.getLikesCount() + 1);
                wrapRef.update("likedBy", FieldValue.arrayUnion(userId));
            }
        });
    }

    // Unliking a Wrap
    public void unlikeWrap(String userId, String wrapId) {
        DocumentReference wrapRef = publicWrappedCollection.document(wrapId);
        wrapRef.get().addOnSuccessListener(documentSnapshot -> {
            Wrap wrap = documentSnapshot.toObject(Wrap.class);
            if (wrap != null && wrap.getLikedBy().contains(userId)) {
                wrapRef.update("likesCount", wrap.getLikesCount() - 1);
                wrapRef.update("likedBy", FieldValue.arrayRemove(userId));
            }
        });
    }

}
