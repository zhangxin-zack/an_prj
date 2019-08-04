package com.scorer.client.entity;

import java.sql.Timestamp;

public class Notice {

  private Long id;
  private String noticTitle;
  private String noticeContent;
  private Long noticeRange;
  private java.sql.Timestamp noticeDate;

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

  public Long getNoticeRange() {
    return noticeRange;
  }

  public void setNoticeRange(Long noticeRange) {
    this.noticeRange = noticeRange;
  }

  public Timestamp getNoticeDate() {
    return noticeDate;
  }

  public void setNoticeDate(Timestamp noticeDate) {
    this.noticeDate = noticeDate;
  }
}
