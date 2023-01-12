package rs.ac.uns.acs.smpuos.GatewayServis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

//    @Autowired
//    SpecialClassGatewayFilterFactory filter;
//
//    @Bean
//    public RouteLocator routes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("user-service", r -> r.path("/user-service/**")
//                        .filters(f -> f.filter(filter.apply(config -> {})) )
//                        .uri("lb://user-service"))
//
////                .route("auth-service", r -> r.path("/auth/**")
////                        .filters(f -> f.filter(filter))
////                        .uri("lb://auth-service"))
//                .build();
//    }

}
