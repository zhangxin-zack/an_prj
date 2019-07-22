package com.scorer.feign._WebSocket;

import com.google.gson.Gson;
import com.scorer.feign.tools.TestObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WebSocketPushHandler implements WebSocketHandler {

    //用户列表，聊天用
    private static final List<WebSocketSession> users = new ArrayList<>();
    //订阅用户列表
    private static final Map<String,List<WebSocketSession>> subscribers = new HashMap<>();
    //订阅用户关系表
    private static final Map<WebSocketSession,String> subscriber_key = new HashMap<>();

    // 用户进入系统监听
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        users.add(session);
        System.out.println("成功进入了系统。。。");
        System.out.println("session.attributes--->"+new Gson().toJson(session.getAttributes()));
        //订阅话题
        if(!TestObject.isEmpty(session.getAttributes().get("topic"))){
            subscribers.putIfAbsent(String.valueOf(session.getAttributes().get("topic")),new ArrayList<>());
            subscribers.get(String.valueOf(session.getAttributes().get("topic"))).add(session);
            subscriber_key.put(session,String.valueOf(session.getAttributes().get("topic")));
        }
        sendMessageToAllUsers(new TextMessage("欢迎连接"));
    }

    //收到消息转发
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 将消息进行转化，因为是消息是json数据，可能里面包含了发送给某个人的信息，所以需要用json相关的工具类处理之后再封装成TextMessage，
        // 我这儿并没有做处理，消息的封装格式一般有{from:xxxx,to:xxxxx,msg:xxxxx}，来自哪里，发送给谁，什么消息等等
        System.out.println(new Gson().toJson(message.getPayload()));
        // 给所有用户群发消息
        //sendMessagesToUsers(msg);
        // 给指定用户群发消息
        //sendMessageToUser(userId, msg);

    }

    // 后台错误信息处理方法
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    // 用户退出后的处理，要将用户信息从websocket的session中remove掉，这样用户就处于离线状态了，也不会占用系统资源
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        users.remove(session);
        if(!TestObject.isEmpty(subscribers.get(subscriber_key.get(session)))){
            subscribers.get(subscriber_key.get(session)).remove(session);
        }
        subscriber_key.remove(session);
        System.out.println("安全退出了系统");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

     //给所有的用户发送消息
    public void sendMessageToAllUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                // isOpen()在线就发送
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //发送消息给指定的用户
    public void sendMessageToUser(String userId, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("").equals(userId)) {
                try {
                    // isOpen()在线就发送
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //发送消息给订阅用户
    public void sendMessageToSubscribers(String topic,final String content){
        if(!TestObject.isEmpty(subscribers.get(topic))){
            for(final WebSocketSession user : subscribers.get(topic)){
                new Thread(() -> {
                    try {
                        if (user.isOpen()) {
                            user.sendMessage(new TextMessage(content));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

}