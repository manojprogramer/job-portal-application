package com.manoj.job.job_portal_ai_service.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SearchEnhancementRequest {
    @NotBlank(message = "query is required to search jobs")
    private String query;
}
