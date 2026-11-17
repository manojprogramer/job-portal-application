package com.manoj.job.job_portal_application_service.client;

import com.manoj.job.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "JOB-PORTAL-USER-SERVICE")
public interface UserClient {
    @GetMapping("/auth/api/users/{userId}")
    UserResponse getByUserId(@PathVariable Long userId);
}
