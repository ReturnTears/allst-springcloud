package com.allst.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Gateway路由配置类
 * @author YiYa
 * @since 2020-07-28 下午 10:38
 */
@Configuration
public class GatewayConfig {
    /**
     * 配置一个id为pathRoute的路由规则，当访问http://localhost:9527/internet时会自动转发到http://news.baidu.com/internet
     * @param routeLocatorBuilder 路由构建器
     * @return 返回
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("pathRoute", r -> r.path("/internet").uri("http://news.baidu.com/internet")).build();
        return routes.build();
    }

}
