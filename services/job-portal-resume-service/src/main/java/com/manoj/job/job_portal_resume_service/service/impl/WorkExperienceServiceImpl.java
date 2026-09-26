package com.manoj.job.job_portal_resume_service.service.impl;

import com.manoj.job.dto.WorkExperienceResponse;
import com.manoj.job.job_portal_resume_service.mapper.WorkExperienceMapper;
import com.manoj.job.job_portal_resume_service.model.Resume;
import com.manoj.job.job_portal_resume_service.model.WorkExperience;
import com.manoj.job.job_portal_resume_service.payload.AddWorkExperienceRequest;
import com.manoj.job.job_portal_resume_service.repo.WorkExperienceRepo;
import com.manoj.job.job_portal_resume_service.service.ResumeService;
import com.manoj.job.job_portal_resume_service.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    @Autowired
    private WorkExperienceRepo workExperienceRepo;

    @Autowired
    private ResumeService resumeService;

    @Override
    public WorkExperienceResponse addWorkExperience(Long resumeId, Long candidateId, AddWorkExperienceRequest request) throws Exception {
        Resume resume = resumeService.getResumeByEntity(resumeId);

        assertOwner(resume,candidateId);
        WorkExperience workExperience = WorkExperience.builder()
                .resume(resume)
                .companyName(request.getCompanyName())
                .companyLogoUrl(request.getCompanyLogoUrl())
                .jobTitle(request.getJobTitle())
                .employmentType(request.getEmploymentType())
                .location(request.getLocation())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isCurrentJob(Boolean.TRUE.equals(request.getIsCurrentJob()))
                .description(request.getDescription())
                .technologiesName(request.getTechnologies() != null ? request.getTechnologies() : List.of())
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();
        WorkExperience saved = workExperienceRepo.save(workExperience);
        return WorkExperienceMapper.toWorkExperienceResponse(saved);
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId))
            throw new Exception("resume not found");
    }

    @Override
    public List<WorkExperienceResponse> getWorkExperiences(Long resumeId) {
        return workExperienceRepo.findByResumeIdOrderByDisplayOrderAsc(resumeId).stream()
                .map(WorkExperienceMapper::toWorkExperienceResponse).toList();
    }

    @Override
    public WorkExperienceResponse updateWorkExperience(Long resumeId, Long workExperienceId, Long candidateId, AddWorkExperienceRequest request) throws Exception {
        WorkExperience workExperience = getWorkExperienceEntity(workExperienceId);
        assertOwner(workExperience.getResume(),candidateId);

        workExperience.setCompanyLogoUrl(request.getCompanyLogoUrl());
        workExperience.setCompanyName(request.getCompanyName());
        workExperience.setJobTitle(request.getJobTitle());
        workExperience.setEmploymentType(request.getEmploymentType());
        workExperience.setLocation(request.getLocation());
        workExperience.setStartDate(request.getStartDate());
        workExperience.setEndDate(request.getEndDate());
        workExperience.setIsCurrentJob(request.getIsCurrentJob());
        workExperience.setDescription(request.getDescription());
        if(request.getTechnologies() != null) workExperience.setTechnologiesName(request.getTechnologies());
        if(request.getDisplayOrder() != null) workExperience.setDisplayOrder(request.getDisplayOrder());

       WorkExperience updated =  workExperienceRepo.save(workExperience);
        return WorkExperienceMapper.toWorkExperienceResponse(updated);
    }

    @Override
    public void deleteWorkExperience(Long resumeId, Long workExperienceId, Long candidateId) throws Exception {
        WorkExperience workExperience = getWorkExperienceEntity(workExperienceId);
        assertOwner(workExperience.getResume(),candidateId);

        workExperienceRepo.delete(workExperience);

    }

    @Override
    public WorkExperience getWorkExperienceEntity(Long workExperienceId) throws Exception {
        return workExperienceRepo.findById(workExperienceId).orElseThrow(() -> new Exception("Work Experience Not Found"));
    }
}
