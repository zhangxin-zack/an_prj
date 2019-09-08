package com.scorer.clientPhone.service.impl;

import com.google.gson.Gson;
import com.scorer.clientPhone.dao.mysql_dao1.PhoneLocationDao;
import com.scorer.clientPhone.entity.GPSFromCell;
import com.scorer.clientPhone.entity.PhoneArea;
import com.scorer.clientPhone.entity.RingLocationInfo;
import com.scorer.clientPhone.netty.P_Message;
import com.scorer.clientPhone.service.PhoneLocationService;
import com.scorer.clientPhone.tools.HTTP_Tools;
import com.scorer.clientPhone.values.DefaultInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("phoneLocationService")
public class PhoneLocationServiceImpl implements PhoneLocationService {

    @Resource
    private PhoneLocationDao phoneLocationDao;

    @Override
    public RingLocationInfo GetLocationByMSG(P_Message p_message) {
        String content = new String(p_message.getContent_in());
        String[] split = content.split(",");
        //信号强度
        String cell_strong = split[11];
        //电量
        String battery = split[12];
        //基站个数
        int cell_count = Integer.parseInt(split[16]);
        if (cell_count > 0) {
            String cl = "";
            String wl = "";
            String cellBase = split[18] + "," + split[19];
            for (int i = 0; i < cell_count; i++) {
                cl += (i > 0 ? ";" : "") + cellBase + "," + split[20 + i * 3] + "," + split[21 + i * 3] + ",-" + split[22 + i * 3];
            }
            //wifi个数
            int wifiCount = 16 + 3 + cell_count * 3 + 1;
            int wifi_count = Integer.parseInt(split[wifiCount]);
            for (int i = 0; i < wifi_count; i++) {
                wl += (i > 0 ? ";" : "") + split[wifiCount + 2 + i * 3] + "," + split[wifiCount + 3 + i * 3];
            }
            String URL ="http://api.cellocation.com:81/loc/";
            Map<String,String> param = new HashMap<>();
            param.put("cl",cl);
            param.put("wl",wl);
            param.put("output","json");
            GPSFromCell gpsFromCell = new HTTP_Tools().OK_GetParam_Sync(URL,param, GPSFromCell.class);
            return new RingLocationInfo(gpsFromCell);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        String content = "090919,011400,V,0,0,0,0,0,0,0,0,100,100,0,0,0,4,255,460,0,28730,64177,158,28734,56464,130,28734,23163,128,28734,56464,128,1,a,88:25:93:ba:4f:46,-6";
        String[] split = content.split(",");
        System.out.println(new Gson().toJson(split));
        //信号强度
        String cell_strong = split[11];
        //电量
        String battery = split[12];
        //基站个数
        int cell_count = Integer.parseInt(split[16]);
        if (cell_count > 0) {
            String cl = "";
            String wl = "";
            String cellBase = split[18] + "," + split[19];
            for (int i = 0; i < cell_count; i++) {
                cl += (i > 0 ? ";" : "") + cellBase + "," + split[20 + i * 3] + "," + split[21 + i * 3] + ",-" + split[22 + i * 3];
            }
            System.out.println(cl);
            //wifi个数
            int wifiCount = 16 + 3 + cell_count * 3 + 1;
            int wifi_count = Integer.parseInt(split[wifiCount]);
            System.out.println(wifi_count);
            for (int i = 0; i < wifi_count; i++) {
                wl += (i > 0 ? ";" : "") + split[wifiCount + 2 + i * 3] + "," + split[wifiCount + 3 + i * 3];
                System.out.println(wl);
            }

            String URL ="http://api.cellocation.com:81/loc/";
            Map<String,String> param = new HashMap<>();
            param.put("cl",cl);
            param.put("wl",wl);
            param.put("output","json");
            GPSFromCell gpsFromCell = new HTTP_Tools().OK_GetParam_Sync(URL,param, GPSFromCell.class);
            System.out.println(new Gson().toJson(gpsFromCell));
        } else {
        }
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
        return distance < DefaultInfo.schoolR;
    }

    @Override
    public void SavePhoneSchool(String deviceInfo, Boolean isAtSchool, RingLocationInfo ringLocationInfo) {
        phoneLocationDao.SavePhoneSchool(deviceInfo, isAtSchool ? 1 : 2, ringLocationInfo);
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
