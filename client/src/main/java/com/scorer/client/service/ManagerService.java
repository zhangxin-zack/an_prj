package com.scorer.client.service;

import com.scorer.client.entity.Manager;
import com.scorer.client.entity.Menu;
import com.scorer.client.entity.Role;
import com.scorer.client.values.PageBean;

import java.util.List;
import java.util.Map;

public interface ManagerService {

    Manager login(Manager manager);

    Map<String, Object> getManagerList(PageBean page);

    Map<String, Object> addManager(Manager Manager);

    Map<String, Object> updateManager(Manager Manager);

    Map<String, Object> deleteManager(List ManagerIds);

    Map<String, Object> getRoleList(PageBean page);

    Map<String, Object> saveRole(Integer roleId, List<Integer> menuIds);

    Map<String, Object> getAllRoleList();

    Map<String, Object> getAllMenuList();

    Map<String, Object> getActionMenuList(Integer roleId);
}
