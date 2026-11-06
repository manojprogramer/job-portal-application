package com.manoj.job.job_portal_application_service.payload;

import com.manoj.job.domain.ApplicationStatus;
import lombok.Data;

@Data
public class UpdateApplicationStatusRequest {
    private ApplicationStatus status;
}
