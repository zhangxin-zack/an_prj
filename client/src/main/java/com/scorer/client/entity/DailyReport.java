package com.scorer.client.entity;

public class DailyReport {

    private Long studentId;
    private String studentName;
    private String registerNo;
    private String ringNo;
    private Integer attendance; //-1:未到校  1：到校
    private String reportDate;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getRingNo() {
        return ringNo;
    }

    public void setRingNo(String ringNo) {
        this.ringNo = ringNo;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
}
