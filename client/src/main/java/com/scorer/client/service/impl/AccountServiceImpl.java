package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.AccountDao;
import com.scorer.client.dao.mysql_dao1.StudentDao;
import com.scorer.client.entity.Account;
import com.scorer.client.entity.AppMenu;
import com.scorer.client.entity.Student;
import com.scorer.client.service.AccountService;
import com.scorer.client.tools.MessageApi;
import com.scorer.client.tools.ObjectUtils;
import com.scorer.client.tools.TokenTools;
import com.scorer.client.values.PageBean;
import com.scorer.client.values.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl extends BaseSeviceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private StudentDao studentDao;

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @Override
    public Map<String, Object> getAccountList(PageBean page) {
        try {
            page.setTotal(accountDao.getAccountCount(page));
            page.setRows(accountDao.getAccountList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getValidateCode(String phone) {
        try {
            ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
            Integer phoneCode = operations.get("User_Login_Phone_Code:" + phone);
            if (phoneCode == null || phoneCode == 0) {
//                phoneCode = new Random().nextInt(900000) + 100000;
                phoneCode = 123456;
            }
            operations.set("User_Login_Phone_Code:" + phone, phoneCode, 60 * 2, TimeUnit.SECONDS);
            String text = "【手环】您的验证码是" + phoneCode;
            String message = MessageApi.sendSMS(text, phone);
            System.out.println(message);
            return resultMap(Iconstants.RESULT_CODE_0, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    /**
     * 注册
     *
     * @param account
     * @return
     */
    @Override
    public Map<String, Object> register(Account account) {
        try {
            ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
            Integer phoneCode = operations.get("User_Login_Phone_Code:" + account.getPhone());
            //判断验证码是否正确
            if (!(ObjectUtils.noneEmpty(phoneCode, account.getValidateCode()) && String.valueOf(phoneCode).equals(account.getValidateCode()))) {
                return resultMap(Iconstants.RESULT_CODE_1, ResultMap.Result.get(1003), null);
            }
            Map<String, Object> data = new HashMap<>();
            accountDao.addAccount(account);
            Long accountId = account.getId();
            //注册默认为家长
            accountDao.addAccountTitle(accountId, 1L);
            //获取token
            data.put("account", account);
            data.put("token", TokenTools.generateTokenAPP(accountId));
            //(app端菜单被写死了  无动态权限  暂无处理)
            //获取家长身份相关菜单
            //List<AppMenu> menuList = accountDao.getAppMenuList(accountId);
            //data.put("menuList", getTreeData(menuList));
            return resultMap(Iconstants.RESULT_CODE_0, "success", data);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    /**
     * 用户登录
     *
     * @param account
     * @return
     */
    @Override
    public Map<String, Object> login(Account account) {
        try {
            Account loginAccount = accountDao.accountLogin(account);
            Map<String, Object> data = new HashMap<>();
            data.put("account", loginAccount);
            data.put("token", TokenTools.generateTokenAPP(loginAccount.getId()));
            //查询用户是否为家长以及家长管理员 是否为老师以及班主任
            if (loginAccount != null && loginAccount.getId() != 0) {
                data.put("manageBaby", accountDao.selectAccountBabyRelation(loginAccount.getId()));
                data.put("manageClass", accountDao.selectAccountClassRelation(loginAccount.getId()));
            }
            //(app端菜单被写死了  无动态权限  暂无处理)
            //获取用户的所有相关菜单
            // List<AppMenu> menuList = accountDao.getAppMenuList(loginAccount.getId());
            //data.put("menuList", getTreeData(menuList));
            return resultMap(Iconstants.RESULT_CODE_0, "success", data);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    /**
     * 修改密码
     *
     * @param account
     * @return
     */
    @Override
    public Map<String, Object> updatePassword(Account account) {
        try {
            int count = accountDao.updateAccount(account);
            if (count > 0) {
                return resultInfo(Iconstants.RESULT_CODE_0, "success");
            } else {
                return resultInfo(Iconstants.RESULT_CODE_1, ResultMap.Result.get("1011"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    /**
     * 重置密码
     *
     * @param account
     * @return
     */
    @Override
    public Map<String, Object> resetPassword(Account account) {
        try {
            ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
            Integer phoneCode = operations.get("User_Login_Phone_Code:" + account.getPhone());
            if (!(ObjectUtils.noneEmpty(phoneCode, account.getValidateCode()) && String.valueOf(phoneCode).equals(account.getValidateCode()))) {
                return resultMap(Iconstants.RESULT_CODE_1, ResultMap.Result.get(1003), null);
            }
            Long accountId = accountDao.getAccountIdByPhone(account.getPhone());
            account.setId(accountId);
            accountDao.updateAccount(account);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    /**
     * 修改用户
     *
     * @param account
     * @return
     */
    @Override
    public Map<String, Object> updateAccount(Account account) {
        try {
            accountDao.updateAccount(account);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    /**
     * 修改宝贝
     *
     * @param student
     * @return
     */
    @Override
    public Map<String, Object> updateBaby(Student student) {
        try {
            studentDao.updateStudent(student);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> listBaby(PageBean page) {
        try {
            page.setTotal(studentDao.getStudentBabyCount(page));
            page.setRows(studentDao.getStudentBabyList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> bindBaby(Student student) {
        try {
            Student student1 = studentDao.getStudentById(student.getId());
            if (student1 == null) {
                return resultInfo(Iconstants.RESULT_CODE_1, "没有该学生!");
            } else if (student1.getRingNo() == null) {
                studentDao.bindStudentMain(student);
            } else {
                if (Objects.equals(student1.getRingNo(), student.getRingNo())) {
                    studentDao.bindStudentBase(student);
                } else {
                    return resultInfo(Iconstants.RESULT_CODE_1, "该学生已绑定其他手环");
                }
            }
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    /**
     * 添加宝贝
     *
     * @param student
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addBaby(Student student) {
        try {
            studentDao.addStudent(student);
            studentDao.addAccountStudent(student);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

//    @Override
//    public Map<String, Object> getClassesList(PageBean page) {
//        try{
//            page.setTotal(classesDao.getClassesCount(page));
//            page.setRows(classesDao.getClassesList(page));
//            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
//        }catch (Exception e){
//            e.printStackTrace();
//            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
//        }
//    }
//
//    @Override
//    public Map<String, Object> getClassesById(Integer classesId) {
//        try{
//            Classes Classes = classesDao.getClassesById(classesId);
//            return resultMap(Iconstants.RESULT_CODE_0, "success", Classes);
//        }catch (Exception e){
//            e.printStackTrace();
//            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
//        }
//    }
//
//    @Override
//    public Map<String, Object> addClasses(Classes classes) {
//        try{
//            classesDao.addClasses(classes);
//            return resultInfo(Iconstants.RESULT_CODE_0, "success");
//        }catch (Exception e){
//            e.printStackTrace();
//            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
//        }
//    }
//

//
//    @Override
//    public Map<String, Object> deleteClasses(List classesIds) {
//        try{
//            classesDao.deleteClasses(classesIds);
//            return resultInfo(Iconstants.RESULT_CODE_0, "success");
//        }catch (Exception e){
//            e.printStackTrace();
//            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
//        }
//    }

    private Map<String, Object> getTreeData(List<AppMenu> menuList) {
        //获取根节点
        AppMenu rootMenu = null;
        for (AppMenu menu : menuList) {
            long pid = menu.getMenuPid();
            if (pid == 0) {
                rootMenu = menu;
            }
        }
        //拼接根节点数据
        Map<String, Object> datamap = new HashMap<>();
        if (rootMenu == null) {
            return datamap;
        }
        long lv1Id = rootMenu.getMenuId();
        datamap.put("id", lv1Id);
        datamap.put("pid", rootMenu.getMenuPid());
        datamap.put("name", rootMenu.getMenuTitle());
        datamap.put("path", rootMenu.getPath());
        datamap.put("icon", rootMenu.getIcon());
        List<Map<String, Object>> child = new ArrayList<>();
        for (AppMenu menu : menuList) {
            //递归拼接数据
            recursionData(menuList, lv1Id, menu, child);
        }
        datamap.put("children", child);
        return datamap;
    }


    private void recursionData(List<AppMenu> menus, long perId, AppMenu currentMenu,
                               List<Map<String, Object>> perChild) {
        long pid = currentMenu.getMenuPid();
        if (pid == perId) {
            long currentId = currentMenu.getMenuId();
            Map<String, Object> data = new HashMap<>();
            data.put("id", currentId);
            data.put("pid", pid);
            data.put("name", currentMenu.getMenuTitle());
            data.put("path", currentMenu.getPath());
            data.put("icon", currentMenu.getIcon());
            List<Map<String, Object>> child = new ArrayList<>();
            for (AppMenu nextMenu : menus) {
                recursionData(menus, currentId, nextMenu, child);
            }
            data.put("children", child);
            perChild.add(data);
        }
    }
}
