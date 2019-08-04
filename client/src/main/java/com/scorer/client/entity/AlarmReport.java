package com.scorer.client.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AlarmReport {
    private Long id;
    private Timestamp alarmTime;
    private Integer alarmType;
    private String alarmData;
    private Integer studentId;
    private String studentName;
    private String classId;
    private String className;
    private BigDecimal longitude;
    private BigDecimal latitude;

    private Timestamp alarmBeginTime;
    private Timestamp alarmEndTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Timestamp alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(String alarmData) {
        this.alarmData = alarmData;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    public Timestamp getAlarmBeginTime() {
        return alarmBeginTime;
    }
    public void setAlarmBeginTime(Timestamp alarmBeginTime) {
        this.alarmBeginTime = alarmBeginTime;
    }
    public Timestamp getAlarmEndTime() {
        return alarmEndTime;
    }

    public void setAlarmEndTime(Timestamp alarmEndTime) {
        this.alarmEndTime = alarmEndTime;
    }
}
