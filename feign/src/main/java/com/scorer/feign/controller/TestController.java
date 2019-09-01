package com.scorer.feign.controller;

import com.scorer.feign._WebSocket.WebSocketPushHandler;
import com.scorer.feign.entity.WSMessage;
import com.scorer.feign.feign_con.TestFeignCon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Resource
    private WebSocketPushHandler webSocketPushHandler;
    @Resource
    private TestFeignCon testFeignCon;

    @RequestMapping(value = "/Test/TestNetSRC")
    public ResponseEntity<byte[]> TestNetSRC() {
        return testFeignCon.TestNetSRC();
    }

    @RequestMapping(value = "/Test/TestUpload")
    public Map TestUpload(@RequestPart(value = "photo_file", required = false) MultipartFile photo_file) {
        return testFeignCon.TestUpload(photo_file);
    }

    @RequestMapping(value = "/EDU/Test/TestSendMSG")
    public Map TestSendMSG(@RequestParam(value = "uid") Integer uid,
                           @RequestParam(value = "content") String content) {
        Map<String, Object> rMap = new HashMap<>();
        WSMessage wsMessage = new WSMessage();
        wsMessage.setTo_uid(uid);
        wsMessage.setMsg_content(content);
        webSocketPushHandler.sendMessageToUser(uid, wsMessage);
        rMap.put("result", 1);
        return rMap;
    }


}