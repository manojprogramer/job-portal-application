package com.manoj.job_portal_job_service.controller;

import com.manoj.job.dto.ApiResponse;
import com.manoj.job.dto.response.JobTagResponse;
import com.manoj.job_portal_job_service.payload.JobTagRequest;
import com.manoj.job_portal_job_service.service.JobTagService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-tag")
public class JobTagController {
    @Autowired
    private JobTagService jobTagService;

    @PostMapping("/create-tag")
    public ResponseEntity<JobTagResponse> createJobTag(@RequestBody @Valid JobTagRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(jobTagService.createTag(request));
    }
    @GetMapping("/get-all-tags")
    public ResponseEntity<List<JobTagResponse>> getAllTags(){
        return ResponseEntity.ok(jobTagService.getAllTags());
    }
    @PutMapping("/{id}")
    public ResponseEntity<JobTagResponse> updateTag(@PathVariable Long id,
                                                    @RequestBody @Valid JobTagRequest request) throws Exception {
        return ResponseEntity.ok(jobTagService.updateTag(id,request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTag(@PathVariable Long id) throws Exception {
        jobTagService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Job Tag Deleted Successfully",true));
    }

}
