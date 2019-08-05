package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.TeacherDao;
import com.scorer.client.entity.Teacher;
import com.scorer.client.service.TeacherService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl extends BaseSeviceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Map<String, Object> getTeacherList(PageBean page) {
        try{
            page.setTotal(teacherDao.getTeacherCount(page));
            page.setRows(teacherDao.getTeacherList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getTeacherById(Integer teacherId) {
        try{
            Teacher teacher = teacherDao.getTeacherById(teacherId);
            return resultMap(Iconstants.RESULT_CODE_0, "success", teacher);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addTeacher(Teacher teacher) {
        try{
            teacherDao.addTeacher(teacher);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateTeacher(Teacher teacher) {
        try{
            teacherDao.updateTeacher(teacher);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteTeacher(List teacherIds) {
        try{
            teacherDao.deleteTeacher(teacherIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getHeadTeacherList(Map<String, Object> params) {
        try{
            List<Teacher> teachers = teacherDao.selectAllHeadTeacher(params);
            return resultMap(Iconstants.RESULT_CODE_0, "success", teachers);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }
}
