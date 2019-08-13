package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.AccountDao;
import com.scorer.client.dao.mysql_dao1.TeacherDao;
import com.scorer.client.entity.Account;
import com.scorer.client.entity.Teacher;
import com.scorer.client.service.TeacherService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl extends BaseSeviceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private AccountDao accountDao;

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
    @Transactional
    public Map<String, Object> addTeacher(Teacher teacher) {
        try{
            //查询account表是否用户已存在, 存在的话, 修改用户, 不存在则新增用户, 并且绑定用户与班级关系表
            Account account = new Account();
            account.setPhone(teacher.getPhone());
            List<Long> classIds = teacher.getClassIds();
            Account teacherAccount = accountDao.accountLogin(account);
            if(teacherAccount == null){
                account.setUsername(teacher.getAccountUsername());
                account.setNickname(teacher.getTeacherName());
                //添加老师用户
                accountDao.addAccount(account);
                //绑定老师与班级的关系
                Long accountId = account.getId();
                for(Long classId: classIds){
                    teacherDao.addAccountClass(accountId, classId);
                }
            }else{
                List<Long> teacherIds = new ArrayList<>();
                teacherIds.add(teacherAccount.getId());
                //删除关系表
                teacherDao.deleteTeacher(teacherIds);
                //重新绑定关系
                for(Long classId: classIds){
                    teacherDao.addAccountClass(teacherAccount.getId(), classId);
                }
            }
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
    @Transactional
    public Map<String, Object> deleteTeacher(List teacherIds) {
        try{
            //首先删除老师的班主任身份
            teacherDao.deleteHeadTeacher(teacherIds);
            //删除老师与班级中间表关系
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
