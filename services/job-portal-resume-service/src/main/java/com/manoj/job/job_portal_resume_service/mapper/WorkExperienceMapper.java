package com.manoj.job.job_portal_resume_service.mapper;

import com.manoj.job.dto.WorkExperienceResponse;
import com.manoj.job.job_portal_resume_service.model.WorkExperience;

public class WorkExperienceMapper {
    public static WorkExperienceResponse toWorkExperienceResponse(WorkExperience workExperience){
        if(workExperience == null) return null;
        return WorkExperienceResponse.builder()
                .id(workExperience.getId())
                .companyName(workExperience.getCompanyName())
                .companyLogoUrl(workExperience.getCompanyLogoUrl())
                .jobTitle(workExperience.getJobTitle())
                .employmentType(workExperience.getEmploymentType())
                .location(workExperience.getLocation())
                .startDate(workExperience.getStartDate())
                .endDate(workExperience.getEndDate())
                .isCurrentJob(workExperience.getIsCurrentJob())
                .description(workExperience.getDescription())
                .technologies(workExperience.getTechnologiesName())
                .displayOrder(workExperience.getDisplayOrder())
                .build();
    }
}
