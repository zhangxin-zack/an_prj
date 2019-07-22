package com.scorer.gateway.check_right;

import com.google.gson.Gson;
import com.scorer.gateway.value.ResultMap;
import com.scorer.tools.ScorerURI;
import com.scorer.tools.TestObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CheckAppTokenFilter implements GatewayFilter, Ordered {

    @Value("${gateway.value.isDev}")
    private boolean isDev;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uri = exchange.getRequest().getURI().getRawPath();
        if (ScorerURI.NeedAppCheckAppToken.contains(uri)) {
            if (isDev) {
                return chain.filter(exchange);
            }
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String token = headers.getFirst("token");
            String uid = headers.getFirst("uid");
            String uid_token = operations.get("uid_token" + uid);
            if (TestObject.noneEmpty(token, uid, uid_token) && token.equals(uid_token)) {
                operations.set("uid_token" + uid, token, 15 * 24 * 60 * 60, TimeUnit.SECONDS);
                System.out.println("CHECK TOKEN APP SUCCESS!");
                return chain.filter(exchange);
            }else{
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("result",9999);
                responseMap.put("msg", ResultMap.Result.get(responseMap.get("result")));
                byte[] bytes = new Gson().toJson(responseMap).getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                System.err.println("CHECK TOKEN APP FAILED!");
                return exchange.getResponse().writeWith(Flux.just(buffer));
            }
        } else {
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -99;
    }

}