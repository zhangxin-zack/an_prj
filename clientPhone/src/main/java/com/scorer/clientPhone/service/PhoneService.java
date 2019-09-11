package com.scorer.clientPhone.service;

import com.scorer.clientPhone.entity.PhoneArea;
import com.scorer.clientPhone.entity.PhoneSettings;
import com.scorer.clientPhone.entity.Student;
import com.scorer.clientPhone.netty.P_Message;
import io.netty.channel.Channel;

import java.util.List;
import java.util.Map;

public interface PhoneService {
    void SavePhoneArea(PhoneArea phoneArea);

    void SavePhoneSettings(PhoneSettings phoneSettings);

    PhoneSettings LoadPhoneSettings(String ring_no);

    PhoneArea LoadPhoneArea(String ring_no);

    void SendUnSettings(P_Message msg, Channel channel);

    Map GetLatelyInfo(String ring_no);

    List<P_Message> LocationHistory(String ring_no, Long start_time, String end_time);

    Student GetStudentInfoByRingNo(P_Message msg);
}
