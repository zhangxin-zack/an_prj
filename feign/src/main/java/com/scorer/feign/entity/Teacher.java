package com.scorer.feign.entity;

import java.util.List;

public class Teacher {

    private Long teacherId;
    private String teacherName;
    private String phone;
    private String accountUsername;
    private Integer isHead;
    private Long schoolId;

    private List<Long> classIds;
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

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public Integer getIsHead() {
        return isHead;
    }

    public void setIsHead(Integer isHead) {
        this.isHead = isHead;
    }

    public List<Long> getClassIds() {
        return classIds;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public void setClassIds(List<Long> classIds) {
        this.classIds = classIds;
    }
}
