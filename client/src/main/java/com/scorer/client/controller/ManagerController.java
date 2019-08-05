package com.scorer.client.controller;

import com.google.gson.Gson;
import com.scorer.client.entity.Manager;
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
    //这个接口有问题
    @RequestMapping(value = "/role_select")
    public Map selectRole() {
        return managerService.getAllRoleList();
    }

    @RequestMapping(value = "/menu_list")
    public Map getMenuList() {
        return managerService.getAllMenuList();
    }

    @RequestMapping(value = "/action_list")
    public Map getActionList(HttpSession session) {
        Manager user = (Manager)session.getAttribute("user");
        return managerService.getActionMenuList(user.getCurrentRoleId());
    }
}