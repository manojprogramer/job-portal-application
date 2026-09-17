package com.manoj.job_portal_job_service.service;

import com.manoj.job.dto.request.JobRequest;
import com.manoj.job.dto.response.JobResponse;
import com.manoj.payload.JobSearchRequest;

import java.util.List;

public interface JobService {
    JobResponse createJob(Long employerId, JobRequest request);

    JobResponse getJobById(Long id);

    List<JobResponse> getJobs(JobSearchRequest request);

    JobResponse updateJob(Long employerId, JobRequest request);

    List<JobResponse> getJobsByCompany(Long companyId);

    JobResponse updateJob(Long jobId, Long employerId, JobRequest request);

    JobResponse publishJob(Long jobId, Long employerId);

    JobResponse closeJob(Long jobId, Long employerId);

    JobResponse deleteJob(Long jobId, Long employerId);

    void incrementApplicationCount(Long jobId);

    List<JobResponse> getAllJobsAdmin();
}
