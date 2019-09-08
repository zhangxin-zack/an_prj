package com.scorer.clientPhone.entity;

public class RingLocationInfo {

    private Double longitude;
    private Double latitude;

    public RingLocationInfo() {
    }

    public RingLocationInfo(GPSFromCell gpsFromCell) {
        this.latitude=gpsFromCell.getLat();
        this.longitude=gpsFromCell.getLon();
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
}
