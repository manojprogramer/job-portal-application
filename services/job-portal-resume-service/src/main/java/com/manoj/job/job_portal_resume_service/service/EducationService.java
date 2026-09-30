package com.manoj.job.job_portal_resume_service.service;

import com.manoj.job.dto.response.EducationResponse;
import com.manoj.job.job_portal_resume_service.payload.AddEducationRequest;

import java.util.List;

public interface EducationService {
    EducationResponse addEducation(Long resumeId,
                                   Long candidateId,
                                   AddEducationRequest request) throws Exception;
    List<EducationResponse> getEducations(Long resumeId);
    EducationResponse updateEducation(Long educationId,
                                      Long resumeId,
                                      Long candidateId,
                                      AddEducationRequest request) throws Exception;
    void deleteEducation(Long educationId, Long resumeId, Long candidateId) throws Exception;
}
