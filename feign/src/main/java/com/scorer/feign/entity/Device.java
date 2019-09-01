package com.scorer.feign.entity;


public class Device {

  private Long id;
  private String deviceNo;
  private Long productId;
  private String productType;
  private String productModel;
  private Long isBind;
  private java.sql.Timestamp bindDate;
  private java.sql.Timestamp registerDate;

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }


  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }


  public String getProductModel() {
    return productModel;
  }

  public void setProductModel(String productModel) {
    this.productModel = productModel;
  }


  public Long getIsBind() {
    return isBind;
  }

  public void setIsBind(Long isBind) {
    this.isBind = isBind;
  }


  public java.sql.Timestamp getBindDate() {
    return bindDate;
  }

  public void setBindDate(java.sql.Timestamp bindDate) {
    this.bindDate = bindDate;
  }


  public java.sql.Timestamp getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(java.sql.Timestamp registerDate) {
    this.registerDate = registerDate;
  }

}
