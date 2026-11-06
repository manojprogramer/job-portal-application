package com.manoj.job.job_portal_application_service.service;

import com.manoj.job.domain.ApplicationStatus;
import com.manoj.job.dto.response.ApplicationResponse;
import com.manoj.job.job_portal_application_service.model.Application;
import com.manoj.job.job_portal_application_service.payload.CompanyApplicationFilterRequest;
import com.manoj.job.job_portal_application_service.payload.CreateApplicationRequest;
import com.manoj.job.job_portal_application_service.payload.WithDrawnApplicationRequest;

import java.util.List;

public interface ApplicationService {
    ApplicationResponse createApplication(Long candidateId, CreateApplicationRequest request) throws Exception;

    ApplicationResponse getApplicationById(Long id) throws Exception;

    List<ApplicationResponse> getMyApplications(Long candidateId);

    List<ApplicationResponse> getApplicationsForJob(Long jobId);

    List<ApplicationResponse> getApplicationsForCompany(Long userId, CompanyApplicationFilterRequest request);

    ApplicationResponse updateStatus(Long applicationId,
                                     Long employerId,
                                     ApplicationStatus status) throws Exception;

    ApplicationResponse withDraw(Long applicationId, Long candidateId, WithDrawnApplicationRequest applicationRequest) throws Exception;

    ApplicationResponse toggleStar(Long applicationId, Long employerId) throws Exception;

    Application getApplicationEntity(Long id) throws Exception;

    void deleteApplication(Long applicationId,
                           Long candidateId) throws Exception;

}
