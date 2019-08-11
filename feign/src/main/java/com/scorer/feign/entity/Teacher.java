package com.scorer.feign.entity;

public class Teacher {

    private Long teacherId;

    private Long schoolId;

    private String schoolName;

    private String teacherName;

    private Integer isHead;

    private String manageClassIds;
    private String manageClassName;
    private String phone;
    private Long accountId;
    private String accountName;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getIsHead() {
        return isHead;
    }

    public String getManageClassIds() {
        return manageClassIds;
    }

    public void setManageClassIds(String manageClassIds) {
        this.manageClassIds = manageClassIds;
    }

    public String getManageClassName() {
        return manageClassName;
    }

    public void setManageClassName(String manageClassName) {
        this.manageClassName = manageClassName;
    }

    public void setIsHead(Integer isHead) {
        this.isHead = isHead;
    }
}
