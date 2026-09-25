package com.manoj.job.dto.response;

import com.manoj.job.domain.ResumeTemplate;
import com.manoj.job.domain.ResumeVisibility;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeResponse {
    private Long id;
    private Long candidateId;
    private String title;
    private ResumeTemplate resumeTemplate;
    private ResumeVisibility resumeVisibility;
    private Boolean isDefault;
    private PersonalInfoResponse personalInfo;
    private String summary;
    private Integer completionScore;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//    private List<WorkExperienceResponse> workExperienceResponse;
//    private List<EducationResponse> educationResponse;
//    private List<ResumeSkillResponse> resumeSkillResponse;
//    private List<ProjectResponse> projectResponse;
//    private List<CertificationResponse> certificationResponse;
//    private List<AwardResponse> awardResponse;
//    private List<LanguageResponse> languageResponse;
}
