package com.scorer.gateway.route_filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.isAlreadyRouted;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.setAlreadyRouted;

public class WebsocketRoutingFilter implements GlobalFilter, Ordered {

    public static final String SEC_WEBSOCKET_PROTOCOL = "Sec-WebSocket-Protocol";
    private final WebSocketClient webSocketClient;
    private final WebSocketService webSocketService;

    public WebsocketRoutingFilter(WebSocketClient webSocketClient) {
        this(webSocketClient, new HandshakeWebSocketService());
    }

    public WebsocketRoutingFilter(WebSocketClient webSocketClient,
                                  WebSocketService webSocketService) {
        this.webSocketClient = webSocketClient;
        this.webSocketService = webSocketService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获得 requestUrl
        URI requestUrl = exchange.getRequiredAttribute(GATEWAY_REQUEST_URL_ATTR);

        // 判断是否能够处理
        String scheme = requestUrl.getScheme();
        if (isAlreadyRouted(exchange) || (!scheme.equals("ws") && !scheme.equals("wss"))) {
            return chain.filter(exchange);
        }

        // 设置已经路由
        setAlreadyRouted(exchange);

        // 处理连接请求
        return this.webSocketService.handleRequest(exchange,
                new ProxyWebSocketHandler(requestUrl, this.webSocketClient, exchange.getRequest().getHeaders()));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    private static class ProxyWebSocketHandler implements WebSocketHandler {

        private final WebSocketClient client;
        private final URI url;
        private final HttpHeaders headers;
        private final List<String> subProtocols;

        public ProxyWebSocketHandler(URI url, WebSocketClient client, HttpHeaders headers) {
            this.client = client;
            this.url = url;
            this.headers = new HttpHeaders();
            headers.forEach((key, value) -> {
                if (!key.toLowerCase().startsWith("sec-websocket")
                        && !key.equalsIgnoreCase("upgrade")
                        && !key.equalsIgnoreCase("connection")) {
                    this.headers.addAll(key, value);
                }
            });
            List<String> protocols = headers.get(SEC_WEBSOCKET_PROTOCOL);
            if (protocols != null) {
                this.subProtocols = protocols;
            } else {
                this.subProtocols = Collections.emptyList();
            }
        }

        @Override
        public Mono<Void> handle(WebSocketSession session) {
            // pass headers along so custom headers can be sent through
            return client.execute(url, this.headers, new WebSocketHandler() {
                @Override
                public Mono<Void> handle(WebSocketSession proxySession) {
                    // Use retain() for Reactor Netty
                    // 转发消息 客户端->后端服务
                    Mono<Void> proxySessionSend = proxySession
                            .send(session.receive().doOnNext(WebSocketMessage::retain));
                    // 转发消息 后端服务->客户端
                    // .log("proxySessionSend", Level.FINE);
                    Mono<Void> serverSessionSend = session
                            .send(proxySession.receive().doOnNext(WebSocketMessage::retain));
                    // .log("sessionSend", Level.FINE);
                    return Mono.when(proxySessionSend, serverSessionSend).then();
                }

                @Override
                public List<String> getSubProtocols() {
                    return ProxyWebSocketHandler.this.subProtocols;
                }

            });
        }
    }
}


