package com.manoj.job.job_portal_resume_service.service.impl;

import com.manoj.job.dto.response.LanguageResponse;
import com.manoj.job.job_portal_resume_service.mapper.ResumeMapper;
import com.manoj.job.job_portal_resume_service.model.Language;
import com.manoj.job.job_portal_resume_service.model.Resume;
import com.manoj.job.job_portal_resume_service.payload.AddLanguageRequest;
import com.manoj.job.job_portal_resume_service.repo.LanguageRepo;
import com.manoj.job.job_portal_resume_service.service.LanguageService;
import com.manoj.job.job_portal_resume_service.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    private LanguageRepo languageRepo;

    private ResumeService resumeService;

    @Override
    public LanguageResponse addLanguage(Long resumeId, Long candidateId, AddLanguageRequest request) throws Exception {
        Resume resume = resumeService.getResumeByEntity(resumeId);
        assertOwner(resume,candidateId);
        Language language = Language.builder()
                .resume(resume)
                .languageName(request.getLanguageName())
                .proficiencyLevel(request.getLanguageProficiencyLevel())
                .displayOrder(request.getDisplayOrder())
                .build();
        Language saved = languageRepo.save(language);
        return ResumeMapper.toLanguageResponse(saved);
    }

    @Override
    public List<LanguageResponse> getLanguages(Long resumeId) {
        return languageRepo.findByResumeIdOrderByDisplayOrderAsc(resumeId).stream()
                .map(ResumeMapper::toLanguageResponse).toList();
    }

    @Override
    public LanguageResponse updateLanguage(Long languageId, Long resumeId, Long candidateId, AddLanguageRequest request) throws Exception {
        Language language = languageRepo.findById(languageId).orElseThrow(() -> new Exception("Language Not Found"));
        assertOwner(language.getResume(),candidateId);
        language.setLanguageName(request.getLanguageName());
        language.setProficiencyLevel(request.getLanguageProficiencyLevel());
        if(request.getDisplayOrder() != null) language.setDisplayOrder(request.getDisplayOrder());
        Language updated = languageRepo.save(language);
        return ResumeMapper.toLanguageResponse(updated);
    }

    @Override
    public void deleteLanguage(Long languageId, Long resumeId, Long candidateId) throws Exception {
        Language language = languageRepo.findById(languageId).orElseThrow(() -> new Exception("Language Not Found"));
        assertOwner(language.getResume(),candidateId);
        languageRepo.delete(language);
    }
    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId))
            throw new Exception("resume not Found");
    }
}
