package org.springcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author Kristoff
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    // @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        System.out.println("routes=========");
        return builder.routes()
                .route("path_route",
                        p -> p.path("/web1/**").uri("http://localhost:8001")).build();
    }
}
