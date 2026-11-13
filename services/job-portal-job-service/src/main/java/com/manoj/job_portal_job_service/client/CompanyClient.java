package com.manoj.job_portal_job_service.client;

import com.manoj.job.dto.response.CompanyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "JOB-PORTAL-COMPANY-SERVICE")
public interface CompanyClient {
    @GetMapping("/api/company/{id}")
    CompanyResponse getCompanyById(@PathVariable("id") Long id);
    @GetMapping("/api/company/my")
    CompanyResponse getMyCompany(@RequestHeader("X-User-Id") Long ownerId);


}
