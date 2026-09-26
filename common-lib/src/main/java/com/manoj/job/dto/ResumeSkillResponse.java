package com.manoj.job.dto;

import com.manoj.job.domain.ProficiencyLevel;
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
    private Integer yearOfExperience;
    private Integer displayOrder;
}
