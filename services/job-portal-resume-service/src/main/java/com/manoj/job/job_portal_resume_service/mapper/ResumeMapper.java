package com.manoj.job.job_portal_resume_service.mapper;

import com.manoj.job.dto.response.*;
import com.manoj.job.job_portal_resume_service.model.*;
import com.manoj.job.job_portal_resume_service.model.embeddable.PersonalInfo;

public class ResumeMapper {
    public static PersonalInfoResponse toPersonalInfoResponse(PersonalInfo personalInfo){
        if(personalInfo == null) return null;
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
    public static ResumeSkillResponse toSkillResponse(ResumeSkill skill){
        if(skill == null) return null;
        return ResumeSkillResponse.builder()
                .id(skill.getId())
                .skillName(skill.getSkillName())
                .proficiencyLevel(skill.getProficiencyLevel())
                .yearsOfExperience(skill.getYearsOfExperience())
                .displayOrder(skill.getDisplayOrder())
                .build();
    }
    public static EducationResponse toEducationResponse(Education education){
        if (education == null) return null;
        return EducationResponse.builder()
                .id(education.getId())
                .institutionName(education.getInstitutionName())
                .degree(education.getDegree())
                .fieldOfStudy(education.getFieldOfStudy())
                .grade(education.getGrade())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .isCurrentStudying(education.getIsCurrentlyStudying())
                .description(education.getDescription())
                .displayOrder(education.getDisplayOrder())
                .build();
    }
    public static ProjectResponse toProjectResponse(Project project){
        if(project == null) return  null;
        return ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .technologies(project.getTechnologies())
                .projectUrl(project.getProjectUrl())
                .sourceCodeUrl(project.getSourceCodeUrl())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .isOngoing(project.getIsOnGoing())
                .displayOrder(project.getDisplayOrder())
                .build();
    }
    public static LanguageResponse toLanguageResponse(Language language){
        if(language == null) return null;
        return LanguageResponse.builder()
                .id(language.getId())
                .languageName(language.getLanguageName())
                .languageProficiencyLevel(language.getProficiencyLevel())
                .displayOrder(language.getDisplayOrder())
                .build();
    }

}
