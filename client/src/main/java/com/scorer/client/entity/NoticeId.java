package com.scorer.client.entity;

public class NoticeId {

    private Long notice_id;
    private Long notice_time=System.currentTimeMillis();

    public Long getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(Long notice_id) {
        this.notice_id = notice_id;
    }

    public Long getNotice_time() {
        return notice_time;
    }

    public void setNotice_time(Long notice_time) {
        this.notice_time = notice_time;
    }
}
