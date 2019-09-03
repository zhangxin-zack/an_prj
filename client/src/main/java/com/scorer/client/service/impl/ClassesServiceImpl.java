package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.ClassesDao;
import com.scorer.client.dao.mysql_dao1.StudentDao;
import com.scorer.client.dao.mysql_dao1.TeacherDao;
import com.scorer.client.entity.ClassContent;
import com.scorer.client.entity.Classes;
import com.scorer.client.entity.Student;
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
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Map<String, Object> getClassesList(PageBean page) {
        try {
            page.setTotal(classesDao.getClassesCount(page));
            page.setRows(classesDao.getClassesList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
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
        try {
            classesDao.addClassContent(classContent);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getAccountClassesList(PageBean page) {
        try {
            page.setTotal(classesDao.getAccountClassCount(page));
            page.setRows(classesDao.getAccountClassCountList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> selectClassesList(Classes classes) {
        try {
            List<Classes> list = classesDao.getClassListNoPage(classes);
            return resultMap(Iconstants.RESULT_CODE_0, "success", list);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getClassContent(PageBean page) {
        try {
            page.setTotal(classesDao.getClassContentCount(page));
            page.setRows(classesDao.getClassContentList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getTimetable(PageBean page) {
        try {
            Student student = studentDao.getStudentById(Long.valueOf(page.getSearchs().get("studentId").toString()));
            if(student != null) {
                page.getSearchs().put("classId", student.getClassId());
                page.setTotal(classesDao.getTimetableCount(page));
                page.setRows(classesDao.getTimetableList(page));
                return resultMap(Iconstants.RESULT_CODE_0, "success", page);
            }else{
                return resultMap(Iconstants.RESULT_CODE_1, "该学生所属班级暂无课表", page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }


    @Override
    public Map<String, Object> getTimetableClass(PageBean page) {
        try {
            page.setTotal(classesDao.getTimetableCount(page));
            page.setRows(classesDao.getTimetableList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public List<Long> getListClassStudentParent(Long classId) {
        try {
            return classesDao.getListClassStudentParent(classId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Long> getListStudentParent(Long studentId) {
        try {
            return classesDao.getListStudentParent(studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Map<String, Object> addTimetable(Timetable timetable) {
        try {
            classesDao.addTimetable(timetable);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> addClasses(Classes classes) {
        try {
            classesDao.addClasses(classes);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateClasses(Classes classes) {
        try {
            //修改班级相关信息
            classesDao.updateClasses(classes);
            Long headTeacherId = classes.getTeacherId();
            if(headTeacherId != null){
                //判断老师是否和班级绑定， 如果没有绑定则绑定
                long count = classesDao.countTeacherClass(headTeacherId, classes.getId());
                if(count == 0){
                    teacherDao.addAccountClass(headTeacherId, classes.getId(), classes.getSchoolId());
                }
            }
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteClasses(List classesIds) {
        try {
            classesDao.deleteClasses(classesIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }
}
