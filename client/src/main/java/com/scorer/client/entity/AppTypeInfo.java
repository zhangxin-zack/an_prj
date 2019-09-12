package com.scorer.client.entity;


import java.util.List;

public class AppTypeInfo {

    private Integer id;
    private String type_name;
    private String type_label;
    private Long type_app_in;

    private List<AppTypeDetail> appTypeDetail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getType_label() {
        return type_label;
    }

    public void setType_label(String type_label) {
        this.type_label = type_label;
    }

    public Long getType_app_in() {
        return type_app_in;
    }

    public void setType_app_in(Long type_app_in) {
        this.type_app_in = type_app_in;
    }

    public List<AppTypeDetail> getAppTypeDetail() {
        return appTypeDetail;
    }

    public void setAppTypeDetail(List<AppTypeDetail> appTypeDetail) {
        this.appTypeDetail = appTypeDetail;
    }
}
