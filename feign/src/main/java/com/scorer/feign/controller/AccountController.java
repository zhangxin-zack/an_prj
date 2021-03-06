package com.scorer.feign.controller;

import com.scorer.feign.entity.Account;
import com.scorer.feign.entity.Student;
import com.scorer.feign.feign_con.AccountService;
import com.scorer.feign.values.PageBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * app端用户相关接口
 */
@RestController
@RequestMapping("/EDU/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    //会员管理
    @RequestMapping(value = "/list")
    public Map getAccountList(@RequestBody PageBean page){
        return  accountService.getAccountList(page);
    }

    //获取短信验证码
    @RequestMapping(value = "/get_code")
    public Map getValidateCode(@RequestParam(value = "phone") String phone) {
        return accountService.getValidateCode(phone);
    }

    //用户注册
    @RequestMapping(value = "/register")
    public Map register(@RequestBody Account account) {
        return accountService.register(account);
    }

    //用户登录
    @RequestMapping(value = "/login")
    public Map login(@RequestBody Account account) {
        return accountService.login(account);
    }

    //修改密码
    @RequestMapping(value = "/update_password")
    public Map accountUpdatePwd(@RequestBody Account account) {
        return accountService.updatePassword(account);
    }

    //重置密码
    @RequestMapping(value = "/update_reset_pwd")
    public Map accountResetPassword(@RequestBody Account account) {
        return accountService.resetPassword(account);
    }

    //修改用户资料
    @RequestMapping(value = "/update")
    public Map accountUpdate(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    //添加宝贝
    @RequestMapping(value = "/add_baby")
    public Map accountAddBaby(@RequestBody Student student) {
        return accountService.addBaby(student);
    }

    //更新宝贝
    @RequestMapping(value = "/update_baby")
    public Map accountUpdateBaby(@RequestBody Student student) {
        return accountService.updateBaby(student);
    }

    //查询宝贝
    @RequestMapping(value = "/list_baby")
    public Map accountListBaby(@RequestBody PageBean condition) {
        return accountService.listBaby(condition);
    }

    //绑定手环
    @RequestMapping(value = "/bind_baby")
    public Map accountBindBaby(@RequestBody Student student) {
        return accountService.bindBaby(student);
    }

    //更新关系
    @RequestMapping(value = "/update_relation")
    public Map updateRelationName(@RequestBody Student student) {
        return accountService.updateRelationName(student);
    }
}
