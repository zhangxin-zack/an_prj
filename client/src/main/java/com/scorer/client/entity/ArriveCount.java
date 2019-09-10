package com.scorer.client.entity;

public class ArriveCount {
    private int classId;
    private int studentCount;
    private int arriveCount;
    private int ringCount;
    private double arriveAvg;

    public double getArriveAvg() {
        return arriveAvg;
    }

    public void setArriveAvg(double arriveAvg) {
        this.arriveAvg = arriveAvg;
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
}
