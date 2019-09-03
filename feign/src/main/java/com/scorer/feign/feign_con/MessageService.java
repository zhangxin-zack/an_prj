package com.scorer.feign.feign_con;

import com.scorer.feign.entity.WSMessage;
import com.scorer.feign.values.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(value = "scorer-client")
public interface MessageService {

    @RequestMapping(value = "/EDU/Message/SendMSG", consumes = MediaType.APPLICATION_JSON_VALUE)
    WSMessage SaveMSG(@RequestBody WSMessage wsMessage);

    @RequestMapping(value = "/EDU/Message/GetOldMSG", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map GetOldMSG(PageBean page);
}
