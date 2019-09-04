package com.scorer.client.controller;

import com.scorer.client.entity.WSMessage;
import com.scorer.client.service.MessageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class MessageController {

    @Resource
    private MessageService messageService;

    @RequestMapping(value = "/EDU/Message/SendMSG")
    public WSMessage SaveMSG(@RequestBody WSMessage wsMessage) {
        return messageService.SaveMSG(wsMessage);
    }

    @RequestMapping(value = "/EDU/Message/GetHomeMSG")
    public Map GetHomeMSG(@RequestParam(value = "time") Long time,
                          @RequestParam(value = "count") Integer count,
                          @RequestParam(value = "student_id") Long student_id) {
        return messageService.GetHomeMSG(time, count, student_id);
    }

    @RequestMapping(value = "/EDU/Message/GetClassMSG")
    public Map GetClassMSG(@RequestParam(value = "time") Long time,
                           @RequestParam(value = "count") Integer count,
                           @RequestParam(value = "class_id") Long class_id) {
        return messageService.GetClassMSG(time, count, class_id);
    }

    @RequestMapping(value = "/EDU/Message/InviteUser")
    public Map InviteUser(@RequestParam(value = "nick_name") String nick_name,
                          @RequestParam(value = "phone") String phone,
                          @RequestParam(value = "student_id") Long student_id) {
        return messageService.InviteUser(nick_name,phone,student_id);
    }

    @RequestMapping(value = "/EDU/Message/ListHomeUser")
    public Map ListHomeUser(@RequestParam(value = "student_id") Integer student_id) {
        return messageService.ListHomeUser(student_id);
    }

    @RequestMapping(value = "/EDU/Message/ListClassUser")
    public Map ListClassUser(@RequestParam(value = "class_id") Integer class_id) {
        return messageService.ListClassUser(class_id);
    }

    @RequestMapping(value = "/EDU/Message/KickUser")
    public Map KickUser(@RequestParam(value = "uid") Integer uid,
                        @RequestParam(value = "student_id") Integer student_id) {
        return messageService.KickUser(uid,student_id);
    }

}
