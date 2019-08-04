package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.ReportDao;
import com.scorer.client.dao.mysql_dao1.StudentDao;
import com.scorer.client.entity.Student;
import com.scorer.client.service.StudentService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl extends BaseSeviceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ReportDao reportDao;

    @Override
    public Map<String, Object> getStudentList(PageBean page) {
        try{
            page.setTotal(studentDao.getStudentCount(page));
            page.setRows(studentDao.getStudentList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getStudentParentDetail(PageBean page) {
        try{
            page.setTotal(studentDao.getStudentCount(page));
            page.setRows(studentDao.getStudentList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getRingAlarmDetail(PageBean page) {
        try{
            page.setTotal(reportDao.getAlarmReportCount(page));
            page.setRows(reportDao.getAlarmReportList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addStudent(Student Student) {
        try{
            studentDao.addStudent(Student);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateStudent(Student Student) {
        try{
            studentDao.updateStudent(Student);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteStudent(List StudentIds) {
        try{
            studentDao.deleteStudent(StudentIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }
}
