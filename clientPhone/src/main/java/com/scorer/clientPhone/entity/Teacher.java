package com.scorer.clientPhone.entity;

import java.util.ArrayList;
import java.util.List;

public class Teacher {

    private Long teacherId;
    private String teacherName;
    private String phone;
    private String accountUsername;
    private Integer isHead;
    private Long schoolId;

    private List<Long> classIds;
    private List<com.scorer.clientPhone.entity.Classes> manageClasses;

    private List<com.scorer.clientPhone.entity.Classes> operateClasses=new ArrayList<>();   //班主任
    private List<com.scorer.clientPhone.entity.Classes> teachClasses=new ArrayList<>();   //非班主任

    public void initFromDB() {
        if (manageClasses != null)
            for (com.scorer.clientPhone.entity.Classes manageClasses : manageClasses) {
                if(manageClasses.getIs_head()!=null&&manageClasses.getIs_head()==1){
                    operateClasses.add(manageClasses);
                }else{
                    teachClasses.add(manageClasses);
                }
            }
    }

    public void initFromUser() {
        manageClasses = new ArrayList<>();
        if(operateClasses!=null){
            manageClasses.addAll(operateClasses);
        }

        if(teachClasses!=null){
            manageClasses.addAll(teachClasses);
        }

    }


    public List<com.scorer.clientPhone.entity.Classes> getOperateClasses() {
        return operateClasses;
    }

    public void setOperateClasses(List<com.scorer.clientPhone.entity.Classes> operateClasses) {
        this.operateClasses = operateClasses;
    }

    public List<com.scorer.clientPhone.entity.Classes> getTeachClasses() {
        return teachClasses;
    }

    public void setTeachClasses(List<com.scorer.clientPhone.entity.Classes> teachClasses) {
        this.teachClasses = teachClasses;
    }

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

    public List<com.scorer.clientPhone.entity.Classes> getManageClasses() {
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
