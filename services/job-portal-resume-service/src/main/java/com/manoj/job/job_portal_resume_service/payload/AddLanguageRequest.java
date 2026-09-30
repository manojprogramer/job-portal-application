package com.manoj.job.job_portal_resume_service.payload;

import com.manoj.job.domain.LanguageProficiencyLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddLanguageRequest {
    @NotBlank(message = "Language name is required")
    private String languageName;

    @NotNull(message = "Proficiency Level is required")
    private LanguageProficiencyLevel languageProficiencyLevel;

    private Integer displayOrder;
}
