package com.scorer.client.entity;

public class Account {

  private long id;
  private String username;
  private String password;
  private long score;
  private String email;
  private long catetoryId;


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


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public long getCatetoryId() {
    return catetoryId;
  }

  public void setCatetoryId(long catetoryId) {
    this.catetoryId = catetoryId;
  }

}
