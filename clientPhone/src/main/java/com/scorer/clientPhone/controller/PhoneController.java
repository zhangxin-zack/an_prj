package com.scorer.clientPhone.controller;

import com.scorer.clientPhone.entity.PhoneArea;
import com.scorer.clientPhone.entity.PhoneSettings;
import com.scorer.clientPhone.netty.P_Message;
import com.scorer.clientPhone.service.PhoneService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/EDU/Phone/")
public class PhoneController {

    @Resource
    private PhoneService phoneService;

    @RequestMapping(value = "/SavePhoneSettings")
    public Map SavePhoneSettings(@RequestBody PhoneSettings phoneSettings) {
        Map<String, Object> rMap = new HashMap<>();
        phoneService.SavePhoneSettings(phoneSettings);
        rMap.put("result_code", 0);
        return rMap;
    }

    @RequestMapping(value = "/SavePhoneArea")
    public Map SavePhoneArea(@RequestBody PhoneArea phoneArea) {
        Map<String, Object> rMap = new HashMap<>();
        phoneService.SavePhoneArea(phoneArea);
        rMap.put("result_code", 0);
        return rMap;
    }

    @RequestMapping(value = "/LoadPhoneSettings")
    public Map LoadPhoneSettings(@RequestParam("ring_no") String ring_no) {
        Map<String, Object> rMap = new HashMap<>();
        PhoneSettings phoneSettings = phoneService.LoadPhoneSettings(ring_no);
        rMap.put("result_code", 0);
        rMap.put("result_data", phoneSettings);
        return rMap;
    }

    @RequestMapping(value = "/LoadPhoneArea")
    public Map LoadPhoneArea(@RequestParam("ring_no") String ring_no) {
        Map<String, Object> rMap = new HashMap<>();
        PhoneArea phoneArea = phoneService.LoadPhoneArea(ring_no);
        rMap.put("result_code", 0);
        rMap.put("result_data", phoneArea);
        return rMap;
    }

    @RequestMapping(value = "/GetLatelyInfo")
    public Map GetLatelyInfo(@RequestParam("ring_no") String ring_no) {
        Map<String, Object> rMap = new HashMap<>();
        Map latelyInfo = phoneService.GetLatelyInfo(ring_no);
        rMap.put("result_code", 0);
        rMap.put("result_data", latelyInfo);
        return rMap;
    }

    @RequestMapping(value = "/LocationHistory")
    public Map LocationHistory(@RequestParam("ring_no") String ring_no,
                               @RequestParam("start_time") Long start_time,
                               @RequestParam("end_time") String end_time) {
        Map<String, Object> rMap = new HashMap<>();
        List<P_Message> p_messageList = phoneService.LocationHistory(ring_no,start_time,end_time);
        rMap.put("result_code", 0);
        rMap.put("result_data", p_messageList);
        return rMap;
    }

}
