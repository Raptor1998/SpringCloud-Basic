package com.raptor.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * @author raptor
 * @description GatewayConfig
 * @date 2021/9/1 15:19
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //https://news.baidu.com/guonei
        routes.route("path_route_raptor",
                r -> r.path("/guonei")
                        .uri("https://news.baidu.com")).build();


        //new Function<PredicateSpec, Route.AsyncBuilder>() {
        //    @Override
        //    public Route.AsyncBuilder apply(PredicateSpec predicateSpec) {
        //        return predicateSpec.path("/guonei").uri("https://news.baidu.com");
        //    }
        //}
        return routes.build();
    }

}
