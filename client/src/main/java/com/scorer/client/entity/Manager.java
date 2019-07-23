package com.scorer.client.entity;

import java.util.List;

public class Manager {

    private Long id;
    private Long shoolId;
    private String username;
    private String password;
    private String phoneNo;
    private Integer statusFlag;
    private Long currentRoleId;
    private List<Long> roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShoolId() {
        return shoolId;
    }

    public void setShoolId(Long shoolId) {
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

    public Long getCurrentRoleId() {
        return currentRoleId;
    }

    public void setCurrentRoleId(Long currentRoleId) {
        this.currentRoleId = currentRoleId;
    }

    public List<Long> getRoleId() {
        return roleId;
    }

    public void setRoleId(List<Long> roleId) {
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
