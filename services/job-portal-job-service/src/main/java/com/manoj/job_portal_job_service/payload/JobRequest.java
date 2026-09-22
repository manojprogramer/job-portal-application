package com.manoj.job_portal_job_service.payload;

import com.manoj.job.domain.ExperienceLevel;
import com.manoj.job.domain.JobType;
import com.manoj.job.domain.WorkMode;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobRequest {
    @NotBlank(message = "job title is required")
    private String title;
    @NotBlank(message = "Job Description is required")
    private String description;

    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;

    private String requirements;
    private String responsibilities;
    private String benefits;

    private Long categoryId;
    private Set<Long> skillIds;
    private Set<Long> tagIds;

    @DecimalMin(value = "0.0",inclusive = true,message = "Minimum Salary Must Not Be Negative")
    private BigDecimal minSalary;
    @DecimalMax(value = "0.0", inclusive = true, message = "Maximum Salary Must Not Be Negative")
    private BigDecimal maxSalary;

    @NotNull(message = "Job Type is Required")
    private JobType jobType;
    @NotNull(message = "Work Mode is required")
    private WorkMode workMode;
    @NotNull(message = "Experience Level is Required")
    private ExperienceLevel experienceLevel;

    @Min(value = 1, message = "Openings must be At Least 1")
    private Integer openings = 1;
    private LocalDate applicationDeadLine;
    private LocalDate expiredAt;
}
