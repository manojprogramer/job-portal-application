package com.manoj.job.dto.response;

import com.manoj.job.domain.ProficiencyLevel;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobCategoryResponse {
    private Long id;
    private String name;
    private String description;
    private String slug;
    private String iconUrl;
    private Boolean active;

    private Long parentId;
    private String parentName;

    private List<JobCategoryResponse> subCategories;
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResumeSkillResponse {
        private Long id;
        private String skillName;
        private ProficiencyLevel proficiencyLevel;
        private Integer yearOfExperience;
        private Integer displayOrder;
    }
}
