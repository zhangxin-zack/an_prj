package com.scorer.gateway.route_filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class SaveGetInGatewayFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest host = exchange.getRequest().mutate()
                .header("Gateway_Request_In"," In:"+exchange.getRequest().getHeaders().get("host")+exchange.getRequest().getURI().getRawPath())
                .build();
        ServerWebExchange exchange_n = exchange.mutate().request(host).build();
        return chain.filter(exchange_n);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}