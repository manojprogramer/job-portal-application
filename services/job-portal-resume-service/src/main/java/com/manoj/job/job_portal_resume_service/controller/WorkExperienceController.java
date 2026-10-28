package com.manoj.job.job_portal_resume_service.controller;

import com.manoj.job.dto.response.ApiResponse;
import com.manoj.job.dto.response.WorkExperienceResponse;
import com.manoj.job.job_portal_resume_service.payload.AddWorkExperienceRequest;
import com.manoj.job.job_portal_resume_service.service.WorkExperienceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resume-id}/work-experience")
public class WorkExperienceController {
    @Autowired
    private WorkExperienceService workExperienceService;

    @PostMapping("/add-work-experience")
    public ResponseEntity<WorkExperienceResponse> addWorkExperience(@PathVariable("resume-id") Long resumeId,
                                                                    @RequestHeader("X-User-Id") Long candidateId,
                                                                    @RequestBody @Valid AddWorkExperienceRequest request) throws Exception {
        return ResponseEntity.ok(workExperienceService.addWorkExperience(resumeId,candidateId,request));
    }
    @GetMapping("/get-work-experiences")
    public ResponseEntity<List<WorkExperienceResponse>> getWorkExperiences(@PathVariable("resume-id") Long resumeId){
        return ResponseEntity.ok(workExperienceService.getWorkExperiences(resumeId));
    }
    @PutMapping("/update/{experience-id}")
    public ResponseEntity<WorkExperienceResponse> updateWorkExperience(@PathVariable("resume-id") Long resumeId,
                                                                       @PathVariable("experience-id") Long experienceId,
                                                                       @RequestHeader("X-User-Id") Long candidateId,
                                                                       @RequestBody @Valid AddWorkExperienceRequest request) throws Exception {
        return ResponseEntity.ok(workExperienceService.updateWorkExperience(resumeId,candidateId,experienceId,request));

    }
    @DeleteMapping("/delete/{experience-id}")
    public ResponseEntity<ApiResponse> deleteWorkExperience(@PathVariable("resume-id") Long resumeId,
                                                            @PathVariable("experience-id") Long experienceId,
                                                            @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        workExperienceService.deleteWorkExperience(resumeId,experienceId,candidateId);
        return ResponseEntity.ok(new ApiResponse("Work Experience Deleted Successfully",true));
    }

}
