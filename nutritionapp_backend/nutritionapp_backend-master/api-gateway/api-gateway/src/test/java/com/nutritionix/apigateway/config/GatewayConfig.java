//package com.nutritionix.apigateway.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GatewayConfig {
//
//	@Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("authentication-service", r -> r
//                        .path("/authentication/**")
//                        .uri("lb://authentication-service"))  
////                .route("user-profile", r -> r
////                        .path("/userprofile/**")
////                        .uri("http://localhost:8081")) 
////                .route("nutrition-service", r -> r
////                        .path("/nutrition/**")
////                        .uri("http://localhost:8083")) 
////                .route("wishlist", r -> r
////                        .path("/wishlist/**")
////                        .uri("http://localhost:8084")) 
//                
//                .build();
//    }
//	
//}
