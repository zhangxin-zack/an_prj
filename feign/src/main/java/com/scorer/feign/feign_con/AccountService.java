package com.scorer.feign.feign_con;

import com.scorer.feign.entity.Account;
import com.scorer.feign.entity.Student;
import com.scorer.feign.values.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "scorer-client")
public interface AccountService {

    @RequestMapping(value = "/EDU/account/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getAccountList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/account/get_code", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getValidateCode(@RequestParam(value = "phone") String phone);

    @RequestMapping(value = "/EDU/account/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map register(@RequestBody Account account);

    @RequestMapping(value = "/EDU/account/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map login(@RequestBody Account account);

    @RequestMapping(value = "/EDU/account/update_password", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updatePassword(@RequestBody Account account);

    @RequestMapping(value = "/EDU/account/update_reset_pwd", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map resetPassword(@RequestBody Account account);

    @RequestMapping(value = "/EDU/account/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateAccount(@RequestBody Account account);

    @RequestMapping(value = "/EDU/account/add_baby", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addBaby(@RequestBody Student student);

    @RequestMapping(value = "/EDU/account/update_baby", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateBaby(@RequestBody Student student);

    @RequestMapping(value = "/EDU/account/list_baby", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map listBaby(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/account/bind_baby", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map bindBaby(@RequestBody Student student);

    @RequestMapping(value = "/EDU/account/update_relation", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateRelationName(@RequestBody Student student);
}
