package com.scorer.feign.entity;

import java.sql.Timestamp;

public class Account {

  private long id;
  private String username;
  private String password;
  private String trueName;
  private String email;
  private String phone;
  private Timestamp registerDate;
  private String address;
  private long totalScore;
  private long score;
  private long scoreId;
  private long catetgoryId;
  private String categoryName;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getTrueName() {
    return trueName;
  }

  public void setTrueName(String trueName) {
    this.trueName = trueName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Timestamp getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(Timestamp registerDate) {
    this.registerDate = registerDate;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public long getTotalScore() {
    return totalScore;
  }

  public void setTotalScore(long totalScore) {
    this.totalScore = totalScore;
  }

  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }

  public long getScoreId() {
    return scoreId;
  }

  public void setScoreId(long scoreId) {
    this.scoreId = scoreId;
  }

  public long getCatetgoryId() {
    return catetgoryId;
  }

  public void setCatetgoryId(long catetgoryId) {
    this.catetgoryId = catetgoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
}
