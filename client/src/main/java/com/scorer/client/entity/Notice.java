package com.scorer.client.entity;

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Notice {

    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private String noticeFile;
    private Long noticeDate;
    private Long classId;
    private List<String> classIds;
    private Integer fromTo;
    private Integer isRead;
    private Long noticeId;

    public void setClassIdSplit(String classIdSplit){
        if(classIdSplit==null){
            this.classIds = new ArrayList<>();
        }else{
            this.classIds = Arrays.asList(classIdSplit.split("\\|"));
        }
    }

    public String getNoticeFile() {
        return noticeFile;
    }

    public void setNoticeFile(String noticeFile) {
        this.noticeFile = noticeFile;
    }

    public Long getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Long noticeDate) {
        this.noticeDate = noticeDate;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
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

    public List<String> getClassIds() {
        return classIds;
    }

    public void setClassIds(List<String> classIds) {
        this.classIds = classIds;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}
