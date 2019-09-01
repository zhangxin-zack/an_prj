package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.School;
import com.scorer.client.entity.SchoolDetail;
import com.scorer.client.entity.SchoolMenu;
import com.scorer.client.values.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SchoolDao {

    Long getSchoolCount(PageBean page) throws Exception;

    List<School> getSchoolList(PageBean page) throws Exception;

    void addSchool(School school) throws Exception;

    void updateSchool(School school) throws Exception;

    void deleteSchool(List schoolIds) throws Exception;

    List<School> getSchoolByCondition(School school) throws Exception;

    Integer getClassCountBySchoolId(@Param("schoolId") long schoolId) throws Exception;

    Integer getClassStudentCountBySchoolId(@Param("schoolId") long schoolId) throws Exception;

    Long getSchoolDetailCount(PageBean page) throws Exception;

    List<SchoolDetail> getSchoolDetailList(PageBean page) throws Exception;

    Long getSchoolReportCount(PageBean page) throws Exception;

    List<Map> getSchoolReportList(PageBean page) throws Exception;

    void deleteSchoolFK(List schoolIds) throws Exception;

    List<SchoolMenu> getAllSchoolMenuList() throws Exception;

    Long getAllSchoolMenuListForPageCount(PageBean page) throws Exception;

    List<SchoolMenu> getAllSchoolMenuListForPage(PageBean page) throws Exception;

    void addSchoolMenu(SchoolMenu schoolMenu) throws Exception;

    void updateSchoolMenu(SchoolMenu schoolMenu) throws Exception;

    void deleteSchoolMenus(List menuIds) throws Exception;

    long getAgentSchoolCount(PageBean page) throws Exception;

    List<Map> getAgentSchoolList(PageBean page) throws Exception;
}
