package com.example.spotifywrapped;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spotifywrapped.LightActivities.Data.FriendCompLightActivity;
import com.example.spotifywrapped.LightActivities.Data.TopArtistLightActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity);

        // Find buttons by their IDs
        Button button1 = findViewById(R.id.button5);
        Button button2 = findViewById(R.id.button6);
        Button button3 = findViewById(R.id.button11);
        Button button4 = findViewById(R.id.button13);

        // Set click listeners for each button
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, TopArtistLightActivity.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, FriendCompLightActivity.class));
            }
        });

    }
}
