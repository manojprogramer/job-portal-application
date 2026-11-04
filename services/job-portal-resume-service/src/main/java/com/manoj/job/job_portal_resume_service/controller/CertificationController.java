package com.manoj.job.job_portal_resume_service.controller;

import com.manoj.job.dto.response.ApiResponse;
import com.manoj.job.dto.response.CertificationResponse;
import com.manoj.job.job_portal_resume_service.payload.AddCertificationRequest;
import com.manoj.job.job_portal_resume_service.service.CertificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resume-id}/certification")
public class CertificationController {
    @Autowired
    CertificationService certificationService;

    @PostMapping("/add-certification")
    public ResponseEntity<CertificationResponse> addCertification(@PathVariable("resume-id") Long resumeId,
                                                                  @RequestHeader("X-User-Id") Long candidateId,
                                                                  @RequestBody @Valid AddCertificationRequest request) throws Exception {
        return ResponseEntity.ok(certificationService.addCertification(resumeId,candidateId,request));
    }
    @GetMapping("/get-certification")
    public ResponseEntity<List<CertificationResponse>> getCertification(@PathVariable("resume-id") Long resumeId) throws Exception {
        return ResponseEntity.ok(certificationService.getCertifications(resumeId));
    }
    @PutMapping("/{certification-id}/update")
    public ResponseEntity<CertificationResponse> updateCertification(@PathVariable("resume-id") Long resumeId,
                                                                     @PathVariable("certification-id") Long certificationId,
                                                                     @RequestHeader("X-User-Id")Long candidateId,
                                                                     @RequestBody AddCertificationRequest request) throws Exception {
        return ResponseEntity.ok(certificationService.updateCertification(certificationId,resumeId,candidateId,request));
    }
    @DeleteMapping("/{certification-id}/delete")
    public ResponseEntity<ApiResponse> deleteCertification(@PathVariable("resume-id") Long resumeId,
                                                           @PathVariable("certification-id") Long certificationId,
                                                           @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        certificationService.deleteCertification(certificationId,resumeId,candidateId);
        return ResponseEntity.ok(new ApiResponse("Certification Deleted Successfully", true));
    }

}
