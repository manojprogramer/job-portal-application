package com.manoj.job_portal_job_service.controller;

import com.manoj.job.dto.ApiResponse;
import com.manoj.job_portal_job_service.payload.JobCategoryRequest;
import com.manoj.job.dto.response.JobCategoryResponse;
import com.manoj.job_portal_job_service.service.JobCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-category")
public class JobCategoryController {
    @Autowired
    private JobCategoryService jobCategoryService;

    @PostMapping("/create-category")
    public ResponseEntity<JobCategoryResponse> createCategory(@RequestBody @Valid JobCategoryRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobCategoryService.createCategory(request));
    }
    @GetMapping("/get-all-categories")
    public ResponseEntity<List<JobCategoryResponse>> getAllCategories() throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobCategoryService.getAllCategories());
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobCategoryResponse> getCategoryById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobCategoryService.getCategoryById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<JobCategoryResponse> updateCategory(@PathVariable Long id,
                                                              @RequestBody @Valid JobCategoryRequest request) throws Exception {
        return ResponseEntity.ok(jobCategoryService.updateCategory(id,request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Long id) throws Exception {
        jobCategoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse("Job Category Deleted Successfully",true));
    }
}
