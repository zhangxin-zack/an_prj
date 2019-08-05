package com.scorer.client.controller;

import com.scorer.client.constant.Iconstants;
import com.scorer.client.entity.Manager;
import com.scorer.client.entity.Teacher;
import com.scorer.client.service.ManagerService;
import com.scorer.client.service.TeacherService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/EDU/user")
public class UserController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/login")
    public Map userLogin(HttpSession session,@RequestBody Manager manager) {
        Manager loginManager = managerService.login(manager);
        Map map = new HashMap<>();
        if(loginManager != null){
            map.put(Iconstants.RESULT_CODE, Iconstants.RESULT_CODE_0);
            map.put(Iconstants.RESULT_MESSAGE, "sucess");
            session.setAttribute("user", loginManager);
        }else{
            map.put(Iconstants.RESULT_CODE, Iconstants.RESULT_CODE_1);
            map.put(Iconstants.RESULT_MESSAGE, "账号或密码错误");
        }
        map.put(Iconstants.RESULT_DATA, loginManager);
        return map;
    }

    @RequestMapping(value = "/resetpwd")
    public Map resetPassword(@RequestBody Manager manager) {
        return managerService.updateManager(manager);
    }


}