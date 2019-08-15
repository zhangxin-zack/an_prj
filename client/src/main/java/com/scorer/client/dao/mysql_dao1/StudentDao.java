package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.Student;
import com.scorer.client.values.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {

    long getStudentCount(PageBean page) throws Exception;

    List<Student> getStudentList(PageBean page) throws Exception;

    long getStudentBabyCount(PageBean page) throws Exception;

    List<Student> getStudentBabyList(PageBean page) throws Exception;

    long getStudentCountForApp(PageBean page) throws Exception;

    List<Student> getStudentListForApp(PageBean page) throws Exception;

    long getStudentFamilyCount(PageBean page) throws Exception;

    List<Student> getStudentFamilyList(PageBean page) throws Exception;

    void addStudent(Student Student) throws Exception;

    void updateStudent(Student Student) throws Exception;

    void deleteStudent(List StudentIds) throws Exception;

    void addAccountStudent(Student student);

    Student getStudentById(@Param("id") Long id);

    long getStudentCountBK(PageBean page);

    List<Student> getStudentListBK(PageBean page);
}
