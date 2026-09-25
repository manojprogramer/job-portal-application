package com.manoj.job.job_portal_resume_service.controller;

import com.manoj.job.dto.ApiResponse;
import com.manoj.job.dto.response.PersonalInfoResponse;
import com.manoj.job.dto.response.ResumeResponse;
import com.manoj.job.job_portal_resume_service.payload.CreateResumeRequest;
import com.manoj.job.job_portal_resume_service.service.ResumeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @PostMapping("/create-resume")
    public ResponseEntity<ResumeResponse> createResume(@RequestHeader("X-User-Id") Long candidateId,
                                                       @RequestBody @Valid CreateResumeRequest request){
        return ResponseEntity.ok(resumeService.createResume(candidateId,request));
    }
    @GetMapping("/{resume-id}")
    public ResponseEntity<ResumeResponse> getResumeById(@PathVariable Long resumeId,
                                                        @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        return ResponseEntity.ok(resumeService.getResumeById(resumeId,candidateId));
    }
    @GetMapping("/my")
    public ResponseEntity<List<ResumeResponse>> getMyResume(@RequestHeader("X-User-Id") Long candidateId){
        return ResponseEntity.ok(resumeService.getMyResumes(candidateId));
    }
    @PutMapping("/{resume-id}/personal-info")
    public ResponseEntity<ResumeResponse> updatePersonalInfo(@PathVariable Long resumeId,
                                                             @RequestHeader("X-User-Id") Long candidateId,
                                                             @RequestBody @Valid PersonalInfoResponse personalInfoResponse) throws Exception {
        return ResponseEntity.ok(resumeService.updatePersonalInfo(resumeId,candidateId,personalInfoResponse));
    }
    @PatchMapping("/{resume-id}/summary")
    public ResponseEntity<ResumeResponse> updateSummary(@PathVariable Long resumeId,
                                                        @RequestHeader("X-User-Id") Long candidateId,
                                                        @RequestParam String summary) throws Exception {
        return ResponseEntity.ok(resumeService.updateSummary(resumeId,candidateId,summary));
    }
    @PatchMapping("/{resume-id}/set-default")
    public ResponseEntity<ResumeResponse> setDefaultResume(@PathVariable Long resumeId,
                                                           @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        return ResponseEntity.ok(resumeService.setDefaultResume(resumeId,candidateId));
    }
    @DeleteMapping("/{resume-id}")
    public ResponseEntity<ApiResponse> deleteResponse(@PathVariable Long resumeId,
                                                      @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        resumeService.deleteResume(resumeId,candidateId);
        return ResponseEntity.ok(new ApiResponse("Resume Deleted SuccessFully", true));
    }
}
