package com.manoj.job.job_portal_resume_service.model;

import com.manoj.job.domain.JobType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Resume resume;

    @Column(nullable = false)
    private String companyName;

    private String companyLogoUrl;

    @Column(nullable = false)
    private String jobTitle;

    @Enumerated(EnumType.STRING)
    private JobType employmentType;

    private String location;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isCurrentJob = false;

    private String description;

    @ElementCollection
    private List<String> technologiesName = new ArrayList<>();

    @Column(nullable = false)
    private Integer displayOrder = 0;



}
