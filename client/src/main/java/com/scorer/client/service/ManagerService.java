package com.scorer.client.service;

import com.scorer.client.entity.Manager;
import com.scorer.client.entity.Menu;
import com.scorer.client.entity.Role;
import com.scorer.client.values.PageBean;

import java.util.List;
import java.util.Map;

public interface ManagerService {

    public Manager login(Manager manager);

    public Map<String, Object> getManagerList(PageBean page);

    public Map<String, Object> addManager(Manager Manager);

    public Map<String, Object> updateManager(Manager Manager);

    public Map<String, Object> deleteManager(List ManagerIds);

    public Map<String, Object> getRoleList(PageBean page);

    public Map<String, Object> saveRole(Long roleId, List<Long> menuIds);

    public Map<String, Object> getAllRoleList();

    public Map<String, Object> getAllMenuList();

    public Map<String, Object> getActionMenuList(Long roleId);
}
