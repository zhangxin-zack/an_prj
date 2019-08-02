package com.scorer.client.service;

import com.scorer.client.entity.Teacher;
import com.scorer.client.values.PageBean;

import java.util.List;
import java.util.Map;


public interface TeacherService {

    public Map<String, Object> getTeacherList(PageBean page);

    public Map<String, Object> getTeacherById(Long teacherId);

    public Map<String, Object> addTeacher(Teacher teacher);

    public Map<String, Object> updateTeacher(Teacher teacher);

    public Map<String, Object> deleteTeacher(List teacherIds);
}
