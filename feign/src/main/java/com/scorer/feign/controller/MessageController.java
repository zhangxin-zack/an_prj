package com.scorer.feign.controller;

import com.scorer.feign._WebSocket.WebSocketPushHandler;
import com.scorer.feign.entity.WSMessage;
import com.scorer.feign.feign_con.ClassesService;
import com.scorer.feign.feign_con.MessageService;
import com.scorer.feign.values.PageBean;
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
        if(wsMessage.getTo_home()==1){
            uids.addAll(classesService.getListStudentParent(wsMessage.getFrom_student_id().longValue()));
        }else{
            for (Integer classId:wsMessage.getTo_classes()) {
                uids.addAll(classesService.getListClassStudentParent(classId.longValue()));
            }
        }
        for(Long to_uid:uids){
            webSocketPushHandler.sendMessageToUser(to_uid.intValue(), wsMessage);
        }
        rMap.put("result_code", 0);
        return rMap;
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

    @RequestMapping(value = "/EDU/Message/GetUserOneMessage")
    public Map GetUserOneMessage(@RequestParam(value = "uid") Integer uid) {
        return messageService.GetUserOneMessage(uid);
    }

}
