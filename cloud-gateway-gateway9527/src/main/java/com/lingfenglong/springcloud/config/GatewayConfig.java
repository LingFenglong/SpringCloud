package com.lingfenglong.springcloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("payment_route01", route -> route
                        .path("/payment/get/**")
                        .uri("http://localhost:8001"))

                .route("payment_route02", route -> route
                        .path("/payment/lb")
                        .uri("http://localhost:8001"))

                .route("baidu_route01", route -> route
                        .path("/baidu")
                        .uri("http://www.baidu.com"))

                .route("aliyun_route02", route -> route
                        .path("/aliyun")
                        .uri("https://cn.aliyun.com"))

                .route("route001", route -> route
                        .path("/payment/predicate/test")
//                        .and()
//                        .cookie("username", "lingfenglong").and()
//                        .header("myheader", "tty").and()
//                        .method(HttpMethod.GET)
                        .filters(filter -> filter
                                .addResponseHeader("X-header-name", "X-header-value")
                                .filter((exchange, chain) -> {
                                    log.error("这是一个假的error级别的日志");
                                    log.info("执行了自定义的全局过滤器: " + "MyLogGateWayFilter " + "hello");

                                    // 检查用户名
                                    boolean flag = Objects.equals(exchange.getRequest().getQueryParams().getFirst("username"), "lingfenglong");
                                    if (!flag) {
                                        exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                                        log.error("用户名不存在");
                                        return exchange.getResponse().setComplete();
                                    }
                                    log.info("登录成功");
                                    return chain.filter(exchange);
                                }))
                        .uri("http://localhost:8001"))
                .build();
    }
}
