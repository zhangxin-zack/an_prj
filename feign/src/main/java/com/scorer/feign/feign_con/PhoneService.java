package com.scorer.feign.feign_con;

import com.scorer.feign.entity.PhoneArea;
import com.scorer.feign.entity.PhoneSettings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "scorer-clientPhone")
public interface PhoneService {

    @RequestMapping(value = "/EDU/Phone/SavePhoneSettings", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map SavePhoneSettings(@RequestBody PhoneSettings phoneSettings);

    @RequestMapping(value = "/EDU/Phone/SavePhoneArea", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map SavePhoneArea(@RequestBody PhoneArea phoneArea);

    @RequestMapping(value = "/EDU/Phone/LoadPhoneSettings", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map LoadPhoneSettings(@RequestParam(value = "ring_no") String ring_no);

    @RequestMapping(value = "/EDU/Phone/LoadPhoneArea", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map LoadPhoneArea(@RequestParam(value = "ring_no") String ring_no);

    @RequestMapping(value = "/EDU/Phone/GetLatelyInfo", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map GetLatelyInfo(@RequestParam("ring_no") String ring_no);

    @RequestMapping(value = "/EDU/Phone/LocationHistory", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map LocationHistory(@RequestParam("ring_no") String ring_no,
                        @RequestParam("start_time") Long start_time,
                        @RequestParam("end_time") String end_time);

    @RequestMapping(value = "/EDU/Phone/GetMaxHeart", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map GetMaxHeart();

    @RequestMapping(value = "/EDU/Phone/SetMaxHeart", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map SetMaxHeart(@RequestParam("maxHeart") Integer maxHeart);

    @RequestMapping(value = "/EDU/Phone/GetMaxTemp", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map GetMaxTemp();

    @RequestMapping(value = "/EDU/Phone/SetMaxTemp", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map SetMaxTemp(@RequestParam("maxTemp") Integer maxTemp);

    @RequestMapping(value = "/EDU/Phone/GetSchoolR", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map GetSchoolR();

    @RequestMapping(value = "/EDU/Phone/SetSchoolR", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map SetSchoolR(@RequestParam("schoolR") Double schoolR);
}
