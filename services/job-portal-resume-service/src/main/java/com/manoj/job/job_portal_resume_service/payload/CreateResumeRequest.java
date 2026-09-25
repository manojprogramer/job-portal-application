package com.manoj.job.job_portal_resume_service.payload;

import com.manoj.job.domain.ResumeTemplate;
import com.manoj.job.domain.ResumeVisibility;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateResumeRequest {
    @NotBlank(message = "message title is required")
    private String title;
    private ResumeTemplate template;
    private ResumeVisibility resumeVisibility;
    private Boolean isDefault;
}
