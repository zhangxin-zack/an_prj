package com.scorer.client.dao.mysql_dao1;

import com.scorer.client.entity.Manager;
import com.scorer.client.entity.Menu;
import com.scorer.client.entity.Role;
import com.scorer.client.values.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerDao {

    Manager login(Manager manager) throws Exception;

    long getManagerCount(PageBean page) throws Exception;

    List<Manager> getManagerList(PageBean page) throws Exception;

    void addManager(Manager manager) throws Exception;

    void addManagerRole(Integer managerId, Integer roleId) throws Exception;

    void deleteManagerRole(Integer id) throws Exception;

    void updateManager(Manager manager) throws Exception;

    void deleteManager(List managerIds) throws Exception;

    long getRoleCount(PageBean page) throws Exception;

    List<Role> getRoleList(PageBean page) throws Exception;

    List<Role> getAllRoleList() throws Exception;

    void deleteRoleMenu(Integer roleId) throws Exception;

    void addRoleMenu(Integer roleId, Integer menuId) throws Exception;

    List<Menu> getAllMenuList() throws Exception;

    List<Menu> getActionMenuList(Integer roleId) throws Exception;
}
