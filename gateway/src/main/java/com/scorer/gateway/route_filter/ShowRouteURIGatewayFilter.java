package com.scorer.gateway.route_filter;

import com.google.gson.Gson;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

public class ShowRouteURIGatewayFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String gateway_request_in = headers.getFirst("Gateway_Request_In");
            String in = String.join(" ",gateway_request_in);
            System.out.println(in + " -->Out: " + exchange.getRequest().getURI().getRawPath() + " -->Time:" + new Date());
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}