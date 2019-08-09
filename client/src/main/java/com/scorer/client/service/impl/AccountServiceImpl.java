package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.AccountDao;
import com.scorer.client.entity.Account;
import com.scorer.client.entity.AppMenu;
import com.scorer.client.entity.Menu;
import com.scorer.client.service.AccountService;
import com.scorer.client.tools.MessageApi;
import com.scorer.client.tools.ObjectUtils;
import com.scorer.client.tools.RedisTools;
import com.scorer.client.values.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl extends BaseSeviceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @Override
    public Map<String, Object> getValidateCode(String phone) {
        try{
            ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
            Integer phoneCode = operations.get("User_Login_Phone_Code:" + phone);
            if (phoneCode == null || phoneCode == 0) {
                phoneCode = new Random().nextInt(900000) + 100000;
            }
            operations.set("User_Login_Phone_Code:" + phone, phoneCode, 60 * 2, TimeUnit.SECONDS);
            String text = "【手环】您的验证码是" + phoneCode;
            String message = MessageApi.sendSMS(text, phone);
            return resultMap(Iconstants.RESULT_CODE_0, "success", message);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    /**
     * 注册
     * @param account
     * @return
     */
    @Override
    public Map<String, Object> register(Account account) {
        try{
            ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
            Integer phoneCode = operations.get("User_Login_Phone_Code:" + account.getPhone());
            //判断验证码是否超时
            if (ObjectUtils.noneEmpty(phoneCode)) {
                return resultMap(Iconstants.RESULT_CODE_1, ResultMap.Result.get("1010"), null);
            }
            //判断验证码是否正确
            if (phoneCode.equals(account.getValidateCode())) {
                return resultMap(Iconstants.RESULT_CODE_1, ResultMap.Result.get("1003"), null);
            }

            Map<String, Object> data = new HashMap<>();
            accountDao.addAccount(account);
            Long accountId = account.getId();
            //注册默认为家长
            accountDao.addAccountTitle(accountId, 1l);
            data.put("account", account);
            //获取token
            String token = RedisTools.generateTokenAdminWeb(accountId);
            data.put("token",  token);
            //获取家长身份相关菜单
            List<AppMenu> menuList = accountDao.getAppMenuList(accountId);
            data.put("menuList",  getTreeData(menuList));

            return resultMap(Iconstants.RESULT_CODE_0, "success", data);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    /**
     * 用户登录
     * @param account
     * @return
     */
    @Override
    public Map<String, Object> login(Account account) {
        try{
            Account loginAccount = accountDao.accountLogin(account);
            Map<String, Object> data = new HashMap<>();
            data.put("account", loginAccount);
            //获取用户的所有相关菜单
            List<AppMenu> menuList = accountDao.getAppMenuList(loginAccount.getId());
            data.put("menuList",  getTreeData(menuList));
            return resultMap(Iconstants.RESULT_CODE_0, "success", loginAccount);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    /**
     * 修改密码
     * @param account
     * @return
     */
    @Override
    public Map<String, Object> updatePassword(Account account) {
        try{
            int count = accountDao.updateAccount(account);
            if(count > 0){
                return resultInfo(Iconstants.RESULT_CODE_0, "success");
            }else{
                return resultInfo(Iconstants.RESULT_CODE_1, ResultMap.Result.get("1011"));
            }
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    /**
     * 重置密码
     * @param account
     * @return
     */
    @Override
    public Map<String, Object> resetPassword(Account account) {
        try{
            ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
            Integer phoneCode = operations.get("User_Login_Phone_Code:" + account.getPhone());
            //判断验证码是否超时
            if (ObjectUtils.noneEmpty(phoneCode)) {
                return resultMap(Iconstants.RESULT_CODE_1, ResultMap.Result.get("1010"), null);
            }
            //判断验证码是否正确
            if (phoneCode.equals(account.getValidateCode())) {
                return resultMap(Iconstants.RESULT_CODE_1, ResultMap.Result.get("1003"), null);
            }
            accountDao.updateAccount(account);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    /**
     * 修改用户
     * @param account
     * @return
     */
    @Override
    public Map<String, Object> updateAccount(Account account) {
        try{
            accountDao.updateAccount(account);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
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

    private Map<String, Object> getTreeData(List<AppMenu> menuList){
        //获取根节点
        AppMenu rootMenu = null;
        for(AppMenu menu: menuList){
            long pid = menu.getMenuPid();
            if(pid == 0){
                rootMenu = menu;
            }
        }
        //拼接根节点数据
        Map<String, Object> datamap = new HashMap<>();
        long lv1Id = rootMenu.getMenuId();
        datamap.put("id", lv1Id);
        datamap.put("pid", rootMenu.getMenuPid());
        datamap.put("name", rootMenu.getMenuTitle());
        datamap.put("path", rootMenu.getPath());
        datamap.put("icon", rootMenu.getIcon());
        List<Map<String, Object>> child = new ArrayList<>();
        for(AppMenu menu: menuList){
            //递归拼接数据
            recursionData(menuList, lv1Id, menu, child);
        }
        datamap.put("children", child);
        return datamap;
    }


    private void recursionData(List<AppMenu> menus, long perId, AppMenu currentMenu,
                               List<Map<String, Object>> perChild){
        long pid = currentMenu.getMenuPid();
        if(pid == perId){
            long currentId = currentMenu.getMenuId();
            Map<String, Object> data = new HashMap<>();
            data.put("id", currentId);
            data.put("pid", pid);
            data.put("name", currentMenu.getMenuTitle());
            data.put("path", currentMenu.getPath());
            data.put("icon", currentMenu.getIcon());
            List<Map<String, Object>> child = new ArrayList<>();
            for(AppMenu nextMenu: menus) {
                recursionData(menus, currentId, nextMenu, child);
            }
            data.put("children", child);
            perChild.add(data);
        }
    }
}
