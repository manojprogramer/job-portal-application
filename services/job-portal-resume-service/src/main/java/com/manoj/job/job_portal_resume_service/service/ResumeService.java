package com.manoj.job.job_portal_resume_service.service;

import com.manoj.job.dto.response.PersonalInfoResponse;
import com.manoj.job.dto.response.ResumeResponse;
import com.manoj.job.job_portal_resume_service.model.Resume;
import com.manoj.job.job_portal_resume_service.payload.CreateResumeRequest;

import java.util.List;

public interface ResumeService {
    ResumeResponse createResume(Long candidateId, CreateResumeRequest request);
    ResumeResponse getResumeById(Long resumeId, Long candidateId) throws Exception;
    List<ResumeResponse> getMyResumes(Long candidateId);
    ResumeResponse updatePersonalInfo(Long resumeId, Long candidateId, PersonalInfoResponse request) throws Exception;
    ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary) throws Exception;
    ResumeResponse setDefaultResume(Long resumeId, Long candidateId) throws Exception;
    void deleteResume(Long resumeId, Long candidateId) throws Exception;
    Resume getResumeByEntity(Long resumeId) throws Exception;

}
