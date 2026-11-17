package com.manoj.job.job_portal_ai_service.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WorkExperienceBulletRequest {

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    private String company;

    @NotBlank(message = "Raw Description is required")
    private String rawDescription;

    private String achievementsHint;
}
