package com.manoj.job_portal_job_service.mapper;

import com.manoj.job.dto.response.*;
import com.manoj.job_portal_job_service.model.Job;
import com.manoj.job_portal_job_service.model.embeddable.JobLocation;
import com.manoj.job_portal_job_service.model.embeddable.SalaryRange;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class JobMapper {
    public static JobResponse toResponse(Job job, CompanyResponse companyResponse){
        JobCategoryResponse jobCategoryResponse = job.getCategory() != null
                ? JobCategoryMapper.jobCategoryResponse(job.getCategory(),true) : null;
        Set<JobSkillResponse> jobSkillResponses =  job.getSkills() != null
                ? job.getSkills().stream().map(JobSkillMapper::toResponse).collect(Collectors.toSet())
                : Collections.emptySet() ;
        Set<JobTagResponse> jobTagResponses = job.getTags() != null
                ? job.getTags().stream().map(JobTagMapper::toResponse).collect(Collectors.toSet())
                : Collections.emptySet();
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
                .jobCategoryResponse(jobCategoryResponse)
                .jobSkillResponse(jobSkillResponses)
                .jobTagResponse(jobTagResponses)
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
