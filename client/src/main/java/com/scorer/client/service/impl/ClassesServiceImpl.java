package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.ClassesDao;
import com.scorer.client.entity.ClassContent;
import com.scorer.client.entity.Classes;
import com.scorer.client.entity.Timetable;
import com.scorer.client.service.ClassesService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClassesServiceImpl extends BaseSeviceImpl implements ClassesService {

    @Autowired
    private ClassesDao classesDao;

    @Override
    public Map<String, Object> getClassesList(PageBean page) {
        try{
            page.setTotal(classesDao.getClassesCount(page));
            page.setRows(classesDao.getClassesList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getClassesById(Integer classesId) {
        /*try{
            Classes Classes = classesDao.getClassesById(classesId);
            return resultMap(Iconstants.RESULT_CODE_0, "success", Classes);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }*/
        return null;
    }


    @Override
    public Map<String, Object> addClassContent(ClassContent classContent) {
        try{
            classesDao.addClassContent(classContent);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getClassContent(PageBean page) {
        try{
            page.setTotal(classesDao.getClassContentCount(page));
            page.setRows(classesDao.getClassContentList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getTimetable(PageBean page) {
        try{
            page.setTotal(classesDao.getTimetableCount(page));
            page.setRows(classesDao.getTimetableList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addTimetable(Timetable timetable) {
        try{
            classesDao.addTimetable(timetable);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> addClasses(Classes classes) {
        try{
            classesDao.addClasses(classes);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateClasses(Classes classes) {
        try{
            classesDao.updateClasses(classes);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteClasses(List classesIds) {
        try{
            classesDao.deleteClasses(classesIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }
}
