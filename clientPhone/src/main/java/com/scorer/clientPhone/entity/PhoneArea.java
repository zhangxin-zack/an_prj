package com.scorer.clientPhone.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "phone_area")
public class PhoneArea {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String ring_no;

    private List<AreaLoc> safeAreaList;
    private List<AreaLoc> safeDangerList;

    public PhoneArea() {
    }

    @PersistenceConstructor
    public PhoneArea(ObjectId id, String ring_no, List<AreaLoc> safeAreaList, List<AreaLoc> safeDangerList) {
        this.id = id;
        this.ring_no = ring_no;
        this.safeAreaList = safeAreaList;
        this.safeDangerList = safeDangerList;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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