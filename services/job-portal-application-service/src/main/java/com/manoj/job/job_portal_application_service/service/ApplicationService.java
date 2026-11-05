package com.manoj.job.job_portal_application_service.service;

import com.manoj.job.domain.ApplicationStatus;
import com.manoj.job.dto.response.ApplicationResponse;
import com.manoj.job.job_portal_application_service.model.Application;
import com.manoj.job.job_portal_application_service.payload.CreateApplicationRequest;

import java.util.List;

public interface ApplicationService {
    ApplicationResponse createApplication(Long candidateId, CreateApplicationRequest request);

    ApplicationResponse getApplicationById(Long id);

    List<ApplicationResponse> getMyApplications(Long candidateId);

    List<ApplicationResponse> getApplicationsForJob(Long jobId);

    List<ApplicationResponse> getApplicationsForCompany(Long companyId);

    ApplicationResponse updateStatus(Long applicationId,
                                     Long employerId,
                                     ApplicationStatus status);

    ApplicationResponse withDraw(Long applicationId, Long candidateId);

    ApplicationResponse toggleStar(Long applicationId, Long employerId);

    Application getApplicationEntity(Long id);

    void deleteApplication(Long applicationId,
                           Long candidateId);

}
