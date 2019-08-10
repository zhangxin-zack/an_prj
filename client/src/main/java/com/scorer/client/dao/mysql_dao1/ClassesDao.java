package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.ClassContent;
import com.scorer.client.entity.Classes;
import com.scorer.client.entity.Timetable;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesDao {

    Long getClassesCount(PageBean page) throws Exception;

    List<Classes> getClassesList(PageBean page) throws Exception;

    //Classes getClassesById(Integer classesId) throws Exception;

    void addClasses(Classes classes) throws Exception;

    void updateClasses(Classes classes) throws Exception;

    void deleteClasses(List classesIds) throws Exception;

    Long getTimetableCount(PageBean page) throws Exception;

    List<Timetable> getTimetableList(PageBean page) throws Exception;

    void addTimetable(Timetable timetable) throws Exception;

    Long getClassContentCount(PageBean page) throws Exception;

    List<ClassContent> getClassContentList(PageBean page) throws Exception;

    void addClassContent(ClassContent classContent) throws Exception;
}
