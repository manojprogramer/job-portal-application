package com.manoj.job_portal_job_service.mapper;

import com.manoj.job.dto.response.JobSkillResponse;
import com.manoj.job_portal_job_service.model.JobSkill;

public class JobSkillMapper {
    public static JobSkillResponse toResponse(JobSkill skill){
        return JobSkillResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .slug(skill.getSlug())
                .skillCategory(skill.getCategory())
                .active(skill.getActive())
                .build();

    }
}
