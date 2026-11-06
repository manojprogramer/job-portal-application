package com.manoj.job.job_portal_application_service.payload;

import com.manoj.job.domain.AiShortListStatus;
import com.manoj.job.domain.ApplicationStatus;
import lombok.Data;

@Data
public class CompanyApplicationFilterRequest {

    private Long jobId;

    private ApplicationStatus applicationStatus;

    private Boolean isStarred;

    private AiShortListStatus aiShortListStatus;

    private Integer minAiScore;

    private String sortBy;
}
