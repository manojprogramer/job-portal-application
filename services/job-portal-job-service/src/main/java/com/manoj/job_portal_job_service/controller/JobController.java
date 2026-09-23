package com.manoj.job_portal_job_service.controller;

import com.manoj.job.dto.ApiResponse;
import com.manoj.job_portal_job_service.payload.JobRequest;
import com.manoj.job.dto.response.JobResponse;
import com.manoj.job_portal_job_service.service.JobService;
import com.manoj.payload.JobSearchRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping("/create")
    public ResponseEntity<JobResponse> createJob(@RequestHeader ("X-User-Id") Long employerId,
                                                 @RequestBody @Valid JobRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.createJob(employerId,request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobService.getJobById(id));
    }
    @GetMapping("/getjobs")
    public ResponseEntity<List<JobResponse>>getJobs(@ModelAttribute JobSearchRequest request){
        return ResponseEntity.ok(jobService.getJobs(request));
    }
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<JobResponse>>getJobsBycompany(@PathVariable Long companyId){
        return ResponseEntity.status(HttpStatus.OK).body(jobService.getJobsByCompany(companyId));
    }
    @GetMapping("/admin")
    public ResponseEntity<List<JobResponse>> getAllJobsAdmin(){
        return ResponseEntity.ok(jobService.getAllJobsAdmin());
    }
    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable Long id,
                                                 @RequestHeader("X-User-Id") Long employerId,
                                                 @RequestBody @Valid JobRequest request) throws Exception {
        return ResponseEntity.ok(jobService.updateJob(id,employerId,request));
    }
    @PatchMapping("/{id}/publish")
    public ResponseEntity<JobResponse> publishJob(@PathVariable Long id,
                                                  @RequestHeader("X-User-Id") Long employerId) throws Exception {
        return ResponseEntity.ok(jobService.publishJob(id,employerId));
    }
    @PatchMapping("/{id}/close")
    public ResponseEntity<JobResponse> closeJob(@PathVariable Long id,
                                                @RequestHeader("X-User-Id") Long employerId) throws Exception {
        return ResponseEntity.ok(jobService.closeJob(id,employerId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteJob(@PathVariable Long id,
                                                 @RequestHeader("X-User-Id") Long employerId) throws Exception {
        jobService.deleteJob(id,employerId);
        return ResponseEntity.ok(new ApiResponse("Job Deleted Successfully",true));
    }
}
