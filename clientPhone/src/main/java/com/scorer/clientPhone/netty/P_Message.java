package com.scorer.clientPhone.netty;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "p_message")
public class P_Message {

    @Id
    private ObjectId id;

    @Indexed(direction = IndexDirection.DESCENDING)
    private Long msg_time;

    private String key;

    @Indexed
    private String ring_no;
    private long len;
    @Indexed
    private String command;
    private byte[] content_in;
    private byte[] content_out;
    private Double longitude;
    private Double latitude;

    public P_Message() {
    }

    @PersistenceConstructor
    public P_Message(ObjectId id, Long msg_time, String key, String ring_no, long len, String command, byte[] content_in, byte[] content_out, Double longitude, Double latitude) {
        this.id = id;
        this.msg_time = msg_time;
        this.key = key;
        this.ring_no = ring_no;
        this.len = len;
        this.command = command;
        this.content_in = content_in;
        this.content_out = content_out;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public P_Message(String key, String ring_no, String command) {
        this.key = key;
        this.ring_no = ring_no;
        this.len = command.length();
        this.command = command;
    }

    public P_Message(String key, String ring_no, long len, String command) {
        this.msg_time = System.currentTimeMillis();
        this.key = key;
        this.ring_no = ring_no;
        this.len = len;
        this.command = command;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(Long msg_time) {
        this.msg_time = msg_time;
    }

    public String getRing_no() {
        return ring_no;
    }

    public void setRing_no(String ring_no) {
        this.ring_no = ring_no;
    }

    public long getLen() {
        return len;
    }

    public void setLen(long len) {
        this.len = len;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public byte[] getContent_in() {
        return content_in;
    }

    public void setContent_in(byte[] content_in) {
        this.content_in = content_in;
    }

    public byte[] getContent_out() {
        return content_out;
    }

    public void setContent_out(byte[] content_out) {
        this.content_out = content_out;
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
