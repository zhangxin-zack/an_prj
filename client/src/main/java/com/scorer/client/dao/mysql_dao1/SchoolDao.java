package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.Classes;
import com.scorer.client.entity.School;
import com.scorer.client.values.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolDao {

    Long getSchoolCount(PageBean page) throws Exception;

    List<School> getSchoolList(PageBean page) throws Exception;

    void addSchool(School school) throws Exception;

    void updateSchool(School school) throws Exception;

    void deleteSchool(List schoolIds) throws Exception;

    School getSchoolById(@Param("schoolId") long schoolId) throws Exception;

    Long getClassCountBySchoolId(@Param("schoolId") long schoolId) throws Exception;

    Long getClassStudentCountBySchoolId(@Param("schoolId") long schoolId) throws Exception;
}