package com.manoj.job.job_portal_resume_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "awards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String awardTitle;

    private String issuingOrganization;

    private LocalDate awardDate;

    private String description;

    private String awardUrl;

    private Integer displayOrder;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Resume resume;
}