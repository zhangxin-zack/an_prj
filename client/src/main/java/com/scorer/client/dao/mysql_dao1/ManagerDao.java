package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.Manager;
import com.scorer.client.entity.Menu;
import com.scorer.client.entity.Role;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerDao {

    public Manager login(Manager manager) throws Exception;

    public long getManagerCount(PageBean page) throws Exception;

    public List<Manager> getManagerList(PageBean page) throws Exception;

    public void addManager(Manager manager) throws Exception;

    public void addManagerRole(Integer managerId, Integer roleId) throws Exception;

    public void deleteManagerRole(Integer id) throws Exception;

    public void updateManager(Manager manager) throws Exception;

    public void deleteManager(List managerIds) throws Exception;

    public long getRoleCount(PageBean page) throws Exception;

    public List<Role> getRoleList(PageBean page) throws Exception;

    public List<Role> getAllRoleList() throws Exception;

    public void deleteRoleMenu(Integer roleId) throws Exception;

    public void addRoleMenu(Integer roleId, Integer menuId) throws Exception;

    public List<Menu> getAllMenuList() throws Exception;

    public List<Menu> getActionMenuList(Integer roleId) throws Exception;
}
