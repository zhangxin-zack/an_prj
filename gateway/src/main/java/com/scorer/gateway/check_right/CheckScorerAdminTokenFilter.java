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
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CheckScorerAdminTokenFilter implements GatewayFilter, Ordered {

    @Value("${gateway.value.isDev}")
    private boolean isDev;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uri = exchange.getRequest().getURI().getRawPath();
        if (ScorerURI.NeedAppCheckScorerAdminToken.contains(uri)) {
            if (isDev) {
                return chain.filter(exchange);
            }
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            MultiValueMap<String, HttpCookie> cookies = exchange.getRequest().getCookies();
            String admin_id = null, token = null;
            try {
                admin_id = Objects.requireNonNull(cookies.getFirst("cookie_admin_id_h5")).getValue();
                token = Objects.requireNonNull(cookies.getFirst("cookie_admin_token_h5")).getValue();
            } catch (NullPointerException ignored) {
            }
            String admin_id_token = operations.get("admin_id_token_h5" + admin_id);
            if (TestObject.noneEmpty(token, admin_id, admin_id_token) && token.equals(admin_id_token)) {
                operations.set("admin_id_token_h5" + admin_id, token, 24 * 60 * 60, TimeUnit.SECONDS);
                System.out.println("CHECK TOKEN ADMIN SUCCESS!");
                return chain.filter(exchange);
            } else {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("result", 9999);
                responseMap.put("msg", ResultMap.Result.get(responseMap.get("result")));
                byte[] bytes = new Gson().toJson(responseMap).getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                System.err.println("CHECK TOKEN ADMIN FAILED!");
                return exchange.getResponse().writeWith(Flux.just(buffer));
            }
        } else {
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -98;
    }

}