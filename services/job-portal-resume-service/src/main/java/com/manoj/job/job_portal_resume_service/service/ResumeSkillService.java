package com.manoj.job.job_portal_resume_service.service;

import com.manoj.job.dto.response.ResumeSkillResponse;
import com.manoj.job.job_portal_resume_service.payload.AddResumeSkillRequest;

import java.util.List;

public interface ResumeSkillService {
  ResumeSkillResponse addSkill(Long resumeId, Long candidateId, AddResumeSkillRequest resumeSkillRequest) throws Exception;
    List<ResumeSkillResponse> getSkills(Long resumeId);
    ResumeSkillResponse updateSkill(Long skillId, Long resumeId, Long candidateId, AddResumeSkillRequest resumeSkillRequest) throws Exception;
    void deleteSkill(Long skillId, Long resumeId, Long candidateId) throws Exception;
}
