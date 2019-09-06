package com.scorer.feign.controller;

import com.scorer.feign.entity.Manager;
import com.scorer.feign.entity.Menu;
import com.scorer.feign.entity.Role;
import com.scorer.feign.feign_con.ManagerService;
import com.scorer.feign.values.PageBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/EDU/sys")
public class ManagerController {

    @Resource
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
    public Map saveRole(@RequestParam(value = "roleId") Integer roleId,
                        @RequestParam(value = "menuIds") List<Long> menuIds) {
        return managerService.saveRole(roleId, menuIds);
    }

    @RequestMapping(value = "/role_select")
    public Map selectRole() {
        return managerService.getAllRoleList();
    }

    @RequestMapping(value = "/menu_list")
    public Map getMenuList() {
        return managerService.getAllMenuList();
    }

    @RequestMapping(value = "/action_list")
    public Map getActionList() {
        return managerService.getActionMenuList();
    }

    @RequestMapping(value = "/get_role_menus")
    public Map getRoleMenus(@RequestParam(value = "roleId") Long roleId) {
        return managerService.getRoleMenuIds(roleId);
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
        return managerService.deleteRole(roleIds);
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
        return managerService.deleteMenu(menuIds);
    }

    @RequestMapping(value = "/list_agent")
    public Map listAgent(@RequestBody PageBean condition) {
        return managerService.listAgent(condition);
    }

    @RequestMapping(value = "/list_agent_area")
    public Map listAgentArea(@RequestBody PageBean condition){
        return managerService.listAgentArea(condition);
    }

    @RequestMapping(value = "/list_agent_school")
    public Map listAgentSchool(@RequestBody PageBean condition) {
        return managerService.listAgentSchool(condition);
    }

    @RequestMapping(value = "/list_all_area")
    public Map listAllArea(@RequestParam("agentId") Long agentId )
    {
        return managerService.listAllArea(agentId);
    }

    @RequestMapping(value = "/add_agent_area")
    public Map addAgentArea(@RequestParam(value = "agentId") Long agentId,
                            @RequestParam(value = "areaInfo") List<Map<String, String>> areaMap) {
        return managerService.addAgentArea(agentId, areaMap);
    }

    @RequestMapping(value = "/delete_agent_area")
    public Map deleteAgentArea(@RequestParam(value = "agentAreaIds") List<Long> agentAreaIds){
        return managerService.deleteAgentArea(agentAreaIds);
    }
}