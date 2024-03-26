package com.example.spotifywrapped;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import Utility.DarkModePreferenceManager;

public class WrapActivity extends AppCompatActivity {

    private Spinner startMonthSpinner;
    private Spinner startDateSpinner;
    private Spinner startYearSpinner;
    private Spinner endMonthSpinner;
    private Spinner endDateSpinner;
    private Spinner endYearSpinner;
    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (DarkModePreferenceManager.isDarkMode(getApplicationContext())) {
            setContentView(R.layout.wrappeddark);
            settingsButton = findViewById(R.id.dark_settings_button);
            initializeSpinnersForDark();
        } else {
            setContentView(R.layout.wrappedlight);
            settingsButton = findViewById(R.id.light_settings_button);
            initializeSpinnersForLight();
        }

        settingsButton.setOnClickListener(v -> navigateToSettingsActivity());
    }

    private void initializeSpinnersForDark() {
        // Initialize spinners
        startMonthSpinner = findViewById(R.id.dSpinnerStartMonth);
        startDateSpinner = findViewById(R.id.dSpinnerStartDay);
        startYearSpinner = findViewById(R.id.dSpinnerStartYear);
        endMonthSpinner = findViewById(R.id.dSpinnerEndMonth);
        endDateSpinner = findViewById(R.id.dSpinnerEndDay);
        endYearSpinner = findViewById(R.id.dSpinnerEndYear);

        // Populate spinners
        setupSpinners(startDateSpinner, 1, 31, R.layout.dark_spinner_item, R.layout.dark_spinner_item);
        setupSpinners(startYearSpinner, 2000, 2099, R.layout.dark_spinner_item, R.layout.dark_spinner_item);
        setupSpinners(endDateSpinner, 1, 31, R.layout.dark_spinner_item, R.layout.dark_spinner_item);
        setupSpinners(endYearSpinner, 2000, 2099, R.layout.dark_spinner_item, R.layout.dark_spinner_item);
        setupMonthSpinner(startMonthSpinner, R.layout.dark_spinner_item, R.layout.dark_spinner_item);
        setupMonthSpinner(endMonthSpinner, R.layout.dark_spinner_item, R.layout.dark_spinner_item);
    }

    private void initializeSpinnersForLight() {
        // Initialize spinners
        startMonthSpinner = findViewById(R.id.lSpinnerStartMonth);
        startDateSpinner = findViewById(R.id.lSpinnerStartDay);
        startYearSpinner = findViewById(R.id.lSpinnerStartYear);
        endMonthSpinner = findViewById(R.id.lSpinnerEndMonth);
        endDateSpinner = findViewById(R.id.lSpinnerEndDay);
        endYearSpinner = findViewById(R.id.lSpinnerEndYear);

        // Populate spinners
        setupSpinners(startDateSpinner, 1, 31, R.layout.light_spinner_item, R.layout.light_spinner_item);
        setupSpinners(startYearSpinner, 2000, 2099, R.layout.light_spinner_item, R.layout.light_spinner_item);
        setupSpinners(endDateSpinner, 1, 31, R.layout.light_spinner_item, R.layout.light_spinner_item);
        setupSpinners(endYearSpinner, 2000, 2099, R.layout.light_spinner_item, R.layout.light_spinner_item);
        setupMonthSpinner(startMonthSpinner, R.layout.light_spinner_item, R.layout.light_spinner_item);
        setupMonthSpinner(endMonthSpinner, R.layout.light_spinner_item, R.layout.light_spinner_item);
    }

    private void setupSpinners(Spinner spinner, int min, int max, int layoutResource, int dropdownResource) {
        List<String> list = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            list.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, layoutResource, list);
        adapter.setDropDownViewResource(dropdownResource);
        spinner.setAdapter(adapter);
    }

    private void setupMonthSpinner(Spinner spinner, int layoutResource, int dropdownResource) {
        String[] months = new DateFormatSymbols().getMonths();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, layoutResource, months);
        adapter.setDropDownViewResource(dropdownResource);
        spinner.setAdapter(adapter);
    }

    private void navigateToSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}