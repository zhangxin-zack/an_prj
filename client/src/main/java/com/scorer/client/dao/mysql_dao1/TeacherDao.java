package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.Teacher;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDao {

    public long getTeacherCount(PageBean page) throws Exception;

    public List<Teacher> getTeacherList(PageBean page) throws Exception;

    public Teacher getTeacherById(Integer teacherId) throws Exception;

    public void addTeacher(Teacher teacher) throws Exception;

    public void updateTeacher(Teacher teacher) throws Exception;

    public void deleteTeacher(List teacherIds) throws Exception;

}
