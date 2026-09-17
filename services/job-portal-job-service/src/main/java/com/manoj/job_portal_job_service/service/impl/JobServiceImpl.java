package com.manoj.job_portal_job_service.service.impl;

import com.manoj.job.dto.request.JobRequest;
import com.manoj.job.dto.response.JobResponse;
import com.manoj.job_portal_job_service.service.JobService;
import com.manoj.payload.JobSearchRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    @Override
    public JobResponse createJob(Long employerId, JobRequest request) {
        return null;
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

    @Override
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
