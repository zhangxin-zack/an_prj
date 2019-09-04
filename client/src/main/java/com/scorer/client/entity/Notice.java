package com.scorer.client.entity;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Notice {

    private Long id;
    private String noticTitle;
    private String noticeContent;
    private java.sql.Timestamp noticeDate;
    private Long classId;
    private List<String> classIds;
    private Integer fromTo;
    private Integer isRead;

    public String getClassIdSplit(){
        if(classIds==null){
            return null;
        }else{
            return StringUtils.join(classIds,"|");
        }
    }

    public void setClassIdSplit(String classIdSplit){
        if(classIdSplit==null){
            this.classIds = new ArrayList<>();
        }else{
            this.classIds = Arrays.asList(classIdSplit.split("\\|"));
        }
    }

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
