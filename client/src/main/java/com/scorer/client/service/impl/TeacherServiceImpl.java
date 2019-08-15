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
        try {
            page.setTotal(teacherDao.getTeacherCount(page));
            List<Teacher> teachers = teacherDao.getTeacherList(page);
            for(Teacher tec:teachers){
                tec.initFromDB();
            }
            page.setRows(teachers);
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getTeacherById(Integer teacherId) {
        try {
            Teacher teacher = teacherDao.getTeacherById(teacherId);
            return resultMap(Iconstants.RESULT_CODE_0, "success", teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    @Transactional
    public Map<String, Object> addTeacher(Teacher teacher) {
        try {
            //查询account表是否用户已存在, 存在的话, 修改用户, 不存在则新增用户, 并且绑定用户与班级关系表
            Account account = new Account();
            account.setPhone(teacher.getPhone());
            Account teacherAccount = accountDao.accountLogin(account);
            if (teacherAccount == null) {
                account.setUsername(teacher.getAccountUsername());
                account.setNickname(teacher.getTeacherName());
                //添加老师用户
                accountDao.addAccount(account);
                accountDao.addAccountTitle(account.getId(), 1L);
                accountDao.addAccountTitle(account.getId(), 2L);
            }else{
                account.setId(teacherAccount.getId());
            }
            //绑定老师与学校关系
            teacherDao.addSchoolTeacher(account.getId(), teacher.getSchoolId());
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Map<String, Object> updateTeacher(Teacher teacher) {
        try {
            teacher.initFromUser();
            Account account = new Account();
            account.setPhone(teacher.getPhone());
            account.setUsername(teacher.getAccountUsername());
            account.setNickname(teacher.getTeacherName());
            account.setId(teacher.getTeacherId());
            if (account.getUsername() != null || account.getNickname() != null) {
                accountDao.updateAccount(account);
            }
            //重新绑定老师与学校关系
            teacherDao.addSchoolTeacher(account.getId(), teacher.getSchoolId());
            teacherDao.updateTeacher(teacher);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Map<String, Object> deleteTeacher(List teacherIds, Long schoolId) {
        try {
            //首先删除老师的班主任身份
            teacherDao.deleteTeacherFromClass(teacherIds,schoolId);
            //删除老师相关title
            accountDao.deleteAccountTitle(teacherIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getHeadTeacherList(Map<String, Object> params) {
        try {
            List<Teacher> teachers = teacherDao.selectAllHeadTeacher(params);
            return resultMap(Iconstants.RESULT_CODE_0, "success", teachers);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }
}
