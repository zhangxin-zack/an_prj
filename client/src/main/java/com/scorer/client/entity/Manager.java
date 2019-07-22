package com.scorer.client.entity;

import java.util.List;

public class Manager {

    private Integer id;
    private Integer shoolId;
    private String username;
    private String password;
    private String phoneNo;
    private Integer statusFlag;
    private Integer currentRoleId;
    private List<Integer> roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShoolId() {
        return shoolId;
    }

    public void setShoolId(Integer shoolId) {
        this.shoolId = shoolId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Integer getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Integer statusFlag) {
        this.statusFlag = statusFlag;
    }

    public Integer getCurrentRoleId() {
        return currentRoleId;
    }

    public void setCurrentRoleId(Integer currentRoleId) {
        this.currentRoleId = currentRoleId;
    }

    public List<Integer> getRoleId() {
        return roleId;
    }

    public void setRoleId(List<Integer> roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", shoolId=" + shoolId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
