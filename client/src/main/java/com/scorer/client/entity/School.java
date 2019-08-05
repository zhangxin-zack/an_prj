package com.scorer.client.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class School {

    private Long id;
    private String title;   //学校名称
    private String simpleTitle;  //学校简称
    private Timestamp registeDate; //加入日期
    private String linkman;   //联系人
    private String phone;   //电话
    private String area;  //所在地区
    private String address;   //地址
    private BigDecimal lon;  //经度
    private BigDecimal lat;  //纬度
    private String email;    //邮箱
    private Long bindCount;  //安装手表数
    private String description;   //学校图文介绍

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSimpleTitle() {
        return simpleTitle;
    }

    public void setSimpleTitle(String simpleTitle) {
        this.simpleTitle = simpleTitle;
    }

    public Timestamp getRegisteDate() {
        return registeDate;
    }

    public void setRegisteDate(Timestamp registeDate) {
        this.registeDate = registeDate;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getBindCount() {
        return bindCount;
    }

    public void setBindCount(Long bindCount) {
        this.bindCount = bindCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
