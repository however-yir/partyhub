package org.example.djxt.dto;

public class StarDeptRank {
    private Integer deptId;
    private String deptName;
    private long recordCount;
    private double avgScore;
    private double completedRate;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public double getCompletedRate() {
        return completedRate;
    }

    public void setCompletedRate(double completedRate) {
        this.completedRate = completedRate;
    }
}
