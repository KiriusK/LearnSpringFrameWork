package ru.gb.configs;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeBuilder) {
        return routeBuilder.routes()
                .route("Employs", r->r.path("/employs/**").uri("http://localhost:8081/"))
                .route("Tasks", r->r.path("/tasks/**").uri("http://localhost:8082/"))
                .build();
    }

    @Bean
    public GlobalFilter customFilter() {
        return new CustomGatewayFilter();
    }
}
