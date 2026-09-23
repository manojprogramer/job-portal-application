package com.manoj.job_portal_job_service.mapper;

import com.manoj.job.dto.response.JobTagResponse;
import com.manoj.job_portal_job_service.model.JobTag;

public class JobTagMapper {
    public static JobTagResponse toResponse(JobTag jobTag){
        return JobTagResponse.builder()
                .id(jobTag.getId())
                .name(jobTag.getName())
                .slug(jobTag.getSlug())
                .build();
    }
}
