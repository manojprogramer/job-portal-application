package com.manoj.job_portal_job_service.mapper;

import com.manoj.job.dto.response.JobCategoryResponse;
import com.manoj.job_portal_job_service.model.JobCategory;

import java.util.List;
import java.util.stream.Collectors;

public class JobCategoryMapper {

    public static JobCategoryResponse jobCategoryResponse(JobCategory jobCategory, Boolean includeChildren){
        List<JobCategoryResponse>subCategories = null;
        if(includeChildren)
            jobCategory.getSubcategories()
                .stream().map(s -> jobCategoryResponse(s,false)).toList();
        return JobCategoryResponse.builder()
                .id(jobCategory.getId())
                .name(jobCategory.getName())
                .description(jobCategory.getDescription())
                .slug(jobCategory.getSlug())
                .iconUrl(jobCategory.getIconUrl())
                .active(jobCategory.getActive())
                .parentId(jobCategory.getParent() != null ? jobCategory.getParent().getId() : null)
                .parentName(jobCategory.getParent() != null ? jobCategory.getParent().getName() : null)
                .subCategories(subCategories)
                .createdAt(jobCategory.getCreatedAt())
                .build();

    }
}
