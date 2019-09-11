package com.scorer.feign._Stream;

import com.scorer.feign._WebSocket.WebSocketPushHandler;
import com.scorer.feign.entity.WSMessage;
import com.scorer.feign.feign_con.ClassesService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@EnableBinding({TopicHomeMSG_In.class})
public class SteamToWebSocket {

    @Resource
    private WebSocketPushHandler webSocketPushHandler;
    @Resource
    private ClassesService classesService;

    @StreamListener(TopicHomeMSG_In.TOPIC)
    public void receiveTopicHomeMSG(WSMessage wsMessage) {
        List<Long> uids = new ArrayList<>(classesService.getListStudentParent(wsMessage.getFrom_student_id().longValue()));
        for(Long to_uid:uids){
            webSocketPushHandler.sendMessageToUser(to_uid.intValue(), wsMessage);
        }
    }

}
