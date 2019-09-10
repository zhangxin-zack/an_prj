package com.scorer.client.service.impl;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.dao.mysql_dao1.ManagerDao;
import com.scorer.client.dao.mysql_dao1.SchoolDao;
import com.scorer.client.entity.Manager;
import com.scorer.client.entity.Menu;
import com.scorer.client.entity.Role;
import com.scorer.client.entity.SchoolMenu;
import com.scorer.client.service.ManagerService;
import com.scorer.client.service.impl.BaseSeviceImpl;
import com.scorer.client.tools.HuaWeiOBS;
import com.scorer.client.tools.TokenTools;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl extends BaseSeviceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public Map<String, Object> loginManage(Manager manager) {
        try {
            Manager loginManager = managerDao.loginManage(manager);
            if (loginManager != null) {
                managerDao.setLoginTime(manager);
                loginManager.setPassword(null);
                loginManager.setToken(TokenTools.generateTokenSchool(loginManager.getId()));
                return resultMap(Iconstants.RESULT_CODE_0, "success", loginManager);
            } else {
                return resultMap(Iconstants.RESULT_CODE_1, "账号或密码错误", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> loginSchool(Manager manager) {
        try {
            Manager loginManager = managerDao.loginSchool(manager);
            if (loginManager != null) {
                managerDao.setLoginTime(manager);
                loginManager.setPassword(null);
                loginManager.setToken(TokenTools.generateTokenSchool(loginManager.getId()));
                return resultMap(Iconstants.RESULT_CODE_0, "success", loginManager);
            } else {
                return resultMap(Iconstants.RESULT_CODE_1, "账号或密码错误", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> listAgent(PageBean page) {
        try {
            page.setTotal(managerDao.getAgentCount(page));
            page.setRows(managerDao.getAgentList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> listAgentArea(PageBean page) {
        try {
            page.setTotal(managerDao.getAgentAreaCount(page));
            page.setRows(managerDao.getAgentAreaList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> listAgentSchool(PageBean page) {
        try {
            page.setTotal(schoolDao.getAgentSchoolCount(page));
            page.setRows(schoolDao.getAgentSchoolList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> listAllArea(Long agentId) {
        try {
            List<Map> areaList = managerDao.listAllArea(agentId);
            return resultMap(Iconstants.RESULT_CODE_0, "success", areaList);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> listAllAreaPage(PageBean page) {
        try {
            page.setTotal(managerDao.listAllAreaCount(page));
            page.setRows(managerDao.listAllAreaList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getManagerList(PageBean page) {
        try {
            page.setRows(managerDao.getManagerList(page));
            page.setTotal(managerDao.getManagerCount(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addManager(Manager manager) {
        try {
            managerDao.addManager(manager);
            for (Long roleId : manager.getRoleId()) {
                managerDao.addManagerRole(manager.getId(), roleId);
            }
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> updateManager(Manager manager) {
        try {
            managerDao.updateManager(manager);
            managerDao.deleteManagerRole(manager.getId());
            for (Long roleId : manager.getRoleId()) {
                managerDao.addManagerRole(manager.getId(), roleId);
            }
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteManager(List ManagerIds) {
        try {
            //删除管理员相关角色
            managerDao.deleteManagersRole(ManagerIds);
            //删除管理员
            managerDao.deleteManager(ManagerIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getRoleList(PageBean page) {
        try {
            page.setTotal(managerDao.getRoleCount(page));
            page.setRows(managerDao.getRoleList(page));
            return resultMap(Iconstants.RESULT_CODE_0, "success", page);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addRole(Role role) {
        try {
            managerDao.addRole(role);
            return resultMap(Iconstants.RESULT_CODE_0, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> updateRole(Role role) {
        try {
            managerDao.updateRole(role);
            return resultMap(Iconstants.RESULT_CODE_0, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> deleteRoles(List roleIds) {
        try {
            //删除角色表
            managerDao.deleteRole(roleIds);
            //删除角色相关菜单关系以及管理员关系
            managerDao.deleteManagersRoleByRoleIds(roleIds);
            managerDao.deleteRoleMenuByRoleIds(roleIds);
            return resultMap(Iconstants.RESULT_CODE_0, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }


    @Override
    public Map<String, Object> addMenu(Menu menu) {
        try {
            managerDao.addMenu(menu);
            return resultMap(Iconstants.RESULT_CODE_0, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> updateMenu(Menu menu) {
        try {
            managerDao.updateMenu(menu);
            return resultMap(Iconstants.RESULT_CODE_0, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> deleteMenus(List menuIds) {
        try {
            //删除角色表
            managerDao.deleteMenus(menuIds);
            //删除角色相关菜单关系
            managerDao.deleteManagersRole(menuIds);
            return resultMap(Iconstants.RESULT_CODE_0, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> addAgentArea(Map areaInfo) {
        try {
            Long agentId = Long.parseLong(areaInfo.get("agentId").toString());
            List<Map<String, String>> areaMap = (List<Map<String, String>>) areaInfo.get("areaInfo");
            for (Map<String, String> area : areaMap) {
                managerDao.saveAgentRole(agentId, area.get("province"), area.get("city"), area.get("village"));
            }
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> deleteAgentArea(List<Long> agentAreaIds) {
        try {
            managerDao.deleteAgentRole(agentAreaIds);
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> uploadFile(MultipartFile file) {
        String path = HuaWeiOBS.fileUpload(file);
        if(path!=null){
            return resultMap(Iconstants.RESULT_CODE_1, "success", "{\"path\":\""+path+"\"}");
        }else{
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!");
        }
    }

    @Override
    public Map<String, Object> saveRole(Long roleId, List<Long> menuIds) {
        try {
            managerDao.deleteRoleMenu(roleId);
            for (Long menuId : menuIds) {
                managerDao.addRoleMenu(roleId, menuId);
            }
            return resultInfo(Iconstants.RESULT_CODE_0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return resultInfo(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getAllRoleList() {
        try {
            List<Role> roleList = managerDao.getAllRoleList();
            return resultMap(Iconstants.RESULT_CODE_0, "success", roleList);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    /**
     * 查询全量菜单（树形）
     *
     * @return
     */
    @Override
    public Map<String, Object> getAllMenuList() {
        try {
            List<Menu> menuList = managerDao.getAllMenuList();
            return resultMap(Iconstants.RESULT_CODE_0, "success", getTreeData(menuList));
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }

    }

    @Override
    public Map<String, Object> getActionMenuList(Long roleId) {
        try {
            List<Menu> menuList = managerDao.getActionMenuList(roleId);
            return resultMap(Iconstants.RESULT_CODE_0, "success", getTreeData(menuList));
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getActionMenuData(Long currentRoleId) {
        try {
            List<Menu> menuList = managerDao.getActionMenuList(currentRoleId);
            return resultMap(Iconstants.RESULT_CODE_0, "success", menuList);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    @Override
    public Map<String, Object> getRoleMenuIds(Long roleId) {
        try {
            List<Long> menuIds = managerDao.getRoleMenuIds(roleId);
            System.out.println("------------------------------------------------" + menuIds);
            return resultMap(Iconstants.RESULT_CODE_0, "success", menuIds);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(Iconstants.RESULT_CODE_1, "failed!" + e.getMessage(), null);
        }
    }

    private Map<String, Object> getTreeData(List<Menu> menuList) {
        //获取根节点
        Menu rootMenu = null;
        for (Menu menu : menuList) {
            long pid = menu.getMenuPid();
            if (pid == 0) {
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
        for (Menu menu : menuList) {
            //递归拼接数据
            recursionData(menuList, lv1Id, menu, child);
        }
        datamap.put("children", child);
        return datamap;
    }


    private void recursionData(List<Menu> menus, long perId, Menu currentMenu,
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
            for (Menu nextMenu : menus) {
                recursionData(menus, currentId, nextMenu, child);
            }
            data.put("children", child);
            perChild.add(data);
        }
    }

}
