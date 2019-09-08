package com.scorer.clientPhone.dao.mysql_dao1;

import com.scorer.clientPhone.entity.RingLocationInfo;
import com.scorer.clientPhone.netty.P_Message;
import org.apache.ibatis.annotations.Param;

public interface PhoneLocationDao {
    void SaveSafeAreaAlarm(@Param("ring_no") String deviceInfo,
                           @Param("isSafe") Integer isSafe,
                           @Param("ringLocationInfo") RingLocationInfo ringLocationInfo);

    RingLocationInfo GetSchoolArea(String ring_no);

    void SavePhoneHeart(@Param("p_message") P_Message p_message,
                        @Param("heart") int heart);

    void SavePhoneTemp(@Param("p_message") P_Message p_message,
                       @Param("temp") int temp);

    void SavePhoneSchool(@Param("ring_no") String deviceInfo,
                         @Param("isSafe") Integer adSchool,
                         @Param("ringLocationInfo") RingLocationInfo ringLocationInfo);
}
