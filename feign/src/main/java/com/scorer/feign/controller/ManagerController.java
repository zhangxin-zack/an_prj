package com.scorer.feign.controller;

import com.scorer.feign.entity.Manager;
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

    @RequestMapping(value = {"/manager_status", "/manager_status"})
    public Map updateManagerStatus(@RequestBody Manager manager) {
        return managerService.updateManager(manager);
    }

    @RequestMapping(value = "/manager_remove")
    public Map deleteManager(@RequestParam(value = "managerIds") List<Integer> managerIds) {
        return managerService.deleteManager(managerIds);
    }

    @RequestMapping(value = "/role_list")
    public Map getRoleList(@RequestBody PageBean page) {
        return managerService.getRoleList(page);
    }

    @RequestMapping(value = "/role_save")
    public Map saveRole(@RequestParam(value = "roleId") Integer roleId,
                        @RequestParam(value = "menuIds") List<Integer> menuIds) {
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
}