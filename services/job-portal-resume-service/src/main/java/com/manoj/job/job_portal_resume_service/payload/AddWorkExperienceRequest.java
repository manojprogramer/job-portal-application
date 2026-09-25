package com.manoj.job.job_portal_resume_service.payload;

import com.manoj.job.domain.JobType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddWorkExperienceRequest {
    @NotBlank(message = "company name is required")
    private String companyName;

    private String companyLogoUrl;

    @NotBlank(message = "JobTitle is Required")
    private String jobTitle;

    private JobType employmentType;
    private String location;

    @NotBlank(message = "Start Date is Required")
    private LocalDate startDate;
    private LocalDate endDate;

    private Boolean isCurrentJob = false;


    private String description;
    private List<String> technologies;
    private Integer displayOrder;


}
