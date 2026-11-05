package com.manoj.job.job_portal_application_service.service.impl;

import com.manoj.job.domain.ApplicationStatus;
import com.manoj.job.dto.response.ApplicationResponse;
import com.manoj.job.job_portal_application_service.model.Application;
import com.manoj.job.job_portal_application_service.payload.CreateApplicationRequest;
import com.manoj.job.job_portal_application_service.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Override
    public ApplicationResponse createApplication(Long candidateId, CreateApplicationRequest request) {
        return null;
    }

    @Override
    public ApplicationResponse getApplicationById(Long id) {
        return null;
    }

    @Override
    public List<ApplicationResponse> getMyApplications(Long candidateId) {
        return List.of();
    }

    @Override
    public List<ApplicationResponse> getApplicationsForJob(Long jobId) {
        return List.of();
    }

    @Override
    public List<ApplicationResponse> getApplicationsForCompany(Long companyId) {
        return List.of();
    }

    @Override
    public ApplicationResponse updateStatus(Long applicationId, Long employerId, ApplicationStatus status) {
        return null;
    }

    @Override
    public ApplicationResponse withDraw(Long applicationId, Long candidateId) {
        return null;
    }

    @Override
    public ApplicationResponse toggleStar(Long applicationId, Long employerId) {
        return null;
    }

    @Override
    public Application getApplicationEntity(Long id) {
        return null;
    }

    @Override
    public void deleteApplication(Long applicationId, Long candidateId) {

    }
}
