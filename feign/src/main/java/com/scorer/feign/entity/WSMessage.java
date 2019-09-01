package com.scorer.feign.entity;

import java.io.Serializable;

public class WSMessage implements Serializable{

    private String user_phone;
    private String nick_name;
    private String avatar_url;

    private Integer msg_id;
    private Integer to_uid;
    private Integer to_group_id;
    private Integer from_uid;
    private Long msg_time;
    private Integer msg_type;
    private String msg_content;
    private Integer has_read=-1;

    public WSMessage() {
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public Integer getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Integer msg_id) {
        this.msg_id = msg_id;
    }

    public Integer getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(Integer to_uid) {
        this.to_uid = to_uid;
    }

    public Integer getTo_group_id() {
        return to_group_id;
    }

    public void setTo_group_id(Integer to_group_id) {
        this.to_group_id = to_group_id;
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
