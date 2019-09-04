package com.scorer.client.entity;


import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

public class Timetable {

    private long id;
    private long classId;
    private String classTime;
    private String timeName;
    private String timetable;
    private MultipartFile timetableFile;
    private java.sql.Timestamp startDate;

    public Timetable() {
    }

    public Timetable(Long classId, Long startDate,String timetable) {
        this.classId=classId;
        this.timetable=timetable;
        this.startDate=new Timestamp(startDate);
    }


    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public java.sql.Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(java.sql.Timestamp startDate) {
        this.startDate = startDate;
    }

    public MultipartFile getTimetableFile() {
        return timetableFile;
    }

    public void setTimetableFile(MultipartFile timetableFile) {
        this.timetableFile = timetableFile;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }
}
