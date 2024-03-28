package com.example.spotifywrapped.DarkActivities.Data;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.example.spotifywrapped.BottomNavigationActivity;
import com.example.spotifywrapped.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

public class WrappedDarkActivity extends BottomNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wrappeddark);

        // Initialize spinner
        Spinner spinner = findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_options, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Set initial selection to "Short Term"
        spinner.setSelection(adapter.getPosition("Short Term"));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set listener for bottom navigation items
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    // Handle Home selection
                    return true;
                } else if (itemId == R.id.navigation_group) {
                    // Handle Group selection
                    return true;
                } else if (itemId == R.id.navigation_history) {
                    // Handle History selection
                    return true;
                } else if (itemId == R.id.navigation_language) {
                    // Handle Language selection
                    return true;
                } else if (itemId == R.id.navigation_settings) {
                    // Handle Settings selection
                    return true;
                }
                return false;
            }
        });

        // Highlight the initially selected item (assuming "Home" is initially selected)
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }
    @Override
    protected void handleBottomNavigationItemSelected(MenuItem item) {
        // This method should be implemented to handle bottom navigation item clicks.
        // However, since we are handling item selection directly in the onCreate method,
        // you can leave this method empty.
    }
}
