package com.scorer.feign.controller;

import com.scorer.feign.entity.PhoneArea;
import com.scorer.feign.entity.PhoneSettings;
import com.scorer.feign.feign_con.PhoneService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
}
