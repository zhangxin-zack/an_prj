package com.scorer.feign.feign_con;

import com.scorer.feign.entity.WSMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "scorer-client")
public interface Customer_Service {

    @RequestMapping(value = "/EDU/Talk/GetAllUserUnreadMsg")
    List<WSMessage> GetAllUserUnreadMsg(Integer uid);

    @RequestMapping(value = "/EDU/Talk/SetReadMessage")
    void SetReadMessage(WSMessage message);

}
