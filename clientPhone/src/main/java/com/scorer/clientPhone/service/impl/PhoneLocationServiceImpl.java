package com.scorer.clientPhone.service.impl;

import com.scorer.clientPhone.dao.mysql_dao1.PhoneLocationDao;
import com.scorer.clientPhone.entity.PhoneArea;
import com.scorer.clientPhone.entity.RingLocationInfo;
import com.scorer.clientPhone.netty.P_Message;
import com.scorer.clientPhone.service.PhoneLocationService;
import com.scorer.clientPhone.values.DefaultInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("phoneLocationService")
public class PhoneLocationServiceImpl implements PhoneLocationService {

    @Resource
    private PhoneLocationDao phoneLocationDao;

    @Override
    public RingLocationInfo GetLocationByMSG(P_Message p_message) {
        return null;
    }

    @Override
    public Boolean CheckArea(PhoneArea phoneArea, RingLocationInfo ringLocationInfo) {
        for (PhoneArea.AreaLoc safeArea : phoneArea.getSafeAreaList()) {
            double distance = GetDistance(safeArea.getLongitude(), safeArea.getLatitude(), ringLocationInfo.getLongitude(), ringLocationInfo.getLatitude());
            if (distance < safeArea.getRadius()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void SaveSafeAreaAlarm(String deviceInfo, Boolean isSafe, RingLocationInfo ringLocationInfo) {
        phoneLocationDao.SaveSafeAreaAlarm(deviceInfo, isSafe ? 1 : 2, ringLocationInfo);
    }

    @Override
    public RingLocationInfo GetSchoolArea(String ring_no) {
        return phoneLocationDao.GetSchoolArea(ring_no);
    }

    @Override
    public Boolean CheckOneArea(RingLocationInfo location1, RingLocationInfo location2) {
        double distance = GetDistance(location1.getLongitude(), location1.getLatitude(), location2.getLongitude(), location2.getLatitude());
        return distance< DefaultInfo.schoolR;
    }

    @Override
    public void SavePhoneSchool(String deviceInfo, Boolean isAtSchool, RingLocationInfo areaLoc) {

    }

    @Override
    public void SavePhoneHeart(P_Message p_message, int heart) {
        phoneLocationDao.SavePhoneHeart(p_message, heart);
    }

    @Override
    public void SavePhoneTemp(P_Message p_message, int temp) {
        phoneLocationDao.SavePhoneTemp(p_message, temp);
    }

    private static double GetDistance(double long1, double lat1, double long2, double lat2) {
        double a, b, R;
        R = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
        return d;
    }


}
