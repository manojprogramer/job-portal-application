package com.manoj.job.job_portal_resume_service.service;

import com.manoj.job.dto.ResumeSkillResponse;
import com.manoj.job.job_portal_resume_service.payload.AddResumeSkillRequest;

import java.util.List;

public interface ResumeSkillService {
    ResumeSkillResponse addSkill(Long resumeId, Long candidateId, AddResumeSkillRequest resumeSkillRequest);
    List<ResumeSkillResponse> getSkills(Long resumeId);
    ResumeSkillResponse updateSkill(Long skillId, Long resumeId, Long candidateId, AddResumeSkillRequest resumeSkillRequest);
    void deleteSkill(Long skillId, Long resumeId, Long candidateId);
}
