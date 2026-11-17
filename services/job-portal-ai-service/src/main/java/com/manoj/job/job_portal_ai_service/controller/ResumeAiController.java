package com.manoj.job.job_portal_ai_service.controller;

import com.manoj.job.job_portal_ai_service.payload.*;
import com.manoj.job.job_portal_ai_service.service.ResumeAiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/resume")
public class ResumeAiController {
    @Autowired
    private ResumeAiService resumeAiService;

    @PostMapping("/summary")
    public ResponseEntity<AiTextResponse> generateSummary(@RequestBody ResumeSummaryRequest request) throws Exception {
        AiTextResponse response = resumeAiService.generateProfessionalSummary(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/experience-bullets")
    public ResponseEntity<WorkExperienceBulletsResponse> generateBullets(@Valid @RequestBody WorkExperienceBulletRequest request) throws Exception {
        WorkExperienceBulletsResponse workExperienceBulletsResponse= resumeAiService.generateWorkExperienceBullets(request);
        return ResponseEntity.ok(workExperienceBulletsResponse);
    }
    @PostMapping("/improvements")
    public ResponseEntity<ResumeImprovementResponse> getImprovements(@Valid @RequestBody ResumeImprovementRequest request) throws Exception {
        ResumeImprovementResponse response = resumeAiService.getResumeImprovementTips(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/career-feedback")
    public ResponseEntity<CareerFeedBackResponse> getCareerFeedBack(@Valid @RequestBody CareerFeedBackRequest careerFeedBackRequest) throws Exception {
        CareerFeedBackResponse careerFeedBackResponse = resumeAiService.getCareerFeedBack(careerFeedBackRequest);
        return ResponseEntity.ok(careerFeedBackResponse);
    }
}
