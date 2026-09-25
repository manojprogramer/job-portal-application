package com.manoj.job.job_portal_resume_service.service;

import com.manoj.job.dto.WorkExperienceResponse;
import com.manoj.job.job_portal_resume_service.model.WorkExperience;
import com.manoj.job.job_portal_resume_service.payload.AddWorkExperienceRequest;

import java.util.List;

public interface WorkExperienceService {
    WorkExperienceResponse addWorkExperience(Long resumeId, Long candidateId, AddWorkExperienceRequest request);
    List<WorkExperienceResponse> getWorkExperiences(Long resumeId);
    WorkExperienceResponse updateWorkExperience(Long resumeId,
                                                Long workExperienceId,
                                                AddWorkExperienceRequest request);
    void deleteWorkExperience(Long resumeId, Long workExperienceId);
}
