package com.scorer.feign.entity;

import java.util.List;

public class Classes {

    private Long id;
    private String classesName;
    private Long schoolId;
    private String classesTitle;
    private String schoolName;
    private Long teacherId;
    private String teacherName;
    private Integer grade;
    private Long classTimeId;
    private List<Teacher> teacherList;

    private Integer is_head;  //是否班主任（用户查询时）处理(1,是班主任，-1,不是)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public String getClassesTitle() {
        return classesTitle;
    }

    public void setClassesTitle(String classesTitle) {
        this.classesTitle = classesTitle;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getIs_head() {
        return is_head;
    }

    public void setIs_head(Integer is_head) {
        this.is_head = is_head;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public Long getClassTimeId() {
        return classTimeId;
    }

    public void setClassTimeId(Long classTimeId) {
        this.classTimeId = classTimeId;
    }
}
