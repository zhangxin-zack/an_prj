package com.scorer.client.entity;

public class ArriveCount {
    private int classId;
    private String className="";
    private int studentCount;
    private int arriveCount;
    private int ringCount;
    private Double arriveAvg=0.0;

    public ArriveCount() {
    }

    public ArriveCount(Classes classes) {
        this.classId = classes.getId().intValue();
        this.className = classes.getClassesName();
    }

    public double getArriveAvg() {
        return arriveAvg;
    }

    public void setArriveAvg(Double arriveAvg) {
        this.arriveAvg = arriveAvg == null ? 0d : arriveAvg;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getArriveCount() {
        return arriveCount;
    }

    public void setArriveCount(int arriveCount) {
        this.arriveCount = arriveCount;
    }

    public int getRingCount() {
        return ringCount;
    }

    public void setRingCount(int ringCount) {
        this.ringCount = ringCount;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
