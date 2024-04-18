package org.example.djxt.dto;

public class StarStatsOverview {
    private String year;
    private long totalRecords;
    private long submittedRecords;
    private long completedRecords;
    private double avgScore;
    private Integer maxScore;
    private Integer minScore;
    private long fiveStarCount;
    private long fourStarCount;
    private long threeStarCount;
    private long otherStarCount;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getSubmittedRecords() {
        return submittedRecords;
    }

    public void setSubmittedRecords(long submittedRecords) {
        this.submittedRecords = submittedRecords;
    }

    public long getCompletedRecords() {
        return completedRecords;
    }

    public void setCompletedRecords(long completedRecords) {
        this.completedRecords = completedRecords;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public long getFiveStarCount() {
        return fiveStarCount;
    }

    public void setFiveStarCount(long fiveStarCount) {
        this.fiveStarCount = fiveStarCount;
    }

    public long getFourStarCount() {
        return fourStarCount;
    }

    public void setFourStarCount(long fourStarCount) {
        this.fourStarCount = fourStarCount;
    }

    public long getThreeStarCount() {
        return threeStarCount;
    }

    public void setThreeStarCount(long threeStarCount) {
        this.threeStarCount = threeStarCount;
    }

    public long getOtherStarCount() {
        return otherStarCount;
    }

    public void setOtherStarCount(long otherStarCount) {
        this.otherStarCount = otherStarCount;
    }
}
