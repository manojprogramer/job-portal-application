package com.manoj.job.dto.response;

import com.manoj.job.domain.ProficiencyLevel;
import jakarta.annotation.security.DenyAll;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeSkillResponse {
    private Long id;
    private String skillName;
    private ProficiencyLevel proficiencyLevel;
    private Integer yearsOfExperience;
    private Integer displayOrder;
}
