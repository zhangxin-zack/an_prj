package com.scorer.feign.controller;

import com.scorer.feign._WebSocket.WebSocketPushHandler;
import com.scorer.feign.entity.WSMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MessageController {

    @Resource
    private WebSocketPushHandler webSocketPushHandler;

    @RequestMapping(value = "/EDU/Message/TestSendMSG")
    public Map TestSendMSG(@RequestParam(value = "uid") Integer uid,
                           @RequestParam(value = "content") String content) {
        Map<String, Object> rMap = new HashMap<>();
        WSMessage wsMessage = new WSMessage();
        wsMessage.setMsg_content(content);
        webSocketPushHandler.sendMessageToUser(uid, wsMessage);
        rMap.put("result", 1);
        return rMap;
    }
}
