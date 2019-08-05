package com.scorer.feign.feign_con;

import com.scorer.feign.entity.Manager;
import com.scorer.feign.values.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "scorer-client")
public interface ManagerService {

    @RequestMapping(value = "/Scorer/sys/manager_list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getManagerList(@RequestBody PageBean page);

    @RequestMapping(value = "/Scorer/sys/manager_add", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addManager(@RequestBody Manager manager);

    @RequestMapping(value = "/Scorer/sys/manager_update", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateManager(@RequestBody Manager manager);

    @RequestMapping(value = "/Scorer/sys/manager_remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deleteManager(@RequestParam(value = "managerIds[]") List<Integer> managerIds);

    @RequestMapping(value = "/Scorer/sys/role_list")
    Map getRoleList(@RequestBody PageBean page);

    @RequestMapping(value = "/Scorer/sys/role_save")
    Map saveRole(@RequestParam(value = "roleId") Integer roleId,
                 @RequestParam(value = "menuIds") List<Integer> menuIds);


    @RequestMapping(value = "/Scorer/sys/role_select")
    Map getAllRoleList();

    @RequestMapping(value = "/Scorer/sys/menu_list")
    Map getAllMenuList();

    @RequestMapping(value = "/Scorer/sys/action_list")
    Map getActionMenuList();

    @RequestMapping(value = "/Scorer/user/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map userLogin(@RequestBody Manager manager);
}