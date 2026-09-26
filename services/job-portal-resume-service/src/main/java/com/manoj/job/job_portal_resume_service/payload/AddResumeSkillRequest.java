package com.manoj.job.job_portal_resume_service.payload;

import com.manoj.job.domain.ProficiencyLevel;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddResumeSkillRequest {
    @NotBlank(message = "Skill Name is required")
    @Size(max = 100, message = "Skill Name must Not exceed 100 characters")
    private String skillName;

    @NotNull(message = "Proficiency level is required")
    private ProficiencyLevel proficiencyLevel;

    @Min(value = 0, message = "Years of Experience must not be negative")
    private Integer yearsOfExperience;

    private Integer displayOrder;
}
