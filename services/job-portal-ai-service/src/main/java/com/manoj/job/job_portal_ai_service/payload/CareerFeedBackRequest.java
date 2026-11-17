package com.manoj.job.job_portal_ai_service.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CareerFeedBackRequest {

    @NotBlank(message = "Resume content is required")
    private String resumeContent;

//    Optional - if provided, feedback is tailored toward this role
    private String targetJobTitle;
}
