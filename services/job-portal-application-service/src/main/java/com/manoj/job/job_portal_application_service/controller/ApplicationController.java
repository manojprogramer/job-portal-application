package com.manoj.job.job_portal_application_service.controller;

import com.manoj.job.dto.response.ApiResponse;
import com.manoj.job.dto.response.ApplicationResponse;
import com.manoj.job.job_portal_application_service.model.Application;
import com.manoj.job.job_portal_application_service.payload.CompanyApplicationFilterRequest;
import com.manoj.job.job_portal_application_service.payload.CreateApplicationRequest;
import com.manoj.job.job_portal_application_service.payload.UpdateApplicationStatusRequest;
import com.manoj.job.job_portal_application_service.payload.WithDrawnApplicationRequest;
import com.manoj.job.job_portal_application_service.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/create-application")
    public ResponseEntity<ApplicationResponse> createApplication(
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid CreateApplicationRequest request
            ) throws Exception {
        return ResponseEntity.ok(applicationService.createApplication(candidateId,request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getApplicationById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ApplicationResponse>> getMyApplications(@RequestHeader("X-User-Id") Long candidateId){
        return ResponseEntity.ok(applicationService.getMyApplications(candidateId));
    }
    @GetMapping("/job/{job-id}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsForJob(@PathVariable Long jobId){
        return ResponseEntity.ok(applicationService.getApplicationsForJob(jobId));
    }
    @GetMapping("/company")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsForCompany(@RequestHeader("X-User-Id") Long userId,
                                                                               @ModelAttribute CompanyApplicationFilterRequest filter){
        return ResponseEntity.ok(applicationService.getApplicationsForCompany(userId,filter));
    }
    @PatchMapping("/{id}/update")
    public ResponseEntity<ApplicationResponse> updateStatus(@PathVariable Long id,
                                                            @RequestHeader("X-User-Id") Long employerId,
                                                            @RequestBody @Valid UpdateApplicationStatusRequest request) throws Exception {
        return ResponseEntity.ok(applicationService.updateStatus(id,
                employerId,
                request.getStatus()));

    }
    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<ApplicationResponse> withDraw(@PathVariable Long id,
                                                        @RequestHeader("X-User-Id") Long candidateId,
                                                        @RequestBody WithDrawnApplicationRequest request) throws Exception {
        return ResponseEntity.ok(applicationService.withDraw(id,candidateId,request));
    }
    @PatchMapping("/{id}/star")
    public ResponseEntity<ApplicationResponse> toggleStar(@PathVariable Long id,
                                                          @RequestHeader("X-User-Id") Long employerId) throws Exception {
        return ResponseEntity.ok(applicationService.toggleStar(id,employerId));
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse> deleteApplication(@PathVariable Long id,
                                                         @RequestHeader("X-User-Id") Long candidateId) throws Exception {
         applicationService.deleteApplication(id,candidateId);
         return ResponseEntity.ok(new ApiResponse("Application Deleted Successfully",true));
    }


}
