package com.manoj.job.job_portal_api_gateway.config;

import com.manoj.job.job_portal_api_gateway.util.JwtConstant;
import com.manoj.job.job_portal_api_gateway.util.JwtUtil;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayDelegatingRouterFunction;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.*;

@Configuration
public class RouteConfig {

    @Autowired
    private JwtUtil jwtUtil;

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
                .before(this::jwtAuthFilter)
                .before(request -> requireRole(request,"Role_Admin"))
                .build();
    }

    private ServerRequest requireRole(ServerRequest request, String roleAdmin) {
        String roles = request.headers().firstHeader("X-User-Role");
        if(roles == null || !roles.contains(roleAdmin))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Access Denied Fole Role"+roleAdmin);
        return request;
    }

    //    =========================== Protected Routes (Jwt Required) =====================
    @Bean
    public RouterFunction<ServerResponse> userServiceRoutes(){
        return GatewayRouterFunctions.route("user-service-routes")
                .route(RequestPredicates.path("/api/user/**"),HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-user-service"))
                .before(this::jwtAuthFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> companyServiceRoutes(){
        return GatewayRouterFunctions.route("company-service-routes")
                .route(RequestPredicates.path("/api/company/**"),HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-company-service"))
                .before(this::jwtAuthFilter)
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
                .before(this::jwtAuthFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> applicationServiceRoutes(){
        return GatewayRouterFunctions.route("application-service-routes")
                .route(RequestPredicates.path("/api/applications/**"),HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-application-service"))
                .before(this::jwtAuthFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> preferenceServiceRoutes(){
        return GatewayRouterFunctions.route("preference-service-routes")
                .route(RequestPredicates.path("/api/preference/**"),HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-preference-service"))
                .before(this::jwtAuthFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> resumeServiceRoutes(){
        return GatewayRouterFunctions.route("resume-service-routes")
                .route(RequestPredicates.path("/api/resumes/**"),HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-resume-service"))
                .before(this::jwtAuthFilter)
                .build();
    }

//    Jwt Filter
    private ServerRequest jwtAuthFilter(ServerRequest request){
        String authHeader = request.headers().firstHeader(JwtConstant.JWT_HEADER);
        if(authHeader == null || !authHeader.startsWith(JwtConstant.TOKEN_PREFIX))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"missing authorization header");
        String token = authHeader.substring(JwtConstant.TOKEN_PREFIX.length());

        if(!jwtUtil.isTokenValid(token))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid authorization header");
        String email = jwtUtil.extractEmail(token);
        String authorities = jwtUtil.extractAuthorities(token);
        Long userId = jwtUtil.extractUserId(token);

        return ServerRequest.from(request)
                .header("X-User-Id", String.valueOf(userId))
                .header("X-User-Email",email)
                .header("X-User-Role",authorities)
                .build();
    }



}
