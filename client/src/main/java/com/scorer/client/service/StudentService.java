package com.scorer.client.service;

import com.scorer.client.entity.Student;
import com.scorer.client.values.PageBean;

import java.util.List;
import java.util.Map;


public interface StudentService {

    Map<String, Object> getStudentList(PageBean page);

    Map<String, Object> getStudentFamily(PageBean page);

    Map<String, Object> getRingAlarmDetail(PageBean page);

    Map<String, Object> addStudent(Student student);

    Map<String, Object> updateStudent(Student student);

    Map<String, Object> deleteStudent(List studentIds);

    Map<String, Object> getStudentListBK(PageBean condition);
}
