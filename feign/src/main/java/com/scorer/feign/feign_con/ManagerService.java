package com.scorer.feign.feign_con;

import com.scorer.feign.entity.Manager;
import com.scorer.feign.entity.Menu;
import com.scorer.feign.entity.Role;
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

    @RequestMapping(value = "/EDU/sys/manager_list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getManagerList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/sys/manager_add", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addManager(@RequestBody Manager manager);

    @RequestMapping(value = "/EDU/sys/manager_update", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateManager(@RequestBody Manager manager);

    @RequestMapping(value = "/EDU/sys/manager_remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deleteManager(@RequestParam(value = "managerIds") List<Long> managerIds);

    @RequestMapping(value = "/EDU/sys/role_list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getRoleList(@RequestBody PageBean page);

    @RequestMapping(value = "/EDU/sys/role_save", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map saveRole(@RequestParam(value = "roleId") Integer roleId,
                 @RequestParam(value = "menuIds") List<Long> menuIds);

    @RequestMapping(value = "/EDU/sys/role_select", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getAllRoleList();

    @RequestMapping(value = "/EDU/sys/menu_list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getAllMenuList();

    @RequestMapping(value = "/EDU/sys/action_list", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getActionMenuList();

    @RequestMapping(value = "/EDU/sys/get_role_menus", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getRoleMenuIds(@RequestParam(value = "roleId") Long roleId);

    @RequestMapping(value = "/EDU/sys/role_add", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addRole(@RequestBody Role role);

    @RequestMapping(value = "/EDU/sys/role_update", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateRole(@RequestBody Role role);

    @RequestMapping(value = "/EDU/sys/role_delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deleteRole(@RequestParam(value = "roleIds") List<Long> roleIds);

    @RequestMapping(value = "/EDU/sys/menu_add", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addMenu(@RequestBody Menu menu);

    @RequestMapping(value = "/EDU/sys/menu_update", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map updateMenu(@RequestBody Menu menu);

    @RequestMapping(value = "/EDU/sys/menu_delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deleteMenu(@RequestParam(value = "menuIds") List<Long> menuIds);

    @RequestMapping(value = "/EDU/user/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map userLogin(@RequestBody Manager manager);

    @RequestMapping(value = "/EDU/user/loginManage", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map loginManage(Manager manager);

    @RequestMapping(value = "/EDU/user/loginSchool", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map loginSchool(Manager manager);

    @RequestMapping(value = "/EDU/sys/list_agent", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map listAgent(@RequestBody PageBean condition);

    @RequestMapping(value = "/EDU/sys/list_agent_area", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map listAgentArea(@RequestBody PageBean condition);

    @RequestMapping(value = "/EDU/sys/list_agent_school", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map listAgentSchool(@RequestBody PageBean condition);

    @RequestMapping(value = "/EDU/sys/list_all_area", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map listAllArea(@RequestParam("agentId") Long agentId);

    @RequestMapping(value = "/EDU/sys/add_agent_area", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addAgentArea(@RequestParam(value = "agentId") Long agentId,
                     @RequestParam(value = "areaInfo") List<Map<String, String>> areaMap);

    @RequestMapping(value = "/EDU/sys/delete_agent_area", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deleteAgentArea(@RequestParam(value = "agentAreaIds") List<Long> agentAreaIds);
}
