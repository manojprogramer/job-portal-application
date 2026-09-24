package com.manoj.job.job_portal_resume_service.model;

import com.manoj.job.domain.ResumeTemplate;
import com.manoj.job.domain.ResumeVisibility;
import com.manoj.job.job_portal_resume_service.model.embeddable.PersonalInfo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long candidateId;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResumeTemplate template = ResumeTemplate.PROFESSIONAL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResumeVisibility resumeVisibility = ResumeVisibility.PUBLIC;

    @Column(nullable = false)
    private Boolean isDefault = false;

    private PersonalInfo personalInfo;
}
