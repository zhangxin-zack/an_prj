package com.scorer.client.entity;

import java.sql.Timestamp;
import java.util.List;

public class Notice {

    private Long id;
    private String noticTitle;
    private String noticeContent;
    private java.sql.Timestamp noticeDate;
    private Long classId;
    private List<Long> classIds;
    private Integer fromTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoticTitle() {
        return noticTitle;
    }

    public void setNoticTitle(String noticTitle) {
        this.noticTitle = noticTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Timestamp getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Timestamp noticeDate) {
        this.noticeDate = noticeDate;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Integer getFromTo() {
        return fromTo;
    }

    public void setFromTo(Integer fromTo) {
        this.fromTo = fromTo;
    }

    public List<Long> getClassIds() {
        return classIds;
    }

    public void setClassIds(List<Long> classIds) {
        this.classIds = classIds;
    }
}
