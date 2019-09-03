package com.scorer.client.entity;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;

public class WSMessage implements Serializable {

    private String username;
    private String phone;
    private String nickName;
    private String img_url;

    private Integer msg_id;
    private int to_home=-1;
    private ArrayList<Integer> to_classes;
    private Integer from_uid;
    private Integer from_student_id;
    private Long msg_time;
    private Integer msg_type;
    private String msg_content;
    private Integer has_read = -1;

    public WSMessage() {
    }

    public String getTo_class_split(){
        if(to_classes==null){
            return null;
        }else{
            return StringUtils.join(to_classes,"|");
        }
    }

    public Integer getFrom_student_id() {
        return from_student_id;
    }

    public void setFrom_student_id(Integer from_student_id) {
        this.from_student_id = from_student_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Integer getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Integer msg_id) {
        this.msg_id = msg_id;
    }

    public int getTo_home() {
        return to_home;
    }

    public void setTo_home(int to_home) {
        this.to_home = to_home;
    }

    public ArrayList<Integer> getTo_classes() {
        return to_classes;
    }

    public void setTo_classes(ArrayList<Integer> to_classes) {
        this.to_classes = to_classes;
    }

    public Integer getFrom_uid() {
        return from_uid;
    }

    public void setFrom_uid(Integer from_uid) {
        this.from_uid = from_uid;
    }

    public Long getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(Long msg_time) {
        this.msg_time = msg_time;
    }

    public Integer getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(Integer msg_type) {
        this.msg_type = msg_type;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public Integer getHas_read() {
        return has_read;
    }

    public void setHas_read(Integer has_read) {
        this.has_read = has_read;
    }
}