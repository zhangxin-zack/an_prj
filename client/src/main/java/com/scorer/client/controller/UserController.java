package com.scorer.client.controller;

import com.scorer.client.entity.Manager;
import com.scorer.client.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/EDU/user")
public class UserController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/loginManage")
    public Map userLoginManage(@RequestBody Manager manager) {
        return managerService.loginManage(manager);
    }

    @RequestMapping(value = "/loginSchool")
    public Map userLoginSchool(@RequestBody Manager manager) {
        return managerService.loginSchool(manager);
    }

}