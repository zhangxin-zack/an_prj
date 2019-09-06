package com.scorer.client.service;

import com.scorer.client.entity.Manager;
import com.scorer.client.entity.Menu;
import com.scorer.client.entity.Role;
import com.scorer.client.entity.SchoolMenu;
import com.scorer.client.values.PageBean;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface ManagerService {

    Map<String, Object> getManagerList(PageBean page);

    Map<String, Object> addManager(Manager manager);

    Map<String, Object> updateManager(Manager manager);

    Map<String, Object> deleteManager(List managerIds);

    Map<String, Object> getRoleList(PageBean page);

    Map<String, Object> addRole(Role role);

    Map<String, Object> updateRole(Role role);

    Map<String, Object> deleteRoles(List roleIds);

    Map<String, Object> addMenu(Menu menu);

    Map<String, Object> updateMenu(Menu menu);

    Map<String, Object> deleteMenus(List menuIds);

    Map<String, Object> saveRole(Long roleId, List<Long> menuIds);

    Map<String, Object> getAllRoleList();

    Map<String, Object> getAllMenuList();

    Map<String, Object> getActionMenuList(Long roleId);

    Map<String, Object> getActionMenuData(Long currentRoleId);

    Map<String, Object> getRoleMenuIds(Long roleId);

    Map<String,Object> loginManage(Manager manager);

    Map<String,Object> loginSchool(Manager manager);

    Map<String, Object> listAgent(PageBean page);

    Map<String, Object> listAgentArea(PageBean page);

    Map<String, Object> listAgentSchool(PageBean page);

    Map<String, Object> listAllArea(Long agentId);

    Map<String, Object> addAgentArea(Long agentId, List<Map<String, String>> areaMap);

    Map<String, Object> deleteAgentArea(List<Long> agentAreaIds);
}
