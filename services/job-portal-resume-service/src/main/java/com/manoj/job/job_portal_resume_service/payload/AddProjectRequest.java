package com.manoj.job.job_portal_resume_service.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddProjectRequest {
    @NotBlank(message = "Project Title is required")
    private String title;

    private String description;
    private List<String> technologies;
    @Pattern(regexp = "^(https?://).*", message = "Project URL must be valid")
    private String projectUrl;

    @Pattern(regexp = "^(https?://).*", message = "Source Code URL must be Valid")
    private String sourceCodeUrl;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Builder.Default
    private Boolean isOnGoing = false;
    private Integer displayOrder;
}
