package com.manoj.job.job_portal_ai_service.controller;

import com.manoj.job.job_portal_ai_service.payload.JobAlertSuggestRequest;
import com.manoj.job.job_portal_ai_service.payload.JobAlertSuggestResponse;
import com.manoj.job.job_portal_ai_service.payload.SearchEnhanceResponse;
import com.manoj.job.job_portal_ai_service.payload.SearchEnhancementRequest;
import com.manoj.job.job_portal_ai_service.service.SearchAiService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ReflectiveScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai/search")
public class AiSearchController {

    @Autowired
    private SearchAiService searchAiService;

    @PostMapping("/enhance")
    public ResponseEntity<SearchEnhanceResponse> enhanceSearch(
            @Valid @RequestBody SearchEnhancementRequest request
            ) throws Exception {
        return ResponseEntity.ok(searchAiService.enhanceSearch(request));
    }
    @PostMapping("/alert-suggestion")
    public ResponseEntity<JobAlertSuggestResponse> searchAlertCriteria(
            @Valid @RequestBody JobAlertSuggestRequest request) throws Exception {
        return ResponseEntity.ok(searchAiService.suggestJobAlertCriteria(request));
    }

}
