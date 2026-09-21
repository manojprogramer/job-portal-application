package com.manoj.job_portal_job_service.service;

import com.manoj.job.dto.request.JobCategoryRequest;
import com.manoj.job.dto.response.JobCategoryResponse;
import com.manoj.job_portal_job_service.model.JobCategory;

import java.util.List;

public interface JobCategoryService {
    JobCategoryResponse createCategory(JobCategoryRequest request) throws Exception;
    List<JobCategoryResponse> getAllCategories();
    JobCategoryResponse getCategoryById(Long id) throws Exception;
    JobCategoryResponse updateCategory(Long id, JobCategoryRequest request) throws Exception;
    void deleteCategory(Long id);
    JobCategory getCategoryEntityById(Long id) throws Exception;
}
