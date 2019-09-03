package com.scorer.feign.controller;

import com.scorer.feign.entity.Manager;
import com.scorer.feign.feign_con.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/EDU/user")
public class UserController {

    @Resource
    private ManagerService managerService;

    @RequestMapping(value = "/loginManage")
    public Map userLoginManage(@RequestBody Manager manager) {
        return managerService.loginManage(manager);
    }

    @RequestMapping(value = "/loginSchool")
    public Map userLoginSchool(@RequestBody Manager manager) {
        return managerService.loginSchool(manager);
    }

    @RequestMapping(value = "/resetpwd")
    public Map resetPassword(@RequestBody Manager manager) {
        return managerService.updateManager(manager);
    }


}