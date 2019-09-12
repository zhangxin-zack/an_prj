package com.scorer.feign.entity;

public class AppTypeDetail {

    private Integer id;
    private Integer type_id;
    private String type_title;
    private String type_content;
    private String type_img;
    private Long type_time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getType_title() {
        return type_title;
    }

    public void setType_title(String type_title) {
        this.type_title = type_title;
    }

    public String getType_content() {
        return type_content;
    }

    public void setType_content(String type_content) {
        this.type_content = type_content;
    }

    public String getType_img() {
        return type_img;
    }

    public void setType_img(String type_img) {
        this.type_img = type_img;
    }

    public Long getType_time() {
        return type_time;
    }

    public void setType_time(Long type_time) {
        this.type_time = type_time;
    }
}
