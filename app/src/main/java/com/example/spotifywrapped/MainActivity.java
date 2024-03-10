package com.example.spotifywrapped;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int LOADING_SCREEN_DELAY = 3000; // Delay in milliseconds (3 seconds)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingscreen);

        // Delay for 3 seconds and then navigate to the login screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish(); // Optional: Finish the current activity to prevent going back to it
            }
        }, LOADING_SCREEN_DELAY);
    }
}
