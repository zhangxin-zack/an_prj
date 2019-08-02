package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.ManagerDao;
import com.scorer.client.entity.Manager;
import com.scorer.client.entity.Menu;
import com.scorer.client.entity.Role;
import com.scorer.client.service.ManagerService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl extends BaseSeviceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Override
    public Manager login(Manager manager) {
        try{
            return managerDao.login(manager);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<String, Object> getManagerList(PageBean page) {
        try{
            page.setTotal(managerDao.getManagerCount(page));
            page.setRows(managerDao.getManagerList(page));
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
            for(long roleId: manager.getRoleId()){
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
            for(long roleId: manager.getRoleId()){
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
            managerDao.deleteManagerRole(roleId);
            for(long menuId: menuIds){
                managerDao.addManagerRole(roleId, menuId);
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


    private void recursionData(List<Menu> menus, long perId,
            Menu currentMenu, List<Map<String, Object>> perChild){
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
