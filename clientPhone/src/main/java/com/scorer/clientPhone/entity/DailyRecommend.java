package com.scorer.clientPhone.entity;


public class DailyRecommend {

  private long id;
  private String content;
  private String imgPath;
  private String imgUrl;
  private java.util.Date publishDate;
  private long type;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getImgPath() {
    return imgPath;
  }

  public void setImgPath(String imgPath) {
    this.imgPath = imgPath;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public java.util.Date getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(java.util.Date publishDate) {
    this.publishDate = publishDate;
  }

  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }

}
