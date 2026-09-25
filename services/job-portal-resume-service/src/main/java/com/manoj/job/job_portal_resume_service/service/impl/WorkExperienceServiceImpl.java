package com.manoj.job.job_portal_resume_service.service.impl;

import com.manoj.job.dto.WorkExperienceResponse;
import com.manoj.job.job_portal_resume_service.payload.AddWorkExperienceRequest;
import com.manoj.job.job_portal_resume_service.service.WorkExperienceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {
    @Override
    public WorkExperienceResponse addWorkExperience(Long resumeId, Long candidateId, AddWorkExperienceRequest request) {
        return null;
    }

    @Override
    public List<WorkExperienceResponse> getWorkExperiences(Long resumeId) {
        return List.of();
    }

    @Override
    public WorkExperienceResponse updateWorkExperience(Long resumeId, Long workExperienceId, AddWorkExperienceRequest request) {
        return null;
    }

    @Override
    public void deleteWorkExperience(Long resumeId, Long workExperienceId) {

    }
}
