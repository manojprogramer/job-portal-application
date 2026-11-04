package com.manoj.job.job_portal_resume_service.service.impl;

import com.manoj.job.dto.response.CertificationResponse;
import com.manoj.job.job_portal_resume_service.mapper.ResumeMapper;
import com.manoj.job.job_portal_resume_service.model.Certification;
import com.manoj.job.job_portal_resume_service.model.Resume;
import com.manoj.job.job_portal_resume_service.payload.AddCertificationRequest;
import com.manoj.job.job_portal_resume_service.repo.CertificationRepo;
import com.manoj.job.job_portal_resume_service.service.CertificationService;
import com.manoj.job.job_portal_resume_service.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CertificationServiceImpl implements CertificationService {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private CertificationRepo certificationRepo;


    @Override
    public CertificationResponse addCertification(Long resumeId, Long candidateId, AddCertificationRequest request) throws Exception {
        Resume resume = resumeService.getResumeByEntity(resumeId);
        assertOwner(resume,candidateId);
        Certification certification = Certification.builder()
                .certificationName(request.getCertificationName())
                .description(request.getDescription())
                .credentialId(request.getCredentialId())
                .credentialUrl(request.getCredentialUrl())
                .resume(resume)
                .doesNotExpire(request.getDoesNotExpire())
                .issueDate(request.getIssueDate())
                .expiryDate(request.getExpiryDate())
                .issuingOrganization(request.getIssuingOrganization())
                .displayOrder(request.getDisplayOrder())
                .build();
        Certification saved = certificationRepo.save(certification);
        return ResumeMapper.toCertificationResponse(saved);
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId))
            throw new Exception("Resume Not Found");
    }

    @Override
    public List<CertificationResponse> getCertifications(Long resumeId) throws Exception {
        Resume resume = resumeService.getResumeByEntity(resumeId);
        return certificationRepo.findByResumeIdOrderByDisplayOrderAsc(resumeId).stream().map(ResumeMapper::toCertificationResponse).toList();
    }

    @Override
    public CertificationResponse updateCertification(Long certificationId, Long resumeId, Long candidateId, AddCertificationRequest request) throws Exception {
        Certification certification = certificationRepo.findById(certificationId).orElseThrow(() -> new Exception("Certification Not Found"));
        certification.setCertificationName(request.getCertificationName());
        certification.setDescription(request.getDescription());
        certification.setCredentialId(request.getCredentialId());
        certification.setCredentialUrl(request.getCredentialUrl());
        certification.setExpiryDate(request.getExpiryDate());
        certification.setDoesNotExpire(request.getDoesNotExpire());
        certification.setIssueDate(request.getIssueDate());
        Certification updated = certificationRepo.save(certification);
        return ResumeMapper.toCertificationResponse(updated);
    }

    @Override
    public void deleteCertification(Long certificationId, Long resumeId, Long candidateId) throws Exception {
        Certification certification =  certificationRepo.findById(certificationId).orElseThrow(() -> new Exception("Resume Not Found"));
        assertOwner(certification.getResume(),candidateId);
        certificationRepo.delete(certification);
    }
}
