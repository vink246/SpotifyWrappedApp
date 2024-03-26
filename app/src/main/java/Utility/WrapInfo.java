package Utility;

/**
 * Utility class to encapsulate information about a date range.
 * Provides methods to access and modify start and end dates.
 */
public class WrapInfo {
    // Properties to store start and end date components
    private String startMonth;
    private int startDate;
    private int startYear;
    private String endMonth;
    private int endDate;
    private int endYear;

    /**
     * Constructor to initialize WrapInfo with start and end date components.
     *
     * @param startMonth The month of the start date
     * @param startDate  The day of the start date
     * @param startYear  The year of the start date
     * @param endMonth   The month of the end date
     * @param endDate    The day of the end date
     * @param endYear    The year of the end date
     */
    public WrapInfo(String startMonth, int startDate, int startYear, String endMonth, int endDate, int endYear) {
        this.startMonth = startMonth;
        this.startDate = startDate;
        this.startYear = startYear;
        this.endMonth = endMonth;
        this.endDate = endDate;
        this.endYear = endYear;
    }

    // Getter methods for accessing date components

    public String getStartMonth() {
        return startMonth;
    }

    public int getStartDate() {
        return startDate;
    }

    public int getStartYear() {
        return startYear;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public int getEndDate() {
        return endDate;
    }

    public int getEndYear() {
        return endYear;
    }

    // Setter methods (if needed) for modifying date components

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }
}
