package com.scorer.client.service;

import com.scorer.client.entity.School;
import com.scorer.client.values.PageBean;

import java.util.List;
import java.util.Map;

public interface SchoolService {

    Map<String, Object> getSchoolList(PageBean page);

    Map<String, Object> addSchool(School school);

    Map<String, Object> updateSchool(School school);

    Map<String, Object> deleteSchool(List schoolIds);

    Map<String, Object> getSchoolById(long schoolId);

    Map<String, Object> getClassCountBySchoolId(long schoolId);

    Map<String, Object> getClassStudentCountBySchoolId(long schoolId);
}
