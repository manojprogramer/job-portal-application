package com.manoj.job.job_portal_resume_service.service.impl;

import com.manoj.job.dto.response.AwardResponse;
import com.manoj.job.job_portal_resume_service.mapper.ResumeMapper;
import com.manoj.job.job_portal_resume_service.model.Award;
import com.manoj.job.job_portal_resume_service.model.Resume;
import com.manoj.job.job_portal_resume_service.payload.AddAwardRequest;
import com.manoj.job.job_portal_resume_service.repo.AwardRepo;
import com.manoj.job.job_portal_resume_service.service.AwardService;
import com.manoj.job.job_portal_resume_service.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private AwardRepo awardRepo;
    @Override
    public AwardResponse addAward(Long resumeId, Long candidateId, AddAwardRequest request) throws Exception {
        Resume resume = resumeService.getResumeByEntity(resumeId);
        assertOwner(resume,candidateId);
        Award award = Award.builder()
                .resume(resume)
                .awardTitle(request.getAwardTitle())
                .awardUrl(request.getAwardUrl())
                .awardDate(request.getAwardDate())
                .description(request.getDescription())
                .issuingOrganization(request.getIssuingOrganization())
                .displayOrder(request.getDisplayOrder())
                .build();
        Award saved = awardRepo.save(award);
        return ResumeMapper.toAwardResponse(saved);
    }

    @Override
    public List<AwardResponse> getAwards(Long resumeId) {
        return awardRepo.findByResumeIdOrderByDisplayOrderAsc(resumeId).stream()
                .map(ResumeMapper::toAwardResponse).toList();
    }

    @Override
    public AwardResponse updateAward(Long awardId, Long resumeId, Long candidateId, AddAwardRequest addAwardRequest) throws Exception {
        Award award = awardRepo.findById(awardId).orElseThrow(() -> new Exception("Award Not Found"));
        assertOwner(award.getResume(),candidateId);
        award.setAwardTitle(addAwardRequest.getAwardTitle());
        award.setAwardUrl(addAwardRequest.getAwardUrl());
        award.setAwardDate(addAwardRequest.getAwardDate());
        award.setDescription(addAwardRequest.getDescription());
        award.setIssuingOrganization(addAwardRequest.getIssuingOrganization());
        Award updated = awardRepo.save(award);
        return ResumeMapper.toAwardResponse(updated);
    }

    @Override
    public void deleteAward(Long awardId, Long resumeId, Long candidateId) throws Exception {
        Award award = awardRepo.findById(awardId).orElseThrow(() -> new Exception("Award Not Found"));
        assertOwner(award.getResume(),candidateId);
        awardRepo.delete(award);

    }
    public void assertOwner(Resume resume,Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId))
            throw new Exception("Resume Not Found");
    }
}
