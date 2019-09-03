package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.ClassContent;
import com.scorer.client.entity.Classes;
import com.scorer.client.entity.ClassTime;
import com.scorer.client.entity.Timetable;
import com.scorer.client.values.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesDao {

    Long getClassesCount(PageBean page) throws Exception;

    List<Classes> getClassesList(PageBean page) throws Exception;

    //Classes getClassesById(Long classesId) throws Exception;

    void addClasses(Classes classes) throws Exception;

    Long countTeacherClass(Long accountId, Long classId) throws Exception;

    void updateClasses(Classes classes) throws Exception;

    void deleteClasses(List classesIds) throws Exception;

    Long getTimetableCount(PageBean page) throws Exception;

    List<Timetable> getTimetableList(PageBean page) throws Exception;

    void addTimetable(Timetable timetable) throws Exception;

    Long getClassContentCount(PageBean page) throws Exception;

    List<ClassContent> getClassContentList(PageBean page) throws Exception;

    void addClassContent(ClassContent classContent) throws Exception;

    Long getAccountClassCount(PageBean page) throws Exception;

    List<?> getAccountClassCountList(PageBean page) throws Exception;

    List<Classes> getClassListNoPage(Classes classes) throws Exception;

    Integer countSchoolClass(Long id) throws Exception;

    List<Long> getListClassStudentParent(@Param("classId") Long classId) throws Exception;

    List<Long> getListStudentParent(@Param("studentId") Long studentId) throws Exception;

    
    Long getClassTimeCount(PageBean page) throws Exception;

    List<ClassTime> getClassTimeList(PageBean page) throws Exception;

    void addClassTime(ClassTime classTime) throws Exception;

    void updateClassTime(ClassTime classTime) throws Exception;

    void deleteClassTime(List classTimeIds) throws Exception;
}
