package com.manoj.job.job_portal_api_gateway.config;

import org.apache.catalina.Server;
import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayDelegatingRouterFunction;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.*;

@Configuration
public class RouteConfig {

    @Bean
    public RouterFunction<ServerResponse> authRoutes(){
        return GatewayRouterFunctions.route("auth-routes").route(RequestPredicates.path("/auth/**"),HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-user-service"))
                .build();
    }

//    =====================Admin only Routes (JWT+Role_Admin) ===============


    @Bean
    public RouterFunction<ServerResponse> adminRoutes(){
        return GatewayRouterFunctions.route("admin-routes").route(RequestPredicates.path("/api/admin/**"),HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-user-service"))
//                .before(this::jwtFilter)
//                .before(request -> requireRole(request,"Role_Admin"))
                .build();
    }

//    =========================== Protected Routes (Jwt Required) =====================
    @Bean
    public RouterFunction<ServerResponse> userServiceRoutes(){
        return GatewayRouterFunctions.route("user-service-routes")
                .route(RequestPredicates.path("/api/user/**"),HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-user-service"))
//                .before(this::jwtFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> companyServiceRoutes(){
        return GatewayRouterFunctions.route("company-service-routes")
                .route(RequestPredicates.path("/api/company/**"),HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-company-service"))
//                .before(this::jwtFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> jobServiceRoutes(){
        return GatewayRouterFunctions.route("job-service-routes")
                .route(RequestPredicates.path("/api/jobs/**")
                        .or(RequestPredicates.path("/api/job-category/**"))
                        .or(RequestPredicates.path("/api/job-skill/**"))
                        .or(RequestPredicates.path("/api/job-tag/**"))
                        ,HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-job-service"))
//                .before(this::jwtFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> applicationServiceRoutes(){
        return GatewayRouterFunctions.route("application-service-routes")
                .route(RequestPredicates.path("/api/applications/**"),HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-application-service"))
//                .before(this::jwtFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> preferenceServiceRoutes(){
        return GatewayRouterFunctions.route("preference-service-routes")
                .route(RequestPredicates.path("/api/preference/**"),HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-preference-service"))
//                .before(this::jwtFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> resumeServiceRoutes(){
        return GatewayRouterFunctions.route("resume-service-routes")
                .route(RequestPredicates.path("/api/resumes/**"),HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-resume-service"))
//                .before(this::jwtFilter)
                .build();
    }


}
