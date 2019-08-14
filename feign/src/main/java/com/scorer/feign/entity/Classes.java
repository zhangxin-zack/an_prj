package com.scorer.feign.entity;


public class Classes {

    private Long id;
    private String classesName;
    private Long schoolId;
    private String schoolName;
    private Long teacherId;
    private String teacherName;
    private Integer grade;

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
}
