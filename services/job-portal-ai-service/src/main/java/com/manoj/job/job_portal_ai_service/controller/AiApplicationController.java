package com.manoj.job.job_portal_ai_service.controller;

import com.manoj.job.job_portal_ai_service.payload.*;
import com.manoj.job.job_portal_ai_service.service.ApplicationAiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai/application")
public class AiApplicationController {

    @Autowired
    private ApplicationAiService applicationAiService;

    @PostMapping("/cover-letter")
    public ResponseEntity<AiTextResponse> generateCoverLetter(
            @Valid @RequestBody CoverLetterRequest coverLetterRequest
            ) throws Exception {
        return ResponseEntity.ok(applicationAiService.generateCoverLetter(coverLetterRequest));
    }

    @PostMapping("/screening-score")
    public ResponseEntity<ScreeningScoreResponse> scoreCandidate(
            @Valid @RequestBody ScreeningScoreRequest request
            ) throws Exception {
        return ResponseEntity.ok(applicationAiService.scoreCandidate(request));
    }
    @PostMapping("/skills-gap")
    public ResponseEntity<SkillGapResponse> analyzeSkillGap(
            @Valid @RequestBody SkillGapRequest request
    ) throws Exception {
        return ResponseEntity.ok(applicationAiService.analyzeSkillGap(request));
    }

}
