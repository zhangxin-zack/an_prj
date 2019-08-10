package com.scorer.client.entity;


public class Timetable {

  private long id;
  private long classId;
  private String timetable;
  private java.sql.Timestamp startDate;

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

}
