package com.manoj.job_portal_job_service.mapper;

import com.manoj.job.dto.response.CompanyResponse;
import com.manoj.job.dto.response.JobResponse;
import com.manoj.job_portal_job_service.model.Job;
import com.manoj.job_portal_job_service.model.embeddable.JobLocation;
import com.manoj.job_portal_job_service.model.embeddable.SalaryRange;

public class JobMapper {
    public static JobResponse toResponse(Job job, CompanyResponse companyResponse){
        JobLocation location = job.getLocation();
        SalaryRange salary = job.getSalaryRange();
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirements(job.getRequirements())
                .responsibilities(job.getResponsibilities())
                .benefits(job.getBenefits())
                .companyResponse(companyResponse)
//                .category(category)
//                .skills(skills)
//                .tags(tags)
                .address(location != null ? location.getAddress() : null)
                .city(location != null ? location.getCity() : null)
                .state(location != null ? location.getState() : null)
                .country(location != null ? location.getCountry() : null)
                .zipcode(location != null ? location.getZipcode() :null)

                .minSalary(salary != null ? salary.getMinRange() : null)
                .maxSalary(salary != null ? salary.getMaxRange() : null)

                .jobType(job.getJobType())
                .workMode(job.getWorkMode())
                .experienceLevel(job.getExperienceLevel())
                .jobStatus(job.getJobStatus())

                .openings(job.getOpenings())
                .applicationDeadline(job.getApplicationDeadline())
                .expiresAt(job.getExpiredAt())
                .active(job.getActive())

                .createdAt(job.getCreatedAt())
                .updatedAt(job.getUpdatedAt())
                .publishedAt(job.getPublishedAt())
                .closedAt(job.getClosedAt())

                .build();

    }

    public static JobResponse convertToResponse(Job job) {
        CompanyResponse companyResponse = CompanyResponse.builder()
                .id(job.getCompanyId())
                .build();
        return JobMapper.toResponse(job,companyResponse);
    }
}
