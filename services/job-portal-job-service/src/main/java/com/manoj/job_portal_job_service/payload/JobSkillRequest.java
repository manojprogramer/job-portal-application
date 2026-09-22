package com.manoj.job_portal_job_service.payload;

import com.manoj.job.domain.SkillCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobSkillRequest {
    @NotBlank(message = "Skill is Required")
    @Size(max = 100, message = "Name must not be exceed 100 characters")
    private  String name;

    @NotBlank(message = "Skill Category must be required")
    private SkillCategory skillCategory;
}
