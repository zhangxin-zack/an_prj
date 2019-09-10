package com.scorer.clientPhone.entity;

public class RingLocationInfo {

    private Double longitude;
    private Double latitude;
    private Double r;
    private String address;

    public RingLocationInfo() {
    }

    public RingLocationInfo(GPSFromCell gpsFromCell) {
        this.latitude=gpsFromCell.getLat();
        this.longitude=gpsFromCell.getLon();
        this.address=gpsFromCell.getAddress();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }
}
