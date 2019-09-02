package com.scorer.feign._WebSocket;

import com.google.gson.Gson;
import com.scorer.feign.tools.TestObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Map;

/**
 * 此类用来获取登录用户信息并交由websocket管理
 */
//@Component
public class MyWebSocketInterceptor implements HandshakeInterceptor {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    //在握手之前执行该方法, 继续握手返回true, 中断握手返回false. 通过attributes参数设置WebSocketSession的属性
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        // 将ServerHttpRequest转换成request请求相关的类，用来获取request域中的用户信息
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            System.out.println("headers--->" + new Gson().toJson(servletRequest.getHeaders()));
            HttpServletRequest httpRequest = servletRequest.getServletRequest();
            String aid = httpRequest.getParameter("aid");
            if (TestObject.isEmpty(aid)) {
                String uid = httpRequest.getHeader("uid");
                String token = httpRequest.getHeader("token");
                if (TestObject.noneEmpty(uid, token) && CheckToke_User(uid, token)) {
                    attributes.put("uid", uid);
                    attributes.put("token", token);
                    String nick_name = URLDecoder.decode(httpRequest.getHeader("name"),"UTF-8");
                    attributes.put("nick_name", nick_name);
                    System.out.println("连接成功了---before");
                    return true;
                }
            }
        }
        System.out.println("连接失败了---before");
        return false;
    }

    private boolean CheckToke_User(String uid, String token) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String uid_token = operations.get("uid_token_wx_app:" + uid);
        return TestObject.noneEmpty(uid, token, uid_token) && token.equals(uid_token);
    }

    //在握手之后执行该方法. 无论是否握手成功都指明了响应状态码和相应头.
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        // TODO Auto-generated method stub
    }

}