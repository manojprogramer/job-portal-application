package com.manoj.job.job_portal_resume_service.controller;

import com.manoj.job.dto.response.ApiResponse;
import com.manoj.job.dto.response.LanguageResponse;
import com.manoj.job.job_portal_resume_service.payload.AddLanguageRequest;
import com.manoj.job.job_portal_resume_service.service.LanguageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resume-id}/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;
    @PostMapping("/add-language")
    public ResponseEntity<LanguageResponse> addLanguage(@PathVariable Long resumeId,
                                                        @RequestHeader("X-User-Id") Long candidateId,
                                                        @RequestBody @Valid AddLanguageRequest request) throws Exception {
        return ResponseEntity.ok(languageService.addLanguage(resumeId,candidateId,request));
    }
    @GetMapping("/get-languages")
    public ResponseEntity<List<LanguageResponse>> getLanguages(@PathVariable Long resumeId){
        return ResponseEntity.ok(languageService.getLanguages(resumeId));
    }
    @PutMapping("/{language-id}")
    public ResponseEntity<LanguageResponse> updateLanguage(
            @PathVariable Long resumeId,
            @PathVariable Long languageId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddLanguageRequest request) throws Exception {
        return ResponseEntity.ok(languageService.updateLanguage(languageId,resumeId,candidateId,request));
    }
    @DeleteMapping("/delete/{language-id}")
    public ResponseEntity<ApiResponse> deleteLanguage(@PathVariable Long resumeId,
                                                      @PathVariable Long languageId,
                                                      @RequestHeader("X-User-Id") Long candidateId) throws Exception {

        languageService.deleteLanguage(languageId,resumeId,candidateId);
        return ResponseEntity.ok(new ApiResponse("Language Deleted Successful",true));
    }

}
