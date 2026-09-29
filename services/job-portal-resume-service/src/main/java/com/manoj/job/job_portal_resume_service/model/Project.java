package com.manoj.job.job_portal_resume_service.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Resume resume;

    @Column(nullable = false)
    private String title;

    private String description;

    @ElementCollection
    private List<String> technologies = new ArrayList<>();

    private String projectUrl;
    private Integer displayOrder = 0;

    private String sourceCodeUrl;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isOnGoing = false;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
