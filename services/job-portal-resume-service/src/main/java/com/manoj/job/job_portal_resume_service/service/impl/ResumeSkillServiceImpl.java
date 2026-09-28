package com.manoj.job.job_portal_resume_service.service.impl;

import com.manoj.job.dto.response.JobCategoryResponse;
import com.manoj.job.dto.response.ResumeSkillResponse;
import com.manoj.job.job_portal_resume_service.mapper.ResumeMapper;
import com.manoj.job.job_portal_resume_service.model.Resume;
import com.manoj.job.job_portal_resume_service.model.ResumeSkill;
import com.manoj.job.job_portal_resume_service.payload.AddResumeSkillRequest;
import com.manoj.job.job_portal_resume_service.repo.ResumeSkillRepo;
import com.manoj.job.job_portal_resume_service.service.ResumeService;
import com.manoj.job.job_portal_resume_service.service.ResumeSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeSkillServiceImpl implements ResumeSkillService {

    @Autowired
    private ResumeSkillRepo resumeSkillRepo;

    @Autowired
    private ResumeService resumeService;

    @Override
    public ResumeSkillResponse addSkill(Long resumeId, Long candidateId, AddResumeSkillRequest resumeSkillRequest) throws Exception {
        Resume resume = resumeService.getResumeByEntity(resumeId);
        assertOwner(resume,candidateId);
        ResumeSkill resumeSkill = ResumeSkill.builder()
                .resume(resume)
                .skillName(resumeSkillRequest.getSkillName())
                .proficiencyLevel(resumeSkillRequest.getProficiencyLevel())
                .yearsOfExperience(resumeSkillRequest.getYearsOfExperience())
                .displayOrder(resumeSkillRequest.getDisplayOrder() != null ? resumeSkillRequest.getDisplayOrder() : 0)
                .build();
        ResumeSkill savedSkill = resumeSkillRepo.save(resumeSkill);
        return ResumeMapper.toSkillResponse(savedSkill);
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId))
            throw new Exception("Resume Not Found");

    }

    @Override
    public List<ResumeSkillResponse> getSkills(Long resumeId) {
        return resumeSkillRepo.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                .stream().map(ResumeMapper::toSkillResponse).toList();
    }

    @Override
    public ResumeSkillResponse updateSkill(Long skillId, Long resumeId, Long candidateId, AddResumeSkillRequest resumeSkillRequest) throws Exception {
        ResumeSkill skill = resumeSkillRepo.findById(skillId).orElseThrow(() -> new Exception("Skill Not Found"));
        assertOwner(skill.getResume(),candidateId);
        skill.setSkillName(resumeSkillRequest.getSkillName());
        skill.setProficiencyLevel(resumeSkillRequest.getProficiencyLevel());
        skill.setYearsOfExperience(resumeSkillRequest.getYearsOfExperience());
        if(resumeSkillRequest.getDisplayOrder() != null) skill.setDisplayOrder(resumeSkillRequest.getDisplayOrder());

        return ResumeMapper.toSkillResponse(skill);
    }

    @Override
    public void deleteSkill(Long skillId, Long resumeId, Long candidateId) throws Exception {
        ResumeSkill skill = resumeSkillRepo.findById(skillId)
                .orElseThrow(() -> new Exception("Resume Not Found"));
        assertOwner(skill.getResume(),candidateId);
        resumeSkillRepo.delete(skill);


    }
}
