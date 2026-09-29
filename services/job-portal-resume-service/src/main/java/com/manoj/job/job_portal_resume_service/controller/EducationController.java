package com.manoj.job.job_portal_resume_service.controller;

import com.manoj.job.dto.response.ApiResponse;
import com.manoj.job.dto.response.EducationResponse;
import com.manoj.job.job_portal_resume_service.payload.AddEducationRequest;
import com.manoj.job.job_portal_resume_service.service.EducationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resume-id}/education")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @PostMapping("/add-education")
    public ResponseEntity<EducationResponse> addEducation(@PathVariable("resume-id") Long resumeId,
                                                          @PathVariable("X-User-Id") Long candidateId,
                                                          @RequestBody @Valid AddEducationRequest request) throws Exception {
        return ResponseEntity.ok(educationService.addEducation(resumeId,candidateId,request));
    }
    @GetMapping("/get-educations")
    public ResponseEntity<List<EducationResponse>> getEducations(@PathVariable Long resumeId){
        return ResponseEntity.ok(educationService.getEducations(resumeId));
    }
    @PutMapping("/update/{education-id}")
    public ResponseEntity<EducationResponse> updateEducation(@PathVariable Long resumeId,
                                                             @PathVariable Long educationId,
                                                             @PathVariable("X-User-Id") Long candidateId,
                                                             @RequestBody @Valid AddEducationRequest request) throws Exception {
        return ResponseEntity.ok(educationService.updateEducation(educationId,resumeId,candidateId,request));
    }
    public ResponseEntity<ApiResponse> deleteEducation(@PathVariable Long resumeId,
                                                       @PathVariable Long educationId,
                                                       @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        educationService.deleteEducation(educationId,resumeId,candidateId);
        return ResponseEntity.ok(new ApiResponse("Education is Deleted Successful",true));

    }
}
