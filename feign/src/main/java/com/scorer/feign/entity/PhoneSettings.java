package com.scorer.feign.entity;

public class PhoneSettings {

    private Object id;

    private String ring_no;

    private String remind_str;
    private String contacts_str;
    private String silence_time_str;

    public PhoneSettings() {
    }

    public PhoneSettings(Object id, String ring_no, String remind_str, String contacts_str, String silence_time_str) {
        this.id = id;
        this.ring_no = ring_no;
        this.remind_str = remind_str;
        this.contacts_str = contacts_str;
        this.silence_time_str = silence_time_str;
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

    public String getRemind_str() {
        return remind_str;
    }

    public void setRemind_str(String remind_str) {
        this.remind_str = remind_str;
    }

    public String getContacts_str() {
        return contacts_str;
    }

    public void setContacts_str(String contacts_str) {
        this.contacts_str = contacts_str;
    }

    public String getSilence_time_str() {
        return silence_time_str;
    }

    public void setSilence_time_str(String silence_time_str) {
        this.silence_time_str = silence_time_str;
    }
}