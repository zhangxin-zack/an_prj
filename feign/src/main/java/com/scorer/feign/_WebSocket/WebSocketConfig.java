package com.scorer.feign._WebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebMvcConfigurer,WebSocketConfigurer {

    @Resource
    private WebSocketPushHandler handler;
    @Resource
    private MyWebSocketInterceptor interceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/webSocketServer")
                .addInterceptors(interceptor)
                .setAllowedOrigins("*");
        registry.addHandler(handler, "/sockjs/webSocketServer")
                .addInterceptors(interceptor)
                .setAllowedOrigins("*")
                .withSockJS();
    }

    //下面是以bean注入的方式
//    @Bean
//    public WebSocketHandler WebSocketPushHandler() {
//        return new WebSocketPushHandler();
//    }

}