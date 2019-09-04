package com.scorer.feign.feign_con;

import com.scorer.feign.entity.WSMessage;
import com.scorer.feign.values.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "scorer-client")
public interface MessageService {

    @RequestMapping(value = "/EDU/Message/SendMSG", consumes = MediaType.APPLICATION_JSON_VALUE)
    WSMessage SaveMSG(@RequestBody WSMessage wsMessage);


    @RequestMapping(value = "/EDU/Message/GetHomeMSG", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map GetHomeMSG(@RequestParam(value = "time") Long time,
                   @RequestParam(value = "count") Integer count,
                   @RequestParam(value = "student_id") Long student_id);

    @RequestMapping(value = "/EDU/Message/GetClassMSG", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map GetClassMSG(@RequestParam(value = "time") Long time,
                    @RequestParam(value = "count") Integer count,
                    @RequestParam(value = "class_id") Long class_id);

    @RequestMapping(value = "/EDU/Message/InviteUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map InviteUser(@RequestParam(value = "nick_name") String nick_name,
                   @RequestParam(value = "phone") String phone,
                   @RequestParam(value = "student_id") Long student_id);

    @RequestMapping(value = "/EDU/Message/ListHomeUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map ListHomeUser(@RequestParam(value = "student_id") Integer student_id);

    @RequestMapping(value = "/EDU/Message/ListClassUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map ListClassUser(@RequestParam(value = "class_id") Integer class_id);

    @RequestMapping(value = "/EDU/Message/KickUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map KickUser(@RequestParam(value = "uid") Integer uid,
                 @RequestParam(value = "student_id") Integer student_id);
}
