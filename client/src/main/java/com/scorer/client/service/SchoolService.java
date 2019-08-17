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

    Map<String, Object> getSchoolByCondition(School school);

    Map<String, Object> getSchoolDetail(PageBean page);

    Map<String, Object> getSchoolById(School school);

    Map<String, Object> getClassCountBySchoolId(long schoolId);

    Map<String, Object> getClassStudentCountBySchoolId(long schoolId);

    Map<String, Object> getSchoolReport(PageBean page);
}
