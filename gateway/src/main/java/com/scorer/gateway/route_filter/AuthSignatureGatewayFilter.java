package com.scorer.gateway.route_filter;

import com.scorer.tools.EncryptHead;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class AuthSignatureGatewayFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        EncryptHead encryptHead = new EncryptHead();
        ServerHttpRequest host = exchange.getRequest().mutate()
                .header("Gateway_str_next", encryptHead.str)
                .header("Gateway_enStr_next", encryptHead.enStr)
                .build();
        ServerWebExchange exchange_n = exchange.mutate().request(host).build();
        return chain.filter(exchange_n);
    }

    @Override
    public int getOrder() {
        //不能放最后一级
        return Ordered.LOWEST_PRECEDENCE-100;
    }
}