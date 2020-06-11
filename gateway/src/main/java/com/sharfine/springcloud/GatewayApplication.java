package com.sharfine.springcloud;

import com.sharfine.springcloud.config.RemoteAddrKeyResolver;
import com.sharfine.springcloud.filter.ElapsedGatewayFilterFactory;
import com.sharfine.springcloud.filter.RateLimitByCpuGatewayFilter;
import com.sharfine.springcloud.filter.RateLimitByIpGatewayFilter;
import com.sharfine.springcloud.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);

    }

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    @Bean
    public ElapsedGatewayFilterFactory elapsedGatewayFilterFactory() {
        return new ElapsedGatewayFilterFactory();
    }

    @Autowired
    private RateLimitByCpuGatewayFilter rateLimitByCpuGatewayFilter;

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        // @formatter:off
        return builder.routes()
                //只有这个请求路径才会触发此过滤器
                .route(r -> r.path("/throttle/eureka-consumer-feign/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter(new RateLimitByIpGatewayFilter(10, 1, Duration.ofSeconds(1)))
                                .filter(rateLimitByCpuGatewayFilter)
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("lb://EUREKA-CONSUMER-FEIGN")
                        .order(0)
                        .id("throttle_customer_service")
                )
                .build();
        // @formatter:on
    }

    @Bean(name = RemoteAddrKeyResolver.BEAN_NAME)
    public RemoteAddrKeyResolver remoteAddrKeyResolver() {
        return new RemoteAddrKeyResolver();
    }

}
