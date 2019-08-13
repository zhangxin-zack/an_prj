package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.ManagerDao;
import com.scorer.client.entity.Manager;
import com.scorer.client.entity.Menu;
import com.scorer.client.entity.Role;
import com.scorer.client.service.ManagerService;
import com.scorer.client.tools.TokenTools;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl extends BaseSeviceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Override
    public Map<String,Object> login(Manager manager) {

        try{
            Manager loginManager = managerDao.login(manager);
            if(loginManager != null){
                loginManager.setPassword(null);
                loginManager.setToken(TokenTools.generateTokenSchool(loginManager.getId()));
                return resultMap(Iconstants.RESULT_CODE_0, "success", loginManager);
            }else{
                return resultMap(Iconstants.RESULT_CODE_1, "账号或密码错误", null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getManagerList(PageBean page) {
        try{
            page.setRows(managerDao.getManagerList(page));
            page.setTotal(managerDao.getManagerCount(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addManager(Manager manager) {
        try{
            managerDao.addManager(manager);
            for(Long roleId: manager.getRoleId()){
                managerDao.addManagerRole(manager.getId(), roleId);
            }
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateManager(Manager manager) {
        try{
            managerDao.updateManager(manager);
            managerDao.deleteManagerRole(manager.getId());
            for(Long roleId: manager.getRoleId()){
                managerDao.addManagerRole(manager.getId(), roleId);
            }
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteManager(List ManagerIds) {
        try{
            //删除管理员相关角色
            managerDao.deleteManagersRole(ManagerIds);
            //删除管理员
            managerDao.deleteManager(ManagerIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getRoleList(PageBean page) {
        try{
            page.setTotal(managerDao.getRoleCount(page));
            page.setRows(managerDao.getRoleList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> saveRole(Long roleId, List<Long> menuIds) {
        try{
            managerDao.deleteRoleMenu(roleId);
            for(Long menuId: menuIds){
                managerDao.addRoleMenu(roleId, menuId);
            }
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        }catch (Exception e){
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getAllRoleList() {
        try{
            List<Role> roleList = managerDao.getAllRoleList();
            return resultMap(Iconstants.RESULT_CODE_0, "success", roleList);
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getAllMenuList() {
        try{
            List<Menu> menuList = managerDao.getAllMenuList();
            return resultMap(Iconstants.RESULT_CODE_0, "success", getTreeData(menuList));
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }

    }

    @Override
    public Map<String, Object> getActionMenuList(Long roleId) {
        try{
            List<Menu> menuList = managerDao.getActionMenuList(roleId);
            return resultMap(Iconstants.RESULT_CODE_0, "success", getTreeData(menuList));
        }catch (Exception e){
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    private Map<String, Object> getTreeData(List<Menu> menuList){
        //获取根节点
        Menu rootMenu = null;
        for(Menu menu: menuList){
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
        for(Menu menu: menuList){
            //递归拼接数据
            recursionData(menuList, lv1Id, menu, child);
        }
        datamap.put("children", child);
        return datamap;
    }


    private void recursionData(List<Menu> menus, long perId, Menu currentMenu,
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
            for(Menu nextMenu: menus) {
                recursionData(menus, currentId, nextMenu, child);
            }
            data.put("children", child);
            perChild.add(data);
        }
    }
}
