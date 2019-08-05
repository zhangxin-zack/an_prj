package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.Teacher;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TeacherDao {

    Long getTeacherCount(PageBean page) throws Exception;

    List<Teacher> getTeacherList(PageBean page) throws Exception;

    Teacher getTeacherById(Integer teacherId) throws Exception;

    void addTeacher(Teacher teacher) throws Exception;

    void updateTeacher(Teacher teacher) throws Exception;

    void deleteTeacher(List teacherIds) throws Exception;

    List<Teacher> selectAllHeadTeacher(Map<String, Object> params) throws Exception;
}
