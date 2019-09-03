package com.scorer.client.controller;

import com.scorer.client.entity.WSMessage;
import com.scorer.client.service.MessageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageController {

    @Resource
    private MessageService messageService;

    @RequestMapping(value = "/EDU/Message/SendMSG")
    public WSMessage SaveMSG(@RequestBody WSMessage wsMessage) {
        return messageService.SaveMSG(wsMessage);

    }
}
