package com.manoj.job_portal_job_service.service.impl;

import com.manoj.job_portal_job_service.payload.JobCategoryRequest;
import com.manoj.job.dto.response.JobCategoryResponse;
import com.manoj.job_portal_job_service.mapper.JobCategoryMapper;
import com.manoj.job_portal_job_service.model.JobCategory;
import com.manoj.job_portal_job_service.repo.JobCategoryRepo;
import com.manoj.job_portal_job_service.service.JobCategoryService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobCategoryImpl implements JobCategoryService {
    @Autowired
    private JobCategoryRepo jobCategoryRepo;


    @Override
    public JobCategoryResponse createCategory(JobCategoryRequest request) throws Exception {
        if(jobCategoryRepo.existsByName(request.getName())){
            throw new Exception("Category name already exists");
        }
        JobCategory parent = null;
        if(request.getParentId() != null){
            parent = getCategoryEntityById(request.getParentId());
        }
        String slug = generateUniqueSlug(request.getName());
        JobCategory category = JobCategory.builder()
                .name(request.getName())
                .slug(slug)
                .description(request.getDescription())
                .iconUrl(request.getIconUrl())
                .parent(parent)
                .build();
        JobCategory saved  = jobCategoryRepo.save(category);
        return JobCategoryMapper.jobCategoryResponse(saved,true);
    }

    private String generateUniqueSlug(@NotBlank(message = "category name is required") String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]","")
                .trim()
                .replaceAll("[\\s-]+","-");
        if(!jobCategoryRepo.existsBySlug(base)) return base;
        int counter = 1;
        while(jobCategoryRepo.existsBySlug(base+"-"+counter)){
            counter++;
        }
        return base+"-"+counter;
    }

    @Override
    public List<JobCategoryResponse> getAllCategories() {
        return jobCategoryRepo.findByActiveTrue().stream().map(c -> JobCategoryMapper.jobCategoryResponse(c,false)).collect(Collectors.toList());
    }

    @Override
    public JobCategoryResponse getCategoryById(Long id) throws Exception {
        return JobCategoryMapper.jobCategoryResponse(jobCategoryRepo.findById(id).orElseThrow(() -> new Exception("Category Not Found")),true);
    }

    @Override
    public JobCategoryResponse updateCategory(Long id, JobCategoryRequest request) throws Exception {
        JobCategory category = getCategoryEntityById(id);
        if(!category.getName().equals(request.getName()) && jobCategoryRepo.existsByName(request.getName()))
            throw new Exception("Category name Already Exist");
        JobCategory parent = null;
        if(request.getParentId() != null){
            if(request.getParentId().equals(id)){
                throw new Exception("Category cannot be its own parent");
            }
            parent = getCategoryEntityById(request.getParentId());
        }
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setIconUrl(request.getIconUrl());
        category.setParent(parent);

        JobCategory updated = jobCategoryRepo.save(category);
        return JobCategoryMapper.jobCategoryResponse(updated,true);
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        JobCategory category = getCategoryEntityById(id);
        jobCategoryRepo.delete(category);

    }

    @Override
    public JobCategory getCategoryEntityById(Long id) throws Exception {
        return jobCategoryRepo.findById(id).orElseThrow(()-> new Exception("Category Not Found"));
    }
}
