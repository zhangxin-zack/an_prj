package com.scorer.client.entity;

import java.util.List;

public class Teacher {

    private Long teacherId;
    private String teacherName;
    private String phone;

    private List<Classes> manageClasses;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Classes> getManageClasses() {
        return manageClasses;
    }

    public void setManageClasses(List<Classes> manageClasses) {
        this.manageClasses = manageClasses;
    }
}
