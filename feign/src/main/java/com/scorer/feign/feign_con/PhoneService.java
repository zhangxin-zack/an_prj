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
}
