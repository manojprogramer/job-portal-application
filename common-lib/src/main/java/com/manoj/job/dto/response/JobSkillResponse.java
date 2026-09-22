package com.manoj.job.dto.response;

import com.manoj.job.domain.SkillCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSkillResponse {
    private Long id;
    private String name;
    private String slug;
    private SkillCategory skillCategory;
    private Boolean active;
}
