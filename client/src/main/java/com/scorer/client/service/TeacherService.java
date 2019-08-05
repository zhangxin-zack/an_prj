package com.scorer.client.service;

import com.scorer.client.entity.Teacher;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface TeacherService {

    Map<String, Object> getTeacherList(PageBean page);

    Map<String, Object> getTeacherById(Integer teacherId);

    Map<String, Object> addTeacher(Teacher teacher);

    Map<String, Object> updateTeacher(Teacher teacher);

    Map<String, Object> deleteTeacher(List teacherIds);

    Map<String, Object> getHeadTeacherList(Map<String, Object> params);
}
