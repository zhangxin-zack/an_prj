package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.Student;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {

    long getStudentCount(PageBean page) throws Exception;

    List<Student> getStudentList(PageBean page) throws Exception;

    long getStudentParentCount(PageBean page) throws Exception;

    List<Student> getStudentParentList(PageBean page) throws Exception;

    void addStudent(Student Student) throws Exception;

    void updateStudent(Student Student) throws Exception;

    void deleteStudent(List StudentIds) throws Exception;

}