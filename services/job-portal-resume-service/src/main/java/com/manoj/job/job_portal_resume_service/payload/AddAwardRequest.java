package com.manoj.job.job_portal_resume_service.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddAwardRequest {

    @NotBlank(message = "Award title is required")
    private String awardTitle;

    @NotBlank(message = "Issuing organization is required")
    private String issuingOrganization;

    @NotNull(message = "Award date is required")
    private LocalDate awardDate;

    private String description;

    private String awardUrl;

    private Integer displayOrder;

}