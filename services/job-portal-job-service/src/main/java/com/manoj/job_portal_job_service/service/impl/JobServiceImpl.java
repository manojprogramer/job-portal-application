package com.manoj.job_portal_job_service.service.impl;

import com.manoj.job.domain.JobStatus;
import com.manoj.job_portal_job_service.payload.JobRequest;
import com.manoj.job.dto.response.JobResponse;
import com.manoj.job_portal_job_service.mapper.JobMapper;
import com.manoj.job_portal_job_service.model.Job;
import com.manoj.job_portal_job_service.model.embeddable.JobLocation;
import com.manoj.job_portal_job_service.model.embeddable.SalaryRange;
import com.manoj.job_portal_job_service.repo.JobRepo;
import com.manoj.job_portal_job_service.repo.JobSpecification;
import com.manoj.job_portal_job_service.service.JobService;
import com.manoj.payload.JobSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
                .employerId(employerId)
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
        return JobMapper.convertToResponse(job);
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
    public JobResponse getJobById(Long id) throws Exception {
        Job job = jobRepo.findById(id).orElseThrow(() -> new Exception("Job Not Found"));
        return JobMapper.convertToResponse(job);
    }

    @Override
    public List<JobResponse> getJobs(JobSearchRequest request) {
        List<Job> jobs = jobRepo.findAll(JobSpecification.build(request));
        return jobs.stream().map(JobMapper::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobResponse> getJobsByCompany(Long companyId) {
        List<Job> jobs = jobRepo.findByCompanyId(companyId);
        return jobs.stream().map(JobMapper::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobResponse updateJob(Long jobId, Long employerId, JobRequest request) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new Exception("Job Not Found"));
        assertEmployer(job,employerId);

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setRequirements(request.getRequirements());
        job.setResponsibilities(request.getResponsibilities());
        job.setBenefits(request.getBenefits());
//        job.setCategory(category);
//        job.setSkills(skills);
//        job.setTags(tags);
        job.setLocation(buildLocation(request));
        job.setSalaryRange(buildSalaryRange(request));
        job.setJobType(request.getJobType());
        job.setWorkMode(request.getWorkMode());
        job.setExperienceLevel(request.getExperienceLevel());
        job.setOpenings(request.getOpenings());
        job.setApplicationDeadline(request.getApplicationDeadLine());
        job.setExpiredAt(request.getExpiredAt());

        return JobMapper.convertToResponse(jobRepo.save(job));
    }

    @Override
    public JobResponse publishJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new Exception("Job Not Found"));
        assertEmployer(job,employerId);
        if(job.getJobStatus() == JobStatus.CLOSED || job.getJobStatus() == JobStatus.EXPIRED)
            throw new Exception("Job is expired");
        job.setJobStatus(JobStatus.OPEN);
        job.setPublishedAt(LocalDateTime.now());
        job.setActive(true);
        return JobMapper.convertToResponse(jobRepo.save(job));
    }

    private void assertEmployer(Job job, Long employerId) throws Exception {
        if(!job.getEmployerId().equals(employerId))
            throw new Exception("You are not the employer who posted this job");
    }

    @Override
    public JobResponse closeJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new Exception("Job Not Found"));
        assertEmployer(job,employerId);

        job.setJobStatus(JobStatus.CLOSED);
        job.setClosedAt(LocalDateTime.now());
        job.setActive(false);
        return JobMapper.convertToResponse(job);
    }

    @Override
    public void deleteJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new Exception("Job Not Found"));
        assertEmployer(job,employerId);
        jobRepo.delete(job);
    }

    @Override
    public void incrementApplicationCount(Long jobId) {

    }

    @Override
    public List<JobResponse> getAllJobsAdmin() {
        return jobRepo.findAll().stream().map(JobMapper :: convertToResponse).collect(Collectors.toList());
    }
}
