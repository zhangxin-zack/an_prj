package com.scorer.feign.controller;

import com.scorer.feign._WebSocket.WebSocketPushHandler;
import com.scorer.feign.entity.WSMessage;
import com.scorer.feign.feign_con.ClassesService;
import com.scorer.feign.feign_con.MessageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessageController {

    @Resource
    private WebSocketPushHandler webSocketPushHandler;
    @Resource
    private ClassesService classesService;
    @Resource
    private MessageService messageService;

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

    @RequestMapping(value = "/EDU/Message/SendMSG")
    public Map SendMSG(@RequestBody WSMessage wsMessage) {
        Map<String, Object> rMap = new HashMap<>();
        wsMessage = messageService.SaveMSG(wsMessage);
        List<Long> uids=new ArrayList<>();
        if(wsMessage.getTo_home()==-1){
            uids.addAll(classesService.getListStudentParent(wsMessage.getFrom_student_id().longValue()));
        }else{
            for (Integer classId:wsMessage.getTo_classes()) {
                uids.addAll(classesService.getListClassStudentParent(classId.longValue()));
            }
        }
        for(Long to_uid:uids){
            webSocketPushHandler.sendMessageToUser(to_uid.intValue(), wsMessage);
        }
        rMap.put("result", 1);
        return rMap;
    }

}
