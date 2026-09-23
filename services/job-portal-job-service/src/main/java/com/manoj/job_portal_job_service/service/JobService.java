package com.manoj.job_portal_job_service.service;

import com.manoj.job_portal_job_service.payload.JobRequest;
import com.manoj.job.dto.response.JobResponse;
import com.manoj.payload.JobSearchRequest;

import java.util.List;

public interface JobService {
    JobResponse createJob(Long employerId, JobRequest request) throws Exception;

    JobResponse getJobById(Long id) throws Exception;

    List<JobResponse> getJobs(JobSearchRequest request);


    List<JobResponse> getJobsByCompany(Long companyId);

    JobResponse updateJob(Long jobId, Long employerId, JobRequest request) throws Exception;

    JobResponse publishJob(Long jobId, Long employerId) throws Exception;

    JobResponse closeJob(Long jobId, Long employerId) throws Exception;

    void deleteJob(Long jobId, Long employerId) throws Exception;

    void incrementApplicationCount(Long jobId);

    List<JobResponse> getAllJobsAdmin();
}
