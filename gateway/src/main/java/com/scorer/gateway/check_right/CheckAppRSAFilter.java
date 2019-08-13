package com.scorer.gateway.check_right;

import com.google.gson.Gson;
import com.scorer.gateway._Excptions.AppCheckFailException;
import com.scorer.gateway.model.RequestRSA;
import com.scorer.gateway.value.ResultMap;
import com.scorer.gateway.tools.RedisInFilter;
import com.scorer.tools.ScorerURI;
import com.scorer.tools.TestObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CheckAppRSAFilter implements GatewayFilter, Ordered {

    @Value("${gateway.value.isDev}")
    private boolean isDev;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uri = exchange.getRequest().getURI().getRawPath();
        if (ScorerURI.NeedAppRSAToken.contains(uri)) {
            if(isDev){
                return chain.filter(exchange);
            }
            HttpHeaders headers = exchange.getRequest().getHeaders();
            try {
                //APP校验-RSA关闭(可能需要其他方式)
//                String encStr = EncryptTool.decodeAPP(headers.getFirst("encStr"));
                String encStr = headers.getFirst("encStr");
                Long timestamp = Long.valueOf(Objects.requireNonNull(headers.getFirst("timestamp")));
                String nonceStr = headers.getFirst("nonceStr");
                RequestRSA requestRSA = new Gson().fromJson(encStr, RequestRSA.class);
                if (TestObject.noneEmpty(timestamp, nonceStr, requestRSA)
                        && RedisInFilter.CheckNonceStr(nonceStr)
                        && Math.abs(System.currentTimeMillis() - timestamp) < 60 * 60 * 1000
                        && timestamp.equals(requestRSA.getTimestamp())
                        && nonceStr.equals(requestRSA.getNonceStr())) {
                    System.out.println("RSA SUCCESS!");
                    return chain.filter(exchange);
                }else{
                    throw new AppCheckFailException("RSA FAIL");
                }
            } catch (Exception ignored) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("result",9999);
                responseMap.put("msg", ResultMap.Result.get(responseMap.get("result")));
                byte[] bytes = new Gson().toJson(responseMap).getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                return exchange.getResponse().writeWith(Flux.just(buffer));
            }
        }else{
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -100;
    }

}