package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.ReportDao;
import com.scorer.client.dao.mysql_dao1.SchoolDao;
import com.scorer.client.entity.School;
import com.scorer.client.service.SchoolService;
import com.scorer.client.service.SchoolService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SchoolServiceImpl extends BaseSeviceImpl implements SchoolService {

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public Map<String, Object> getSchoolList(PageBean page) {
        try{
            page.setTotal(schoolDao.getSchoolCount(page));
            page.setRows(schoolDao.getSchoolList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addSchool(School School) {
        try{
            schoolDao.addSchool(School);
            //发送邮件服务 todo


            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateSchool(School School) {
        try{
            schoolDao.updateSchool(School);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteSchool(List SchoolIds) {
        try{
            schoolDao.deleteSchool(SchoolIds);
            //清空其他表外键 todo


            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getSchoolById(long schoolId) {
        try{
            School school = schoolDao.getSchoolById(schoolId);
            return resultMap(Iconstants.RESULT_CODE_0, "success", school);
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getClassCountBySchoolId(long schoolId) {
        try{
            long classCount = schoolDao.getClassCountBySchoolId(schoolId);
            return resultMap(Iconstants.RESULT_CODE_0, "success", classCount);
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getClassStudentCountBySchoolId(long schoolId) {
        try{
            long classStudentCount = schoolDao.getClassCountBySchoolId(schoolId);
            return resultMap(Iconstants.RESULT_CODE_0, "success", classStudentCount);
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }


}
