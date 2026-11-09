package com.manoj.job.job_portal_preferences.controller;

import com.manoj.job.dto.response.ApiResponse;
import com.manoj.job.dto.response.SavedJobResponse;
import com.manoj.job.job_portal_preferences.payload.SavedJobRequest;
import com.manoj.job.job_portal_preferences.service.SavedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preference/saved-jobs")
public class SavedJobController {

    @Autowired
    private SavedJobService savedJobService;

    @PostMapping("/saved-job")
    public ResponseEntity<SavedJobResponse> saveJob(
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody SavedJobRequest request
            ) throws Exception {
        return ResponseEntity.ok(savedJobService.saveJob(candidateId,request));
    }
    @GetMapping("/get-saved-job")
    public ResponseEntity<List<SavedJobResponse>> getMySavedJobs(
            @RequestHeader("X-User-Id") Long candidateId
    ){
        return ResponseEntity.ok(savedJobService.getSavedJob(candidateId));
    }
    @GetMapping("/check")
    public ResponseEntity<Boolean> isSaved(
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestParam Long jobId
    ){
        return ResponseEntity.ok(savedJobService.isSaved(candidateId,jobId));
    }
    @DeleteMapping("/{saved-job-id}/delete")
    public ResponseEntity<ApiResponse> unSavedJob(
            @PathVariable("saved-job-id") Long savedJobId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        savedJobService.unSaveJob(candidateId,savedJobId);
        return ResponseEntity.ok(new ApiResponse("Job Removed From Saved List", true));
    }



}
