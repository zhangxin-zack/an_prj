package com.scorer.client.service;

import com.scorer.client.entity.ClassContent;
import com.scorer.client.entity.ClassTime;
import com.scorer.client.entity.Classes;
import com.scorer.client.entity.Timetable;
import com.scorer.client.values.PageBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


public interface ClassesService {

    Map<String, Object> getClassesList(PageBean page);

    Map<String, Object> getClassesById(Integer classesId);

    Map<String, Object> addClasses(Classes classes);

    Map<String, Object> updateClasses(Classes classes);

    Map<String, Object> deleteClasses(List classesIds);

    Map<String, Object> getTimetable(PageBean page);

    Map<String, Object> addTimetable(Timetable timetable);

    Map<String, Object> getClassContent(PageBean page);

    Map<String, Object> addClassContent(ClassContent classContent);

    Map<String, Object> getAccountClassesList(PageBean page);

    Map<String, Object> selectClassesList(Classes classes);

    Map<String, Object> getTimetableClass(PageBean condition);

    List<Long> getListClassStudentParent(Long classId);

    List<Long> getListStudentParent(Long studentId);

    Map<String, Object> getClassTimeList(PageBean page);

    Map<String, Object> addClassTime(ClassTime classTime);

    Map<String, Object> updateClassTime(ClassTime classTime);

    Map<String, Object> deleteClassTime(List classTimeIds);

}

