package com.scorer.feign._WebSocket;

import com.google.gson.Gson;
import com.scorer.feign.entity.WSMessage;
import com.scorer.feign.feign_con.Customer_Service;
import com.scorer.feign.tools.TestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WebSocketPushHandler implements WebSocketHandler {

    @Autowired
    private Customer_Service customer_service;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    //用户列表，聊天用
    private static final Map<String, WebSocketSession> userMap = new HashMap<>();
    private static Gson gson = new Gson();

    // 用户进入系统监听
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        final String uid = String.valueOf(session.getAttributes().get("uid"));
        System.out.println("用户[" + uid + "]成功进入了系统");
        //获取该用户所有未读消息
//        final List<WSMessage> wsMessageList = customer_service.GetAllUserUnreadMsg(Integer.valueOf(uid));
//        new Thread(() -> {
//            for (WSMessage wsMessage : wsMessageList) {
//                sendMessageToUser(Integer.valueOf(uid), wsMessage);
//            }
//        }).start();
        userMap.put(uid, session);
        System.out.println("session.attributes--->" + new Gson().toJson(session.getAttributes()));
    }


    //收到心跳消息
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("session_attributes:" + gson.toJson(session.getAttributes()));
        System.out.println("user_send_msg:" + gson.toJson(message.getPayload()));
        if (CheckSessionToken(session)) {
            System.out.println("用户SESSION的TOKEN验证有效");
        } else {
            System.out.println("用户SESSION的TOKEN验证失效");
            ForceOutSession(session);
        }
    }

    // 后台错误信息处理方法
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    }

    private void ForceOutSession(WebSocketSession session) throws IOException {
        String uid = String.valueOf(session.getAttributes().get("uid"));
        if (!TestObject.isEmpty(uid)) {
            WebSocketSession saved_session = userMap.get(uid);
            if (saved_session != null) {
                if (session.getAttributes().get("token").equals(saved_session.getAttributes().get("token"))) {
                    userMap.remove(uid);
                }
            }
            System.out.println("用户[" + uid + "]安全退出了系统");
        }
        if (session.isOpen()) {
            session.close();
        }
    }

    private boolean CheckSessionToken(WebSocketSession session) throws IOException {
        String uid = String.valueOf(session.getAttributes().get("uid"));
        if (!TestObject.isEmpty(uid)) {
            return CheckToke_User(uid, String.valueOf(session.getAttributes().get("token")));
        }
        return false;
    }

    private boolean CheckToke_User(String uid, String token) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String uid_token = operations.get("uid_app_token:" + uid);
        return TestObject.noneEmpty(uid, token, uid_token) && token.equals(uid_token);
    }

    // 用户退出后的处理，要将用户信息从websocket的session中remove掉，这样用户就处于离线状态了，也不会占用系统资源
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        ForceOutSession(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    //发送消息给指定的用户
    public void sendMessageToUser(Integer uid_i, WSMessage message) {
        String uid = String.valueOf(uid_i);
        if (!TestObject.isEmpty(uid)) {
            WebSocketSession user = userMap.get(uid);
            // isOpen()在线就发送
            if (user != null && user.isOpen()) {
                try {
                    user.sendMessage(new TextMessage(new Gson().toJson(message)));
//                    customer_service.SetReadMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}