package com.scorer.gateway;

import com.scorer.gateway.check_right.CheckAppUserTokenFilter;
import com.scorer.gateway.check_right.CheckAppRSAFilter;
import com.scorer.gateway.check_right.CheckWebManagerTokenFilter;
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

    private CheckAppUserTokenFilter checkAppUserTokenFilter =new CheckAppUserTokenFilter();
    private CheckWebManagerTokenFilter checkWebManagerTokenFilter =new CheckWebManagerTokenFilter();
    private CheckAppRSAFilter checkAppRSAFilter =new CheckAppRSAFilter();

    @Bean
    public RouteLocator scorerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/EDU/**")
                        .filters(f -> f.filter(saveGetInGatewayFilter)
                                .filter(authSignatureGatewayFilter)
                                .filter(showRouteURIGatewayFilter)
//                                .filter(checkAppUserTokenFilter)
//                                .filter(checkWebManagerTokenFilter)
//                                .filter(checkAppRSAFilter)
                        )
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
                .build();
    }

}
