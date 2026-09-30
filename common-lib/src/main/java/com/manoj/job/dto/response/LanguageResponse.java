package com.manoj.job.dto.response;


import com.manoj.job.domain.LanguageProficiencyLevel;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageResponse {
    private Long id;
    private String languageName;
    private LanguageProficiencyLevel languageProficiencyLevel;
    private Integer displayOrder;

}
