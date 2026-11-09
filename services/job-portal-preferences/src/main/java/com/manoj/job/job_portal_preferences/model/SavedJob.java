package com.manoj.job.job_portal_preferences.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  Long candidateId;

    @Column(nullable = false)
    private Long jobId;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime savedAt;
}
