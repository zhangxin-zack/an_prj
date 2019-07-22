package com.scorer.feign._WebSocket;

import com.scorer.feign.tools.OpCookie;
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
import java.util.Map;
import java.util.Objects;

/**
 * 此类用来获取登录用户信息并交由websocket管理
 */
@Component
public class MyWebSocketInterceptor_Stomp implements HandshakeInterceptor {

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
            HttpServletRequest httpRequest = servletRequest.getServletRequest();
            String admin_id = OpCookie.getCookieByName(httpRequest, "cookie_admin_id_h5_jp");
            String token = OpCookie.getCookieByName(httpRequest, "cookie_admin_token_h5_jp");
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            String admin_id_token = operations.get("uid_token_admin_html_jp" + admin_id);
            if(TestObject.noneEmpty(admin_id,token,admin_id_token)&& Objects.equals(token,admin_id_token)){
                System.out.println("连接到我了---before---checked");
                return true;
            }
            System.out.println("连接到我了---before--unchecked");
            return true;
        }
        System.out.println("连接失败了---before");
        return false;
    }

    //在握手之后执行该方法. 无论是否握手成功都指明了响应状态码和相应头.
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        // TODO Auto-generated method stub

    }

}