package com.manoj.job.job_portal_resume_service.service.impl;

import com.manoj.job.dto.response.PersonalInfoResponse;
import com.manoj.job.dto.response.ResumeResponse;
import com.manoj.job.job_portal_resume_service.mapper.ResumeMapper;
import com.manoj.job.job_portal_resume_service.model.Resume;
import com.manoj.job.job_portal_resume_service.model.embeddable.PersonalInfo;
import com.manoj.job.job_portal_resume_service.payload.CreateResumeRequest;
import com.manoj.job.job_portal_resume_service.repo.ResumeRepo;
import com.manoj.job.job_portal_resume_service.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeRepo resumeRepo;
    @Override
    public ResumeResponse createResume(Long candidateId, CreateResumeRequest request) {
        if(Boolean.TRUE.equals(request)){
            resumeRepo.findByCandidateIdAndIsDefaultTrue(candidateId).ifPresent(existing ->{
                existing.setIsDefault(false);
                resumeRepo.save(existing);
            });
        }
        Resume resume = Resume.builder()
                .candidateId(candidateId)
                .title(request.getTitle())
                .resumeVisibility(request.getResumeVisibility())
                .isDefault(Boolean.TRUE.equals(request.getIsDefault()))
                .isActive(true)
                .build();
        Resume saved = resumeRepo.save(resume);
        return buildFullResponse(resume);
    }

    @Override
    public ResumeResponse getResumeById(Long resumeId, Long candidateId) throws Exception {
        Resume resume = getResumeByEntity(candidateId);
        assertOwner(resume,candidateId);
        return buildFullResponse(resume);
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId))
            throw new Exception("Resume not Found With id");
    }

    @Override
    public List<ResumeResponse> getMyResumes(Long candidateId) {
        return resumeRepo.findByCandidateIdAndIsActiveTrue(candidateId)
                .stream()
                .map(this::buildFullResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ResumeResponse updatePersonalInfo(Long resumeId, Long candidateId, PersonalInfoResponse request) throws Exception {
        Resume resume = getResumeByEntity(resumeId);
        assertOwner(resume,candidateId);
        PersonalInfo info = resume.getPersonalInfo();
        if(info == null) info = new PersonalInfo();

        if(request.getFirstName()!= null)
            info.setFirstName(request.getFirstName());
        if(request.getLastName() != null)
            info.setLastName(request.getLastName());
        if(request.getHeadLine() != null)
            info.setHeadLine(request.getHeadLine());
        if(request.getEmail() != null)
            info.setEmail(request.getEmail());
        if(request.getPhone() != null)
            info.setPhone(request.getPhone());
        if(request.getCity() != null)
            info.setCity(request.getCity());
        if(info.getCountry() != null)
            info.setCountry(request.getCountry());
        if(info.getLinkedInUrl() != null)
            info.setLinkedInUrl(request.getLinkedInUrl());
        if(info.getGithubUrl() != null)
            info.setGithubUrl(request.getGithubUrl());
        if(info.getPortfolioUrl() != null)
            info.setPortfolioUrl(request.getPortfolioUrl());
        resume.setPersonalInfo(info);
        Resume saved = resumeRepo.save(resume);
        return buildFullResponse(saved);
    }

    @Override
    public ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary) throws Exception {
        Resume resume = getResumeByEntity(resumeId);

        assertOwner(resume, candidateId);
        resume.setSummary(summary);
        Resume updated = resumeRepo.save(resume);
        return buildFullResponse(updated);
    }

    @Override
    public ResumeResponse setDefaultResume(Long resumeId, Long candidateId) throws Exception {
        Resume resume = getResumeByEntity(resumeId);
        assertOwner(resume,candidateId);

        resumeRepo.findByCandidateIdAndIsDefaultTrue(candidateId)
                .ifPresent(existing ->{
                    existing.setIsDefault(false);
                    resumeRepo.save(existing);
                });
        resume.setIsDefault(true);
        Resume updated = resumeRepo.save(resume);
        return buildFullResponse(updated);
    }

    @Override
    public void deleteResume(Long resumeId, Long candidateId) throws Exception {
        Resume resume =getResumeByEntity(resumeId);
        assertOwner(resume,candidateId);
        resume.setIsActive(false);
        resume.setIsDefault(false);
        resumeRepo.save(resume);
        resumeRepo.delete(resume);


    }

    @Override
    public Resume getResumeByEntity(Long resumeId) throws Exception {
        return resumeRepo.findById(resumeId).orElseThrow(() -> new Exception("Resume not Found ById"));
    }
    private ResumeResponse buildFullResponse(Resume resume){
        return ResumeMapper.toResponse(resume);
    }
}
