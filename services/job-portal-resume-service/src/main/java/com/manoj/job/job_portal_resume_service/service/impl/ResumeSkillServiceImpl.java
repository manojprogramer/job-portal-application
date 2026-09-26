package com.manoj.job.job_portal_resume_service.service.impl;

import com.manoj.job.dto.ResumeSkillResponse;
import com.manoj.job.job_portal_resume_service.payload.AddResumeSkillRequest;
import com.manoj.job.job_portal_resume_service.repo.ResumeSkillRepo;
import com.manoj.job.job_portal_resume_service.service.ResumeSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeSkillServiceImpl implements ResumeSkillService {

    @Autowired
    private ResumeSkillRepo resumeSkillRepo;

    @Override
    public ResumeSkillResponse addSkill(Long resumeId, Long candidateId, AddResumeSkillRequest resumeSkillRequest) {
        return null;
    }

    @Override
    public List<ResumeSkillResponse> getSkills(Long resumeId) {
        return List.of();
    }

    @Override
    public ResumeSkillResponse updateSkill(Long skillId, Long resumeId, Long candidateId, AddResumeSkillRequest resumeSkillRequest) {
        return null;
    }

    @Override
    public void deleteSkill(Long skillId, Long resumeId, Long candidateId) {

    }
}
