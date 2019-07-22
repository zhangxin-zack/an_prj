package com.scorer.feign._WebSocket;

import com.scorer.feign.tools.TestObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import javax.annotation.Resource;
import java.util.Objects;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig_Stomp implements WebSocketMessageBrokerConfigurer {

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private MyWebSocketInterceptor_Stomp interceptor;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/connect_point")
                .addInterceptors(interceptor)
                .setAllowedOrigins("*")
                .withSockJS();//withSockJS允许客户端利用sockjs进行浏览器兼容性处理,此时APP直接连接受阻
        stompEndpointRegistry.addEndpoint("/app_webSocket")
                .addInterceptors(interceptor);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");                  //设置服务器广播消息的基础路径(与@SendTo的前缀必须保持一致)
//        registry.setApplicationDestinationPrefixes("/app");                         //设置客户端订阅消息的基础路径(就是设置@MessageMapping的前缀)
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptorAdapter() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //1. 判断是否首次连接请求
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    //2. 验证是否登录
                    return message;
                }
                if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
                    //区分订阅地址(特殊地址处理)
                    String destination = accessor.getDestination();
                    if (destination.startsWith("/topic/GetMatchTimeControl/season_id") || destination.startsWith("/topic/GetMatchShowList/season_id")) {
                        //token校验
                        if ((accessor.containsNativeHeader("uid") && accessor.containsNativeHeader("token"))) {
                            try {
                                String uid = accessor.getNativeHeader("uid").get(0);
                                String token = accessor.getNativeHeader("token").get(0);
                                ValueOperations<String, String> operation = redisTemplate.opsForValue();
                                String uid_token = operation.get("token");
                                //暂时不启用
                                uid_token = "token123";
                                if (TestObject.noneEmpty(uid, token, uid_token) && Objects.equals(uid_token, token)) {
                                    return message;
                                }
                                return null;
                            } catch (Exception e) {
                                return null;
                            }
                        }
                        return null;
                    } else {
                        return message;
                    }
                }
                //其他信息直接发送
                return message;
            }
        });
    }

}