package com.scorer.client.controller;

import com.scorer.client.entity.Manager;
import com.scorer.client.entity.Menu;
import com.scorer.client.entity.Role;
import com.scorer.client.service.ManagerService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/EDU/sys")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/manager_list")
    public Map getManagerList(@RequestBody PageBean page) {
        return managerService.getManagerList(page);
    }

    @RequestMapping(value = "/manager_add")
    public Map addManager(@RequestBody Manager manager) {
        return managerService.addManager(manager);
    }

    @RequestMapping(value = "/manager_update")
    public Map updateManager(@RequestBody Manager manager) {
        return managerService.updateManager(manager);
    }

    @RequestMapping(value = "/manager_status")
    public Map updateManagerStatus(@RequestBody Manager manager) {
        return managerService.updateManager(manager);
    }

    @RequestMapping(value = "/manager_remove")
    public Map deleteManager(@RequestParam(value = "managerIds") List<Long> managerIds) {
        return managerService.deleteManager(managerIds);
    }

    @RequestMapping(value = "/role_list")
    public Map getRoleList(@RequestBody PageBean page) {
        return managerService.getRoleList(page);
    }

    @RequestMapping(value = "/role_save")
    public Map saveRole(@RequestParam(value = "roleId") Long roleId,
                        @RequestParam(value = "menuIds") List<Long> menuIds) {
        return managerService.saveRole(roleId, menuIds);
    }

    @RequestMapping(value = "/role_add")
    public Map addRole(@RequestBody Role role) {
        return managerService.addRole(role);
    }

    @RequestMapping(value = "/role_update")
    public Map updateRole(@RequestBody Role role) {
        return managerService.updateRole(role);
    }

    @RequestMapping(value = "/role_delete")
    public Map deleteRole(@RequestParam(value = "roleIds") List<Long> roleIds) {
        return managerService.deleteRoles(roleIds);
    }

    @RequestMapping(value = "/menu_add")
    public Map addMenu(@RequestBody Menu menu) {
        return managerService.addMenu(menu);
    }

    @RequestMapping(value = "/menu_update")
    public Map updateMenu(@RequestBody Menu menu) {
        return managerService.updateMenu(menu);
    }

    @RequestMapping(value = "/menu_delete")
    public Map deleteMenu(@RequestParam(value = "menuIds") List<Long> menuIds) {
        return managerService.deleteMenus(menuIds);
    }


    @RequestMapping(value = "/role_select")
    public Map selectRole() {
        return managerService.getAllRoleList();
    }

    @RequestMapping(value = "/menu_list")
    public Map getMenuList() {
        return managerService.getAllMenuList();
    }

    /**
     * 获取角色权限菜单(树形)
     * @param session
     * @return
     */
    @RequestMapping(value = "/action_list")
    public Map getActionList(HttpSession session) {
        Manager user = (Manager)session.getAttribute("user");
        return managerService.getActionMenuList(user.getCurrentRoleId());
    }


    /**
     * 获取角色权限菜单（非树形）
     * @param session
     * @return
     */
    @RequestMapping(value = "/action_data")
    public Map getActionData(HttpSession session) {
        Manager user = (Manager)session.getAttribute("user");
        return managerService.getActionMenuData(user.getCurrentRoleId());
    }

    @RequestMapping(value = "/get_role_menus")
    public Map getRoleMenuIds(@RequestParam(value = "roleId") Long roleId) {
        return managerService.getRoleMenuIds(roleId);
    }

    /**
     * 获取代理商列表(分页)
     * @param condition
     * @return
     */
    @RequestMapping(value = "/list_agent")
    public Map listAgent(@RequestBody PageBean condition) {
        return managerService.listAgent(condition);
    }

    /**
     * 获取代理商代理区域列表
     * @param condition
     * @return
     */
    @RequestMapping(value = "/list_agent_area")
    public Map listAgentArea(@RequestBody PageBean condition) {
        return managerService.listAgentArea(condition);
    }

    /**
     * 获取指定代理商的代理区域
     * @param agentId
     * @return
     */
    @RequestMapping(value = "/list_all_area")
    public Map listAllArea(@RequestParam("agentId") Long agentId ) {
        return managerService.listAllArea(agentId);
    }

    /**
     * 获取指定代理商的代理区域
     * @param condition
     * @return
     */
    @RequestMapping(value = "/list_all_area_page")
    public Map listAllAreaPage(@RequestBody PageBean condition) {
        return managerService.listAllAreaPage(condition);
    }

    /**
     * 获取代理商列表(不分页)
     * @param condition
     * @return
     */
    @RequestMapping(value = "/list_agent_school")
    public Map listAgentSchool(@RequestBody PageBean condition) {
        return managerService.listAgentSchool(condition);
    }


    /**
     * 添加代理商区域
     * @param agentId  代理商ID(角色为代理商的managerId)
     * @param areaInfo  省市区
     * @return
     */
//    @RequestMapping(value = "/add_agent_area")
//    public Map addAgentArea(@RequestParam(value = "agentId") Long agentId,
//                            @RequestBody List<Map<String, String>> areaInfo) {
//        return managerService.addAgentArea(agentId, areaInfo);
//    }


    @RequestMapping(value = "/add_agent_area")
    public Map addAgentArea(@RequestBody Map areaInfo) {
        return managerService.addAgentArea(areaInfo);
    }

    @RequestMapping(value = "/delete_agent_area")
    public Map deleteAgentArea(@RequestParam(value = "agentAreaIds") List<Long> agentAreaIds) {
        return managerService.deleteAgentArea(agentAreaIds);
    }
}