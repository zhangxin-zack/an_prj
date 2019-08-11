package com.scorer.client.controller;

import com.scorer.client.entity.Account;
import com.scorer.client.entity.Student;
import com.scorer.client.service.AccountService;
import com.scorer.client.service.ClassesService;
import com.scorer.client.values.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * app端用户相关接口
 */
@RestController
@RequestMapping("/EDU/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 获取短信验证码
     * @param phone
     * @return
     *
     */
    @RequestMapping(value = "/get_code")
    public Map getValidateCode(@RequestParam(value = "phone") String phone) {
        return accountService.getValidateCode(phone);
    }

    /**
     * 用户注册
     * @param account
     * @return
     */
    @RequestMapping(value = "/register")
    public Map register(@RequestBody Account account) {
        return accountService.register(account);
    }

    /**
     * 用户登录
     * @param account
     * @return
     */
    @RequestMapping(value = "/login")
    public Map login(@RequestBody Account account) {
        return accountService.login(account);
    }

    /**
     * 修改密码
     * @param account
     * @return
     */
    @RequestMapping(value = "/update_password")
    public Map accountUpdatePwd(@RequestBody Account account) {
        return accountService.updatePassword(account);
    }

    /**
     * 重置密码
     * @param account
     * @return
     */
    @RequestMapping(value = "/update_reset_pwd")
    public Map accountResetPassword(@RequestBody Account account) {
        return accountService.resetPassword(account);
    }

    /**
     * 修改用户资料
     * @param account
     * @return
     */
    @RequestMapping(value = "/update")
    public Map accountUpdate(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    /**
     * 添加宝贝
     * @param student
     * @return
     */
    @RequestMapping(value = "/add_baby")
    public Map accountAddBaby(@RequestBody Student student) {
        return accountService.addBaby(student);
    }

    /**
     * 更新宝贝
     * @param student
     * @return
     */
    @RequestMapping(value = "/update_baby")
    public Map accountUpdateBaby(@RequestBody Student student) {
        return accountService.updateBaby(student);
    }

}