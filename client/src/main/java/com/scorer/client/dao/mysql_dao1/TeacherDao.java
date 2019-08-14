package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.Teacher;
import com.scorer.client.values.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TeacherDao {

    Long getTeacherCount(PageBean page) throws Exception;

    List<Teacher> getTeacherList(PageBean page) throws Exception;

    Teacher getTeacherById(Integer teacherId) throws Exception;

    void addTeacher(Teacher teacher);

    void addAccountClass(Long accountId, Long classId, Long schoolId) throws Exception;


    void deleteTeacher(List teacherIds) throws Exception;



    void deleteHeadTeacher(List teacherIds) throws Exception;

    List<Teacher> selectAllHeadTeacher(Map<String, Object> params) throws Exception;

    void addSchoolTeacher(@Param("accountId") Long accountId,@Param("schoolId") Long schoolId);

    void updateTeacher(Teacher teacher);

    void deleteTeacherFromClass(@Param("teacherIds") List teacherIds,@Param("schoolId") Long schoolId);
}
