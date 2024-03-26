package Utility;

import android.widget.Spinner;

public class DateRangeSelection {
    private WrapInfo wrapInfo;
    private WrapInfoButtons wrapInfoButtons;

    public DateRangeSelection(WrapInfo wrapInfo, WrapInfoButtons wrapInfoButtons) {
        this.wrapInfo = wrapInfo;
        this.wrapInfoButtons = wrapInfoButtons;
    }

    public void updateDateRangeSelection(Spinner startMonthSpinner, Spinner startDateSpinner, Spinner startYearSpinner,
                                         Spinner endMonthSpinner, Spinner endDateSpinner, Spinner endYearSpinner) {
        // Update references in WrapInfoButtons
        wrapInfoButtons.updateReferences(startMonthSpinner, startDateSpinner, startYearSpinner,
                endMonthSpinner, endDateSpinner, endYearSpinner);
    }
}
