package com.manoj.job.job_portal_ai_service.controller;

import com.manoj.job.job_portal_ai_service.payload.AiTextResponse;
import com.manoj.job.job_portal_ai_service.payload.JobDescriptionRequest;
import com.manoj.job.job_portal_ai_service.payload.SalaryRangeRequest;
import com.manoj.job.job_portal_ai_service.payload.SalaryRangeResponse;
import com.manoj.job.job_portal_ai_service.service.impl.JobAiServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/job")
public class AiJobController {

    @Autowired
    private JobAiServiceImpl jobAiService;

    @PostMapping("/describe")
    public ResponseEntity<AiTextResponse> generateJobDescription(
            @Valid @RequestBody JobDescriptionRequest request
            ) throws Exception {
        AiTextResponse response = jobAiService.generateJobDescription(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/requirements")
    public ResponseEntity<AiTextResponse> generateRequirements(@RequestParam String title,
                                                               @RequestParam(required = false) String category) throws Exception {
        return ResponseEntity.ok(jobAiService.generateJobRequirements(title,category));
    }
    @PostMapping("/salary-suggestion")
    public ResponseEntity<SalaryRangeResponse> suggestSalary(@Valid @RequestBody SalaryRangeRequest request) throws Exception {
        return ResponseEntity.ok(jobAiService.suggestSalaryRange(request));
    }
    @GetMapping("/skill-recommendation")
    public ResponseEntity<AiTextResponse> recommendSkills(@RequestParam String title,
                                                          @RequestParam(required = false) String category) throws Exception {
        return ResponseEntity.ok(jobAiService.generateSkillsForJobs(title,category));
    }
    @GetMapping("/responsibilities")
    public ResponseEntity<AiTextResponse> generateResponsibilities(@RequestParam String title,
                                                                   @RequestParam(required = false) String category) throws Exception {
        return ResponseEntity.ok(jobAiService.generateJobResponsibilities(title,category));
    }
    @GetMapping("/benefits")
    public ResponseEntity<AiTextResponse> generateBenefits(@RequestParam String title,
                                                           @RequestParam(required = false) String category,
                                                           @RequestParam(required = false) String jobType) throws Exception {
        return ResponseEntity.ok(jobAiService.generateJobBenefits(title,category,jobType));
    }
    @GetMapping("/tags-recommendation")
    public ResponseEntity<AiTextResponse> recommendTags(@RequestParam String title,
                                                        @RequestParam(required = false) String description) throws Exception {
        return ResponseEntity.ok(jobAiService.generateTagsForJobs(title,description));
    }
}
