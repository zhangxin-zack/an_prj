package com.scorer.feign.entity;

import java.util.List;

public class PhoneArea {

    private Object id;

    private String ring_no;

    private List<AreaLoc> safeAreaList;
    private List<AreaLoc> safeDangerList;

    public PhoneArea() {
    }

    public PhoneArea(Object id, String ring_no, List<AreaLoc> safeAreaList, List<AreaLoc> safeDangerList) {
        this.id = id;
        this.ring_no = ring_no;
        this.safeAreaList = safeAreaList;
        this.safeDangerList = safeDangerList;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getRing_no() {
        return ring_no;
    }

    public void setRing_no(String ring_no) {
        this.ring_no = ring_no;
    }

    public List<AreaLoc> getSafeAreaList() {
        return safeAreaList;
    }

    public void setSafeAreaList(List<AreaLoc> safeAreaList) {
        this.safeAreaList = safeAreaList;
    }


    public List<AreaLoc> getSafeDangerList() {
        return safeDangerList;
    }

    public void setSafeDangerList(List<AreaLoc> safeDangerList) {
        this.safeDangerList = safeDangerList;
    }

    public class AreaLoc{
        private Double radius;      //区域半径
        private Double longitude;
        private Double latitude;

        public Double getRadius() {
            return radius;
        }

        public void setRadius(Double radius) {
            this.radius = radius;
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
}