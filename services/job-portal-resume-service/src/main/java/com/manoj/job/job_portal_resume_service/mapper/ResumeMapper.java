package com.manoj.job.job_portal_resume_service.mapper;

import com.manoj.job.dto.response.PersonalInfoResponse;
import com.manoj.job.dto.response.ResumeResponse;
import com.manoj.job.job_portal_resume_service.model.Resume;
import com.manoj.job.job_portal_resume_service.model.embeddable.PersonalInfo;

public class ResumeMapper {
    public static PersonalInfoResponse toPersonalInfoResponse(PersonalInfo personalInfo){
        return PersonalInfoResponse.builder()
                .firstName(personalInfo.getFirstName())
                .lastName(personalInfo.getLastName())
                .email(personalInfo.getEmail())
                .phone(personalInfo.getPhone())
                .headLine(personalInfo.getHeadLine())
                .country(personalInfo.getCountry())
                .city(personalInfo.getCity())
                .githubUrl(personalInfo.getGithubUrl())
                .linkedInUrl(personalInfo.getLinkedInUrl())
                .portfolioUrl(personalInfo.getPortfolioUrl())
                .websiteUrl(personalInfo.getWebsiteUrl())
                .build();
    }
    public static ResumeResponse toResponse(Resume resume){
        if(resume == null) return null;
        return ResumeResponse.builder()
                .id(resume.getId())
                .candidateId(resume.getCandidateId())
                .resumeTemplate(resume.getTemplate())
                .resumeVisibility(resume.getResumeVisibility())
                .isDefault(resume.getIsDefault())
                .personalInfo(ResumeMapper.toPersonalInfoResponse(resume.getPersonalInfo()))
                .summary(resume.getSummary())
                .completionScore(resume.getCompletionScore())
                .createdAt(resume.getCreatedAt())
                .updatedAt(resume.getUpdatedAt())
                .build();

    }
}
