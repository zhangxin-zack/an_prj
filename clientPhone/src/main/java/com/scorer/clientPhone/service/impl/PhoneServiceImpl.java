package com.scorer.clientPhone.service.impl;

import com.scorer.clientPhone.entity.PhoneArea;
import com.scorer.clientPhone.entity.PhoneSettings;
import com.scorer.clientPhone.netty.P_Message;
import com.scorer.clientPhone.netty.WatchServerNIO;
import com.scorer.clientPhone.service.PhoneService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("phoneService")
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void SavePhoneArea(PhoneArea phoneArea) {
        mongoTemplate.save(phoneArea);
    }

    @Override
    public void SavePhoneSettings(PhoneSettings phoneSettings) {
        mongoTemplate.save(phoneSettings);
        Channel channel = WatchServerNIO.userDeviceChannel.get(phoneSettings.getRing_no());
        if (channel != null) {
            SetPhoneContact(phoneSettings, channel);
        } else {
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            valueOperations.set("SaveRingSettings:" + phoneSettings.getRing_no(), phoneSettings);
        }
    }

    @Override
    public PhoneSettings LoadPhoneSettings(String ring_no) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("ring_no").is(ring_no)), PhoneSettings.class);
    }

    @Override
    public PhoneArea LoadPhoneArea(String ring_no) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("ring_no").is(ring_no)), PhoneArea.class);
    }

    @Override
    public void SendUnSettings(P_Message msg, Channel channel) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        PhoneSettings phoneSettings = (PhoneSettings) valueOperations.get("SaveRingSettings:" + msg.getRing_no());
        if(phoneSettings!=null){
            SetPhoneContact(phoneSettings, channel);
        }
    }

    private void SetPhoneContact(PhoneSettings phoneSettings, Channel channel) {
        if (phoneSettings.getContacts_str() != null) {
            P_Message p_message = new P_Message(WatchServerNIO.userChannelKey.get(channel), phoneSettings.getRing_no(), "PHL," + phoneSettings.getContacts_str());
            SendSettings(channel, p_message, phoneSettings);
        }
        if (phoneSettings.getRemind_str() != null) {
            P_Message p_message = new P_Message(WatchServerNIO.userChannelKey.get(channel), phoneSettings.getRing_no(), "REMIND," + phoneSettings.getContacts_str());
            SendSettings(channel, p_message, phoneSettings);
        }
        if (phoneSettings.getSilence_time_str() != null) {
            P_Message p_message = new P_Message(WatchServerNIO.userChannelKey.get(channel), phoneSettings.getRing_no(), "SILENCETIME," + phoneSettings.getContacts_str());
            SendSettings(channel, p_message, phoneSettings);
        }
    }

    private void SendSettings(Channel channel, P_Message p_message, PhoneSettings phoneSettings) {
        channel.writeAndFlush(p_message).addListener((ChannelFutureListener) future -> {
            if (!future.isSuccess()) {
                ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
                valueOperations.set("SaveRingSettings:" + phoneSettings.getRing_no(), phoneSettings);
                future.channel().close();
            }else{
                redisTemplate.delete("SaveRingSettings:" + phoneSettings.getRing_no());
            }
        });
    }

}
