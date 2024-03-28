package com.example.spotifywrapped;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class WrapActivity extends AppCompatActivity {

    // Declaration of spinners and a button for settings
    private Spinner startMonthSpinner;
    private Spinner startDateSpinner;
    private Spinner startYearSpinner;
    private Spinner endMonthSpinner;
    private Spinner endDateSpinner;
    private Spinner endYearSpinner;
    private Button settingsButton;
    private Button duoWrapButton;

    // Constants for SharedPreferences key names
    private static final String PREFS_NAME = "WrapActivityPrefs";
    private static final String START_MONTH_POSITION = "startMonthPosition";
    private static final String START_DATE_POSITION = "startDatePosition";
    private static final String START_YEAR_POSITION = "startYearPosition";
    private static final String END_MONTH_POSITION = "endMonthPosition";
    private static final String END_DATE_POSITION = "endDatePosition";
    private static final String END_YEAR_POSITION = "endYearPosition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wrappeddark); // Always use the dark layout
        initializeSpinners();

        settingsButton = findViewById(R.id.settings_button);
        duoWrapButton = findViewById(R.id.duo_wrap_button);

        // Restore previously selected spinner values
        restoreSpinnerSelections();

        // Set up a click listener for the settings button to navigate to the SettingsActivity
        settingsButton.setOnClickListener(v -> navigateToSettingsActivity());

        // Set up a click listener for the duoWrapButton to navigate to DuoWrapActivity
        duoWrapButton.setOnClickListener(v -> navigateToDuoWrapActivity());
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Save the current spinner selections when the activity is paused
        saveSpinnerSelections();
    }

    // Save spinner selections to SharedPreferences
    private void saveSpinnerSelections() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(START_MONTH_POSITION, startMonthSpinner.getSelectedItemPosition());
        editor.putInt(START_DATE_POSITION, startDateSpinner.getSelectedItemPosition());
        editor.putInt(START_YEAR_POSITION, startYearSpinner.getSelectedItemPosition());
        editor.putInt(END_MONTH_POSITION, endMonthSpinner.getSelectedItemPosition());
        editor.putInt(END_DATE_POSITION, endDateSpinner.getSelectedItemPosition());
        editor.putInt(END_YEAR_POSITION, endYearSpinner.getSelectedItemPosition());
        editor.apply();
    }

    // Restore spinner selections from SharedPreferences
    private void restoreSpinnerSelections() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        startMonthSpinner.setSelection(prefs.getInt(START_MONTH_POSITION, 0));
        startDateSpinner.setSelection(prefs.getInt(START_DATE_POSITION, 0));
        startYearSpinner.setSelection(prefs.getInt(START_YEAR_POSITION, 0));
        endMonthSpinner.setSelection(prefs.getInt(END_MONTH_POSITION, 0));
        endDateSpinner.setSelection(prefs.getInt(END_DATE_POSITION, 0));
        endYearSpinner.setSelection(prefs.getInt(END_YEAR_POSITION, 0));
    }

    private void initializeSpinners() {
        // Initialize spinners
        startMonthSpinner = findViewById(R.id.SpinnerStartMonth);
        startDateSpinner = findViewById(R.id.SpinnerStartDay);
        startYearSpinner = findViewById(R.id.SpinnerStartYear);
        endMonthSpinner = findViewById(R.id.SpinnerEndMonth);
        endDateSpinner = findViewById(R.id.SpinnerEndDay);
        endYearSpinner = findViewById(R.id.SpinnerEndYear);

        // Populate spinners
        setupSpinners(startDateSpinner, 1, 31, R.layout.dark_spinner_item, R.layout.dark_spinner_item);
        setupSpinners(startYearSpinner, 2000, 2099, R.layout.dark_spinner_item, R.layout.dark_spinner_item);
        setupSpinners(endDateSpinner, 1, 31, R.layout.dark_spinner_item, R.layout.dark_spinner_item);
        setupSpinners(endYearSpinner, 2000, 2099, R.layout.dark_spinner_item, R.layout.dark_spinner_item);
        setupMonthSpinner(startMonthSpinner, R.layout.dark_spinner_item, R.layout.dark_spinner_item);
        setupMonthSpinner(endMonthSpinner, R.layout.dark_spinner_item, R.layout.dark_spinner_item);
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

    // Navigate to SettingsActivity
    private void navigateToSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    // Navigate to DuoWrapActivity
    private void navigateToDuoWrapActivity() {
        Intent intent = new Intent(this, DuoWrapActivity.class);
        startActivity(intent);
    }
}