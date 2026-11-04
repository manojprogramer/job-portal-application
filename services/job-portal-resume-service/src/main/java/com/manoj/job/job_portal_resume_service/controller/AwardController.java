package com.manoj.job.job_portal_resume_service.controller;

import com.manoj.job.dto.response.ApiResponse;
import com.manoj.job.dto.response.AwardResponse;
import com.manoj.job.dto.response.CertificationResponse;
import com.manoj.job.job_portal_resume_service.payload.AddAwardRequest;
import com.manoj.job.job_portal_resume_service.service.impl.AwardServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resume-id}/awards")
public class AwardController {
    @Autowired
    private AwardServiceImpl awardService;

    @PostMapping("/add-award")
    public ResponseEntity<AwardResponse> addCertification(@PathVariable("resume-id") Long resumeId,
                                                          @RequestHeader("X-User-Id") Long candidateId,
                                                          @RequestBody @Valid AddAwardRequest request) throws Exception {
        return ResponseEntity.ok(awardService.addAward(resumeId,candidateId,request));
    }
    @GetMapping("/get-award")
    public ResponseEntity<List<AwardResponse>> getCertification(@PathVariable("resume-id") Long resumeId) throws Exception {
        return ResponseEntity.ok(awardService.getAwards(resumeId));
    }
    @PutMapping("/{award-id}/update")
    public ResponseEntity<AwardResponse> updateCertification(@PathVariable("resume-id") Long resumeId,
                                                                     @PathVariable("award-id") Long awardId,
                                                             @RequestHeader("X-User-Id") Long candidateId,
                                                                     @RequestBody AddAwardRequest request) throws Exception {
        return ResponseEntity.ok(awardService.updateAward(awardId,resumeId,candidateId,request));
    }
    @DeleteMapping("/{award-id}/delete")
    public ResponseEntity<ApiResponse> deleteCertification(@PathVariable("resume-id") Long resumeId,
                                                           @PathVariable("award-id") Long awardId,
                                                           @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        awardService.deleteAward(awardId,resumeId,candidateId);
        return ResponseEntity.ok(new ApiResponse("Certification Deleted Successfully", true));
    }
}
