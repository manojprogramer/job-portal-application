package com.manoj.job.job_portal_application_service.client;

import com.manoj.job.dto.response.JobResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "JOB-PORTAL-JOB-SERVICE")
public interface JobClient {
    @GetMapping("/api/jobs/{id}")
    JobResponse getJobById(@PathVariable Long id);
}
