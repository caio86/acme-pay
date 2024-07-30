package br.com.acmepay;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("accounts", r -> r.path("/api/v1/accounts/**")
                        .uri("http://localhost:8081"))
                .route("customers", r -> r.path("/api/v1/customers/**")
                        .uri("http://localhost:8082"))
                .route("notifications", r -> r.path("/api/v1/notifications/**")
                        .uri("http://localhost:8083"))
                .route("transactions", r -> r.path("/api/v1/transactions/**")
                        .uri("http://localhost:8084"))
                .build();
    }

}
