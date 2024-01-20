package com.israel.coupon.guardway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
public class GuardwayRouteConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:3000");
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()

//              auth Forge Controller routes


                .route("signInRoute", r -> r
                        .method(HttpMethod.POST)
                        .and()
                        .path("/sign-up")
                        .uri("lb://auth-forge"))

                .route("loginRoute", r -> r
                        .method(HttpMethod.POST)
                        .and()
                        .path("/login")
                        .uri("lb://auth-forge"))

                .route("userDetailsRoute", r -> r
                        .method(HttpMethod.GET)
                        .and()
                        .path("/user-details")
                        .filters(f -> f.rewritePath("/user-details", "/parse-token"))
                        .uri("lb://auth-forge"))


//                coupon hub controller routes


                .route("getCouponRoute", r -> r
                        .method(HttpMethod.GET)
                        .and()
                        .path("/get-coupon-by-uuid/**")
                        .filters(f -> f.rewritePath("/get-coupon-by-uuid/(?<seg>.*)", "/api/coupons/${seg}"))
                        .uri("lb://coupon-hub"))

                .route("getCustomerCouponsRoute", r -> r
                        .method(HttpMethod.GET)
                        .and()
                        .path("/customer-coupons")
                        .filters(f -> f.rewritePath("/customer-coupons", "/api/coupons/customer"))
                        .uri("lb://coupon-hub"))

                .route("getCompanyCouponsRoute", r -> r
                        .method(HttpMethod.GET)
                        .and()
                        .path("/company-coupons")
                        .filters(f -> f.rewritePath("/company-coupons", "/api/coupons/company"))
                        .uri("lb://coupon-hub"))

                .route("purchaseCouponRoute", r -> r
                        .method(HttpMethod.GET)
                        .and()
                        .path("/purchase-coupon/**")
                        .filters(f -> f.rewritePath("/purchase-coupon/(?<seg>.*)", "/api/coupons/purchase/${seg}"))
                        .uri("lb://coupon-hub"))


//                customer connect controller routes


                .route("createCustomerRoute", r -> r
                        .method(HttpMethod.POST)
                        .and()
                        .path("/creat-customer")
                        .filters(f -> f.rewritePath("/creat-customer", "/api/customers"))
                        .uri("lb://customer-connect"))

                .route("getCustomerDetailsRoute", r -> r
                        .method(HttpMethod.GET)
                        .and()
                        .path("/customer-details")
                        .filters(f -> f.rewritePath("/customer-details", "/api/customers/details"))
                        .uri("lb://customer-connect"))

                .route("getAllCustomerDetailsRoute", r -> r
                        .method(HttpMethod.GET)
                        .and()
                        .path("/get-all-customers")
                        .filters(f -> f.rewritePath("/get-all-customers", "/api/customers"))
                        .uri("lb://customer-connect"))

                .route("updateCustomerDetailsRoute", r -> r
                        .method(HttpMethod.PUT)
                        .and()
                        .path("/update-customer-details")
                        .filters(f -> f.rewritePath("/update-customer-details", "/api/customers"))
                        .uri("lb://customer-connect"))

                .route("deleteCustomerRoute", r -> r
                        .method(HttpMethod.DELETE)
                        .and()
                        .path("/delete-customer")
                        .filters(f -> f.rewritePath("/delete-customer", "/api/customers"))
                        .uri("lb://customer-connect"))
                .build();

    }
}
