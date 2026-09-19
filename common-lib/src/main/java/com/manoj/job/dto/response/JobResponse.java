package com.manoj.job.dto.response;

import com.manoj.job.domain.ExperienceLevel;
import com.manoj.job.domain.JobStatus;
import com.manoj.job.domain.JobType;
import com.manoj.job.domain.WorkMode;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String requirements;
    private String responsibilities;
    private String benefits;

    private CompanyResponse companyResponse;
    private Long employerId;

//    private JobCategoryReponse jobCategoryReponse;
//    private Set<JobSkillResponse> jobSkillResponse;
//    private Set<JobTagResponse> jobTagResponse;

    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;

    private BigDecimal minSalary;
    private BigDecimal maxSalary;


    private JobType jobType;
    private WorkMode workMode;
    private ExperienceLevel experienceLevel;
    private JobStatus jobStatus;

    private Integer openings;
    private LocalDate applicationDeadline;
    private LocalDate expiresAt;
    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
    private LocalDateTime closedAt;


}
