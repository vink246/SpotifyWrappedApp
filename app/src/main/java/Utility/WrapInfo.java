package Utility;

public class WrapInfo {
    private String startMonth;
    private int startDate;
    private int startYear;
    private String endMonth;
    private int endDate;
    private int endYear;

    public WrapInfo(String startMonth, int startDate, int startYear, String endMonth, int endDate, int endYear) {
        this.startMonth = startMonth;
        this.startDate = startDate;
        this.startYear = startYear;
        this.endMonth = endMonth;
        this.endDate = endDate;
        this.endYear = endYear;
    }

    // Getter methods
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

    // Setter methods (if needed)
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
