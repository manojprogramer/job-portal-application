package com.manoj.job.job_portal_ai_service.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import java.util.List;

@Data
public class SkillGapRequest {

    @NotBlank(message = "Job Title is required")
    private String jobTitle;

    private List<String> candidateSkills;
    private List<String> requiredSkills;
}
