package com.example.spotifywrapped.firebaseServices;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.PropertyName;
import com.google.firebase.firestore.SetOptions;

import java.io.Serializable;

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

    public void addUser(String username, String email) {
        // Create a new user document with the specified data
        DocumentReference newUserRef = usersCollection.document(username);
        newUserRef.set(new User(username, email), SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Document added successfully
                        Log.d("FirebaseProvider", "User added successfully!");
                    }
                });
    }

    // Define a User class to represent user data
    private static class User implements Serializable {
        @PropertyName("username")
        public String username;
        @PropertyName("email")
        public String email;

        // Required empty constructor for Firestore deserialization
        public User() {
            // Default constructor required by Firestore
        }

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }

        // Add getters and setters as needed
    }
}
