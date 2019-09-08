package com.scorer.clientPhone.service;

import com.scorer.clientPhone.entity.PhoneArea;
import com.scorer.clientPhone.entity.RingLocationInfo;
import com.scorer.clientPhone.netty.P_Message;

public interface PhoneLocationService {
    RingLocationInfo GetLocationByMSG(P_Message p_message);

    Boolean CheckArea(PhoneArea phoneArea, RingLocationInfo areaLoc);

    void SaveSafeAreaAlarm(String deviceInfo, Boolean isSafe, RingLocationInfo nowLocation);

    RingLocationInfo GetSchoolArea(String deviceInfo);

    Boolean CheckOneArea(RingLocationInfo location1, RingLocationInfo location2);

    void SavePhoneSchool(String deviceInfo, Boolean isAtSchool, RingLocationInfo nowLocation);

    void SavePhoneHeart(P_Message p_message, int heart);

    void SavePhoneTemp(P_Message p_message, int temp);
}
