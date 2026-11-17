package com.manoj.job.job_portal_application_service.client;

import com.manoj.job.dto.response.ResumeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "JOB-PORTAL-RESUME-SERVICE")
public interface ResumeClient {
    @GetMapping("/api/resumes/get-resume/{resume-id}")
    ResumeResponse getResumeById(@PathVariable("resume-id") Long resumeId,
                                                        @RequestHeader("X-User-Id") Long candidateId);
}
