package com.manoj.job.job_portal_resume_service.model;

import com.manoj.job.domain.ResumeTemplate;
import com.manoj.job.domain.ResumeVisibility;
import com.manoj.job.job_portal_resume_service.model.embeddable.PersonalInfo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long candidateId;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ResumeTemplate template = ResumeTemplate.PROFESSIONAL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ResumeVisibility resumeVisibility = ResumeVisibility.PUBLIC;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isDefault = false;

    @Builder.Default
    private Integer completionScore = 0;

    @Embedded
    private PersonalInfo personalInfo;

    private String summary;

    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
