package com.example.spotifywrapped.firebaseServices;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.spotifywrapped.models.User;
import com.example.spotifywrapped.models.Wrap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides Firebase Firestore services for user management and data handling.
 */
public class FirebaseProvider {
    private static FirebaseProvider instance; // Singleton instance
    private FirebaseFirestore db;
    private CollectionReference usersCollection;
    private CollectionReference publicWrappedCollection;

    /**
     * Private constructor to prevent direct instantiation.
     */
    private FirebaseProvider() {
        // Initialize Firestore
        db = FirebaseFirestore.getInstance();
        // Reference to the "users" collection
        usersCollection = db.collection("users");
        // Reference to the "public-wraps" collection
        publicWrappedCollection = db.collection("public-wraps");
    }

    /**
     * Gets the singleton instance of FirebaseProvider.
     *
     * @return The FirebaseProvider instance.
     */
    public static synchronized FirebaseProvider getInstance() {
        if (instance == null) {
            instance = new FirebaseProvider();
        }
        return instance;
    }

    /**
     * Adds a user to Firestore.
     *
     * @param user The user object to add.
     */
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

    /**
     * Updates the public status of a user.
     *
     * @param username The username of the user.
     * @param isPublic The public status to set.
     */
    public void setUserPublic(String username, boolean isPublic) {
        if (username != null) {
            Log.d("FirebaseProvider", "Updating User Public Status...");
            DocumentReference userRef = usersCollection.document(username);
            userRef.update("isPublic", isPublic)
                    .addOnSuccessListener(aVoid -> {
                        Log.d("FirebaseProvider", "User public status updated successfully!");
                        // Handle the public setting toggle
                        handlePublicWrapsToggle(userRef, isPublic, username);
                    })
                    .addOnFailureListener(e -> Log.e("FirebaseProvider", "Error updating user public status", e));
        } else {
            Log.e("FirebaseProvider", "Username is null");
        }
    }

    private void handlePublicWrapsToggle(DocumentReference userRef, boolean isPublic, String username) {
        userRef.get().addOnSuccessListener(documentSnapshot -> {
            User user = documentSnapshot.toObject(User.class);
            if (user != null && user.getSavedWraps() != null) {
                for (Wrap wrap : user.getSavedWraps()) {
                    DocumentReference publicWrapRef = publicWrappedCollection.document(wrap.getSummaryId());
                    if (isPublic) {
                        // Publish wrap to public collection
                        wrap.setPublic(true); // Ensure the wrap is marked as public
                        publicWrapRef.set(wrap, SetOptions.merge())
                                .addOnSuccessListener(aVoid -> Log.d("FirebaseProvider", "Public Wrap added successfully for user " + username + " with summary ID: " + wrap.getSummaryId()))
                                .addOnFailureListener(e -> Log.e("FirebaseProvider", "Error adding public Wrap for user " + username, e));
                    } else {
                        // Remove wrap from public collection
                        publicWrapRef.delete()
                                .addOnSuccessListener(aVoid -> Log.d("FirebaseProvider", "Public Wrap removed successfully for user " + username + " with summary ID: " + wrap.getSummaryId()))
                                .addOnFailureListener(e -> Log.e("FirebaseProvider", "Error removing public Wrap for user " + username, e));
                    }
                }
            }
        });
    }

    /**
     * Fetches the public status of a user.
     *
     * @param username          The username of the user.
     * @param onCompleteListener Listener for completion of the operation.
     */
    public void getUserPublic(String username, OnCompleteListener<Boolean> onCompleteListener) {
        if (username != null) {
            Log.d("FirebaseProvider", "Fetching User Public Status...");
            DocumentReference userRef = usersCollection.document(username);
            userRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                        Boolean isPublic = documentSnapshot.getBoolean("isPublic");
                        Task<Boolean> taskResult = Tasks.forResult(isPublic);
                        onCompleteListener.onComplete(taskResult);
                    } else {
                        Log.d("FirebaseProvider", "No such user exists");
                        Task<Boolean> taskResult = Tasks.forResult(false); // Default to false if user does not exist
                        onCompleteListener.onComplete(taskResult);
                    }
                } else {
                    Log.e("FirebaseProvider", "Error fetching user public status", task.getException());
                    onCompleteListener.onComplete(null);
                }
            });
        } else {
            Log.e("FirebaseProvider", "Username is null");
            onCompleteListener.onComplete(Tasks.forResult(false));
        }
    }

    /**
     * Saves a wrap to a user's profile.
     *
     * @param userId  The ID of the user.
     * @param wrap    The Wrap object to save.
     * @param context The context for displaying messages.
     */
    public void saveWrap(String userId, Wrap wrap, Context context) {
        DocumentReference userRef = usersCollection.document(userId);
        // Check if the wrap with the same summaryId already exists
        userRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                List<Wrap> savedWraps = documentSnapshot.toObject(User.class).getSavedWraps();
                boolean isPublic = documentSnapshot.toObject(User.class).isPublic();
                if (savedWraps != null) {
                    boolean wrapExists = savedWraps.stream().anyMatch(savedWrap -> savedWrap.getSummaryId().equals(wrap.getSummaryId()));
                    if (!wrapExists) {
                        if (isPublic) wrap.setPublic(true);
                        // Add the wrap to the array only if it doesn't already exist
                        userRef.update("savedWraps", FieldValue.arrayUnion(wrap));
                        DocumentReference wrapRef = publicWrappedCollection.document(wrap.getSummaryId());
                        if (isPublic) {
                            wrapRef.set(wrap, SetOptions.merge())
                                    .addOnSuccessListener(aVoid -> Log.d("FirebaseProvider", "Public Wrap added successfully!"))
                                    .addOnFailureListener(e -> Log.e("FirebaseProvider", "Error adding public Wrap", e));
                        }
                        Toast.makeText(context, "Wrap Saved!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Wrap has already been Saved!", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(context, "Unable to save: database error!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    // Method to remove a saved Wrap from a User's profile
    public void unsaveWrap(String userId, Wrap wrap) {
        DocumentReference userRef = usersCollection.document(userId);
        userRef.update("savedWraps", FieldValue.arrayRemove(wrap));
    }

    /**
     * Fetches the saved wraps for a user.
     *
     * @param userId             The ID of the user.
     * @param onCompleteListener Listener to handle the completion of the task.
     */
    public void getSavedWraps(String userId, OnCompleteListener<ArrayList<Wrap>> onCompleteListener) {
        usersCollection.document(userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if (documentSnapshot.exists()) {
                            ArrayList<Wrap> savedWraps = new ArrayList<>();
                            List<Wrap> wrapList = documentSnapshot.toObject(User.class).getSavedWraps();

                            if (wrapList != null) {
                                savedWraps.addAll(wrapList);
                            }

                            Task<ArrayList<Wrap>> taskResult = Tasks.forResult(savedWraps);
                            onCompleteListener.onComplete(taskResult);
                        } else {
                            Log.d("FirebaseProvider", "No such document");
                            Task<ArrayList<Wrap>> emptyTask = Tasks.forResult(new ArrayList<>());
                            onCompleteListener.onComplete(emptyTask);
                        }
                    } else {
                        Log.d("FirebaseProvider", "get failed with ", task.getException());
                        onCompleteListener.onComplete(null);
                    }
                });
    }

    /**
     * Fetches public wraps from the database.
     *
     * @param successListener Listener to handle the success of the task.
     */
    public void getPublicWraps(OnSuccessListener<List<Wrap>> successListener) {
        // Get the public wraps collection from Firebase Database.
        publicWrappedCollection.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Initialize a list for public wraps
                    List<Wrap> publicWraps = new ArrayList<>();
                    // Iterate through the query to Wrap objects
                    for (Wrap wrap : queryDocumentSnapshots.toObjects(Wrap.class)) {
                        // Add the Wrap object to the list
                        publicWraps.add(wrap);
                    }
                    // Notify the success listener with the list of public wraps
                    successListener.onSuccess(publicWraps);
                })
                .addOnFailureListener(e -> Log.e("FirebaseProvider", "Error fetching public wraps", e));
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
