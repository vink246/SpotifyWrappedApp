package Utility;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

/**
 * Utility class to manage spinner listeners and references for the date selection spinners.
 */
public class WrapInfoButtons {
    // Spinners for start and end date selection
    private Spinner startMonthSpinner;
    private Spinner startDateSpinner;
    private Spinner startYearSpinner;
    private Spinner endMonthSpinner;
    private Spinner endDateSpinner;
    private Spinner endYearSpinner;

    /**
     * Constructor to initialize WrapInfoButtons with spinner references and setup spinner listeners.
     *
     * @param startMonthSpinner Spinner for selecting the start month
     * @param startDateSpinner  Spinner for selecting the start date
     * @param startYearSpinner  Spinner for selecting the start year
     * @param endMonthSpinner   Spinner for selecting the end month
     * @param endDateSpinner    Spinner for selecting the end date
     * @param endYearSpinner    Spinner for selecting the end year
     */
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

    // Method to setup listeners for all spinners
    public void setupSpinnerListeners() {
        setupSpinnerListener(startMonthSpinner);
        setupSpinnerListener(startDateSpinner);
        setupSpinnerListener(startYearSpinner);
        setupSpinnerListener(endMonthSpinner);
        setupSpinnerListener(endDateSpinner);
        setupSpinnerListener(endYearSpinner);
    }

    // Method to setup listener for a spinner
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

    /**
     * Method to update spinner references and re-setup listeners.
     *
     * @param startMonthSpinner Spinner for selecting the start month
     * @param startDateSpinner  Spinner for selecting the start date
     * @param startYearSpinner  Spinner for selecting the start year
     * @param endMonthSpinner   Spinner for selecting the end month
     * @param endDateSpinner    Spinner for selecting the end date
     * @param endYearSpinner    Spinner for selecting the end year
     */
    public void updateReferences(Spinner startMonthSpinner, Spinner startDateSpinner, Spinner startYearSpinner,
                                 Spinner endMonthSpinner, Spinner endDateSpinner, Spinner endYearSpinner) {
        // Update spinner references
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
