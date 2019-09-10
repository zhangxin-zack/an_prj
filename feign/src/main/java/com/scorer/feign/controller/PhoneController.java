package com.scorer.feign.controller;

import com.scorer.feign.entity.PhoneArea;
import com.scorer.feign.entity.PhoneSettings;
import com.scorer.feign.feign_con.PhoneService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/EDU/Phone/")
public class PhoneController {

    @Resource
    private PhoneService phoneService;

    @RequestMapping(value = "/SavePhoneSettings")
    public Map SavePhoneSettings(@RequestBody PhoneSettings phoneSettings) {
        return phoneService.SavePhoneSettings(phoneSettings);
    }

    @RequestMapping(value = "/SavePhoneArea")
    public Map SavePhoneArea(@RequestBody PhoneArea phoneArea) {
        return phoneService.SavePhoneArea(phoneArea);
    }

    @RequestMapping(value = "/LoadPhoneSettings")
    public Map LoadPhoneSettings(@RequestParam("ring_no") String ring_no) {
        return phoneService.LoadPhoneSettings(ring_no);
    }

    @RequestMapping(value = "/LoadPhoneArea")
    public Map LoadPhoneArea(@RequestParam("ring_no") String ring_no) {
        return phoneService.LoadPhoneArea(ring_no);
    }

    @RequestMapping(value = "/GetLatelyInfo")
    public Map GetLatelyInfo(@RequestParam("ring_no") String ring_no) {
        return phoneService.GetLatelyInfo(ring_no);
    }

    @RequestMapping(value = "/LocationHistory")
    public Map LocationHistory(@RequestParam("ring_no") String ring_no,
                               @RequestParam("start_time") Long start_time,
                               @RequestParam("end_time") String end_time) {
        return phoneService.LocationHistory(ring_no, start_time, end_time);
    }


    @RequestMapping(value = "/GetMaxHeart")
    public Map GetMaxHeart() {
        return phoneService.GetMaxHeart();
    }

    @RequestMapping(value = "/SetMaxHeart")
    public Map SetMaxHeart(@RequestParam("maxHeart") Integer maxHeart) {
        return phoneService.SetMaxHeart(maxHeart);
    }

    @RequestMapping(value = "/GetMaxTemp")
    public Map GetMaxTemp() {
        return phoneService.GetMaxTemp();
    }

    @RequestMapping(value = "/SetMaxTemp")
    public Map SetMaxTemp(@RequestParam("maxTemp") Integer maxTemp) {
        return phoneService.SetMaxTemp(maxTemp);
    }

    @RequestMapping(value = "/GetSchoolR")
    public Map GetSchoolR() {
        return phoneService.GetSchoolR();
    }

    @RequestMapping(value = "/SetSchoolR")
    public Map SetSchoolR(@RequestParam("schoolR") Double schoolR) {
        return phoneService.SetSchoolR(schoolR);
    }
}
