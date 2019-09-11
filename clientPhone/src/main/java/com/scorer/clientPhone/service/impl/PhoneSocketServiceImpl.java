package com.scorer.clientPhone.service.impl;

import com.scorer.clientPhone._Steam.TopicHomeMSG_Out;
import com.scorer.clientPhone.entity.PhoneArea;
import com.scorer.clientPhone.entity.RingLocationInfo;
import com.scorer.clientPhone.entity.Student;
import com.scorer.clientPhone.entity.WSMessage;
import com.scorer.clientPhone.netty.P_Message;
import com.scorer.clientPhone.netty.WatchServerNIO;
import com.scorer.clientPhone.service.PhoneLocationService;
import com.scorer.clientPhone.service.PhoneService;
import com.scorer.clientPhone.service.PhoneSocketService;
import com.scorer.clientPhone.tools.HuaWeiOBS;
import com.scorer.clientPhone.values.DefaultInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service("phoneSocketService")
@EnableBinding({TopicHomeMSG_Out.class})
public class PhoneSocketServiceImpl implements PhoneSocketService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Resource
    private PhoneLocationService phoneLocationService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private PhoneService phoneService;
    @Resource
    private DefaultInfo defaultInfo;
    @Resource
    private TopicHomeMSG_Out topicHomeMSG_out;


    @Override
    public void ReceiveMSG(P_Message msg) {
        switch (msg.getCommand()) {
            case "LK":
                BackMSG(msg);
                break;
            case "LGZONE":
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,yyyy-MM-dd");
                String msgBack = "+8" + "," + sdf.format(new Date());
                msg.setLen(msgBack.length());
                msg.setContent_out(msgBack.getBytes());
                BackMSG(msg);
                break;
            case "TK":
                msg.setLen(4);
                msg.setContent_out("TK,1".getBytes());
                BackMSG(msg);
                SendMSGToHome(msg);
                break;
            case "img":
                msg.setLen(3);
                msg.setContent_out("img".getBytes());
                BackMSG(msg);
                break;
            case "temp":
                SavePhoneTemp(msg);
                msg.setLen(4);
                msg.setContent_out("temp".getBytes());
                BackMSG(msg);
                break;
            case "heart":
                SavePhoneHeart(msg);
                msg.setLen(5);
                msg.setContent_out("heart".getBytes());
                BackMSG(msg);
                break;
            case "UD":
                SavePhoneUD(msg);
                break;
            default:
                break;
        }
    }

    private void SendMSGToHome(P_Message msg) {
        if(msg.getContent_in()!=null&&msg.getContent_in().length>0){
            Student student = phoneService.GetStudentInfoByRingNo(msg);
            if(student!=null){
                WSMessage wsMessage = new WSMessage();
                wsMessage.setFrom_student_id(student.getId().intValue());
                wsMessage.setTo_home(1);
                wsMessage.setNickName(student.getNickname());
                String amrUrl = HuaWeiOBS.fileUploadAMR(msg.getContent_in());
                wsMessage.setMsg_type(3);
                wsMessage.setMsg_content(amrUrl);
                mongoTemplate.insert(wsMessage);
                this.topicHomeMSG_out.output().send(MessageBuilder.withPayload(wsMessage).build());
            }
        }
    }


    @Override
    public void SendUnSettings(P_Message msg, Channel channel) {
        phoneService.SendUnSettings(msg,channel);
    }

    private void SavePhoneTemp(P_Message p_message){
        P_Message p_message_saved = mongoTemplate.findOne(
                Query.query(Criteria
                        .where("ring_no").is(p_message.getRing_no())
                ).with(new Sort(Sort.Direction.DESC,"msg_time")).limit(1) ,P_Message.class);
        if(p_message_saved!=null){
            p_message.setLongitude(p_message_saved.getLongitude());
            p_message.setLatitude(p_message_saved.getLatitude());
        }
        mongoTemplate.insert(p_message);
        int temp = Integer.parseInt(new String(p_message.getContent_in()));
        if(temp> defaultInfo.maxTemp()){
            phoneLocationService.SavePhoneTemp(p_message,temp);
        }
    }

    private void SavePhoneHeart(P_Message p_message){
        P_Message p_message_saved = mongoTemplate.findOne(
                Query.query(Criteria
                        .where("ring_no").is(p_message.getRing_no())
                ).with(new Sort(Sort.Direction.DESC,"msg_time")).limit(1) ,P_Message.class);
        if(p_message_saved!=null){
            p_message.setLongitude(p_message_saved.getLongitude());
            p_message.setLatitude(p_message_saved.getLatitude());
        }
        mongoTemplate.insert(p_message);
        int heart = Integer.parseInt(new String(p_message.getContent_in()));
        if(heart> defaultInfo.maxHeart()){
            phoneLocationService.SavePhoneHeart(p_message,heart);
        }
    }

    private void SavePhoneUD(P_Message p_message) {
        RingLocationInfo nowLocation = phoneLocationService.GetLocationByMSG(p_message);
        p_message.setLatitude(nowLocation.getLatitude());
        p_message.setLongitude(nowLocation.getLongitude());
        mongoTemplate.insert(p_message);
        PhoneArea phoneArea = mongoTemplate.findOne(Query.query(Criteria.where("ring_no").is(p_message.getRing_no())), PhoneArea.class);
        //处理安全区
        Boolean isSafe = true;
        if (phoneArea != null && phoneArea.getSafeAreaList() != null && phoneArea.getSafeAreaList().size() > 0) {
            isSafe = phoneLocationService.CheckArea(phoneArea, nowLocation);
        }
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Boolean savedSafeInfo = (Boolean) valueOperations.get("RingIsSafe:" + p_message.getRing_no());
        if (savedSafeInfo == null || !Objects.equals(isSafe, savedSafeInfo)) {
            phoneLocationService.SaveSafeAreaAlarm(p_message.getRing_no(), isSafe, nowLocation);
        }
        //处理学校
        RingLocationInfo ringLocationInfo = phoneLocationService.GetSchoolArea(p_message.getRing_no());
        if (ringLocationInfo != null && ringLocationInfo.getLatitude() != null && ringLocationInfo.getLongitude() != null) {
            Boolean isAtSchool = phoneLocationService.CheckOneArea(ringLocationInfo, nowLocation);
            Boolean savedIsAtSchool = (Boolean) valueOperations.get("RingIsAtSchool:" + p_message.getRing_no());
            if (savedIsAtSchool == null || !Objects.equals(isAtSchool, savedIsAtSchool)) {
                phoneLocationService.SavePhoneSchool(p_message.getRing_no(), isAtSchool, nowLocation);
            }
        }
    }

    private void BackMSG(P_Message p_message) {
        WatchServerNIO.userDeviceChannel.get(p_message.getRing_no()).writeAndFlush(p_message).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }


}
