package com.scorer.gateway;

import com.scorer.gateway.check_right.CheckAppTokenFilter;
import com.scorer.gateway.check_right.CheckIsAppFilter;
import com.scorer.gateway.check_right.CheckScorerAdminTokenFilter;
import com.scorer.gateway.route_filter.SaveGetInGatewayFilter;
import com.scorer.gateway.route_filter.AuthSignatureGatewayFilter;
import com.scorer.gateway.route_filter.ShowRouteURIGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class RouteList {

    private SaveGetInGatewayFilter saveGetInGatewayFilter =new SaveGetInGatewayFilter();
    private AuthSignatureGatewayFilter authSignatureGatewayFilter =new AuthSignatureGatewayFilter();
    private ShowRouteURIGatewayFilter showRouteURIGatewayFilter =new ShowRouteURIGatewayFilter();

    private CheckAppTokenFilter checkAppTokenFilter =new CheckAppTokenFilter();
    private CheckScorerAdminTokenFilter checkScorerAdminTokenFilter =new CheckScorerAdminTokenFilter();
    private CheckIsAppFilter checkIsAppFilter =new CheckIsAppFilter();

    @Bean
    public RouteLocator scorerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/Scorer/**")
                        .filters(f -> f.filter(saveGetInGatewayFilter)
                                .filter(authSignatureGatewayFilter)
                                .filter(showRouteURIGatewayFilter)
                                .filter(checkAppTokenFilter)
                                .filter(checkScorerAdminTokenFilter)
                                .filter(checkIsAppFilter))
                        .uri("lb://scorer-feign")
                        .order(0))
                .build();
    }

    @Bean
    public RouteLocator testRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/testDownload")
                        .filters(f -> f.filter(saveGetInGatewayFilter)
                                .filter(authSignatureGatewayFilter)
                                .filter(showRouteURIGatewayFilter)
                                .rewritePath("/testDownload","/Test/TestNetSRC"))
                        .uri("lb://scorer-feign")
                        .order(1))
                .route(r -> r.path("/testUpload")
                        .filters(f -> f.filter(saveGetInGatewayFilter)
                                .filter(authSignatureGatewayFilter)
                                .filter(showRouteURIGatewayFilter)
                                .rewritePath("/testUpload","/Test/TestUpload"))
                        .uri("lb://scorer-feign")
                        .order(2))
                .build();
    }

    @Bean
    public RouteLocator customWSRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/connect_point/info/**")
                        .filters(f -> f.filter(saveGetInGatewayFilter)
                                .filter(authSignatureGatewayFilter)
                                .filter(showRouteURIGatewayFilter))
                        .uri("lb://scorer-feign")
                        .order(11))
                .route(r -> r.path("/connect_point/**")
                        .filters(f -> f.filter(saveGetInGatewayFilter)
                                .filter(authSignatureGatewayFilter)
                                .filter(showRouteURIGatewayFilter))
                        .uri("lb:ws://scorer-feign")
                        .order(12))
                .route(r -> r.path("/app_webSocket/info/**")
                        .filters(f -> f.filter(saveGetInGatewayFilter)
                                .filter(authSignatureGatewayFilter)
                                .filter(showRouteURIGatewayFilter))
                        .uri("lb://scorer-feign")
                        .order(21))
                .route(r -> r.path("/app_webSocket/**")
                        .filters(f -> f.filter(saveGetInGatewayFilter)
                                .filter(authSignatureGatewayFilter)
                                .filter(showRouteURIGatewayFilter))
                        .uri("lb:ws://scorer-feign")
                        .order(22))
                .route(r -> r.path("/webSocketServer/info/**")
                        .filters(f -> f.filter(saveGetInGatewayFilter)
                                .filter(authSignatureGatewayFilter)
                                .filter(showRouteURIGatewayFilter))
                        .uri("lb://scorer-feign")
                        .order(31))
                .route(r -> r.path("/webSocketServer/**")
                        .filters(f -> f.filter(saveGetInGatewayFilter)
                                .filter(authSignatureGatewayFilter)
                                .filter(showRouteURIGatewayFilter))
                        .uri("lb:ws://scorer-feign")
                        .order(32))
                .route(r -> r.path("/sockjs/webSocketServer/info/**")
                        .filters(f -> f.filter(saveGetInGatewayFilter)
                                .filter(authSignatureGatewayFilter)
                                .filter(showRouteURIGatewayFilter))
                        .uri("lb://scorer-feign")
                        .order(41))
                .route(r -> r.path("/sockjs/webSocketServer/**")
                        .filters(f -> f.filter(saveGetInGatewayFilter)
                                .filter(authSignatureGatewayFilter)
                                .filter(showRouteURIGatewayFilter))
                        .uri("lb:ws://scorer-feign")
                        .order(42))
                .build();
    }

}
