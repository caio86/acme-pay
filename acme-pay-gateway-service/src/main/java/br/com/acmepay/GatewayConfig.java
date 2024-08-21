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
                        .uri("lb://account-service"))
                .route("customers", r -> r.path("/api/v1/customers/**")
                        .uri("lb://customer-service"))
                .route("notifications", r -> r.path("/api/v1/notifications/**")
                        .uri("lb://notification-service"))
                .route("transactions", r -> r.path("/api/v1/transactions/**")
                        .uri("lb://transaction-service"))
                .build();
    }

}
