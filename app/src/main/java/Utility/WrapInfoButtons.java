package Utility;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class WrapInfoButtons {
    private Spinner startMonthSpinner;
    private Spinner startDateSpinner;
    private Spinner startYearSpinner;
    private Spinner endMonthSpinner;
    private Spinner endDateSpinner;
    private Spinner endYearSpinner;

    public WrapInfoButtons(Spinner startMonthSpinner, Spinner startDateSpinner, Spinner startYearSpinner,
                           Spinner endMonthSpinner, Spinner endDateSpinner, Spinner endYearSpinner) {
        this.startMonthSpinner = startMonthSpinner;
        this.startDateSpinner = startDateSpinner;
        this.startYearSpinner = startYearSpinner;
        this.endMonthSpinner = endMonthSpinner;
        this.endDateSpinner = endDateSpinner;
        this.endYearSpinner = endYearSpinner;
        setupSpinnerListeners();
    }

    public void setupSpinnerListeners() {
        setupSpinnerListener(startMonthSpinner);
        setupSpinnerListener(startDateSpinner);
        setupSpinnerListener(startYearSpinner);
        setupSpinnerListener(endMonthSpinner);
        setupSpinnerListener(endDateSpinner);
        setupSpinnerListener(endYearSpinner);
    }

    private void setupSpinnerListener(final Spinner spinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle spinner item selection here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle no item selected here
            }
        });
    }

    public void updateReferences(Spinner startMonthSpinner, Spinner startDateSpinner, Spinner startYearSpinner,
                                 Spinner endMonthSpinner, Spinner endDateSpinner, Spinner endYearSpinner) {
        this.startMonthSpinner = startMonthSpinner;
        this.startDateSpinner = startDateSpinner;
        this.startYearSpinner = startYearSpinner;
        this.endMonthSpinner = endMonthSpinner;
        this.endDateSpinner = endDateSpinner;
        this.endYearSpinner = endYearSpinner;

        // Re-setup the listeners for the new references
        setupSpinnerListeners();
    }
}
