package com.manoj.job_portal_job_service.service.impl;

import com.manoj.job.dto.request.JobRequest;
import com.manoj.job.dto.response.JobResponse;
import com.manoj.job_portal_job_service.mapper.JobMapper;
import com.manoj.job_portal_job_service.model.Job;
import com.manoj.job_portal_job_service.model.embeddable.JobLocation;
import com.manoj.job_portal_job_service.model.embeddable.SalaryRange;
import com.manoj.job_portal_job_service.repo.JobRepo;
import com.manoj.job_portal_job_service.service.JobService;
import com.manoj.payload.JobSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepo jobRepo;

    @Override
    public JobResponse createJob(Long employerId, JobRequest request) {
        Long companyId = 1L;
        Job job = Job.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .requirements(request.getRequirements())
                .responsibilities(request.getResponsibilities())
                .benefits(request.getBenefits())
                .companyId(companyId)
//                .category(category)
//                .skill(skills)
//                .tags(tags)
                .location(buildLocation(request))
                .salaryRange(buildSalaryRange(request))
                .jobType(request.getJobType())
                .workMode(request.getWorkMode())
                .experienceLevel(request.getExperienceLevel())
                .openings(request.getOpenings() != null ? request.getOpenings() : 1)
                .applicationDeadline(request.getApplicationDeadLine())
                .expiredAt(request.getExpiredAt())
                .build();
        Job jobs = jobRepo.save(job);
        return JobMapper.toResponse(job);
    }

    private SalaryRange buildSalaryRange(JobRequest request) {
        return SalaryRange.builder()
                .minRange(request.getMinSalary())
                .maxRange(request.getMaxSalary())
                .build();
    }

    private JobLocation buildLocation(JobRequest request) {
        return JobLocation.builder()
                .address(request.getAddress())
                .city(request.getCity())
                .country(request.getCountry())
                .state(request.getState())
                .zipcode(request.getZipcode())
                .build();
    }

    @Override
    public JobResponse getJobById(Long id) {
        return null;
    }

    @Override
    public List<JobResponse> getJobs(JobSearchRequest request) {
        return List.of();
    }

    @Override
    public JobResponse updateJob(Long employerId, JobRequest request) {
        return null;
    }

    @Override
    public List<JobResponse> getJobsByCompany(Long companyId) {
        return List.of();
    }

    @Overrideeb
    public JobResponse updateJob(Long jobId, Long employerId, JobRequest request) {
        return null;
    }

    @Override
    public JobResponse publishJob(Long jobId, Long employerId) {
        return null;
    }

    @Override
    public JobResponse closeJob(Long jobId, Long employerId) {
        return null;
    }

    @Override
    public JobResponse deleteJob(Long jobId, Long employerId) {
        return null;
    }

    @Override
    public void incrementApplicationCount(Long jobId) {

    }

    @Override
    public List<JobResponse> getAllJobsAdmin() {
        return List.of();
    }
}
