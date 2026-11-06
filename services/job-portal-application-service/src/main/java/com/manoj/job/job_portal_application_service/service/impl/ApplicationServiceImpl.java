package com.manoj.job.job_portal_application_service.service.impl;

import com.manoj.job.domain.ApplicationStatus;
import com.manoj.job.dto.response.ApplicationResponse;
import com.manoj.job.dto.response.CompanyResponse;
import com.manoj.job.dto.response.JobResponse;
import com.manoj.job.dto.response.UserResponse;
import com.manoj.job.job_portal_application_service.mapper.ApplicationMapper;
import com.manoj.job.job_portal_application_service.model.Application;
import com.manoj.job.job_portal_application_service.payload.CompanyApplicationFilterRequest;
import com.manoj.job.job_portal_application_service.payload.CreateApplicationRequest;
import com.manoj.job.job_portal_application_service.payload.WithDrawnApplicationRequest;
import com.manoj.job.job_portal_application_service.repo.ApplicationRepo;
import com.manoj.job.job_portal_application_service.repo.ApplicationSpecification;
import com.manoj.job.job_portal_application_service.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepo applicationRepo;

    @Override
    public ApplicationResponse createApplication(Long candidateId, CreateApplicationRequest request) throws Exception {
        if(applicationRepo.existsByCandidateIdAndJobId(candidateId,request.getJobId()))
            throw new Exception("you have already applied");
        Long companyId = 1L;
        Long employeeId = 1L;

//        todo : fetch job

//        todo : fetch resume

        Application application = ApplicationMapper.toEntity(request,candidateId,companyId,employeeId);

        Application savedApplication = applicationRepo.save(application);

//        todo : AI screening runs in a background thread, no callback needed

        return buildFullResponse(application);
    }

    @Override
    public ApplicationResponse getApplicationById(Long id) throws Exception {
        Application application = getApplicationEntity(id);
        return buildFullResponse(application);
    }

    @Override
    public List<ApplicationResponse> getMyApplications(Long candidateId) {
        return applicationRepo.findByCandidateId(candidateId).stream().map(this::buildFullResponse).toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationsForJob(Long jobId) {
        return applicationRepo.findByJobId(jobId).stream().map(this::buildFullResponse).toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationsForCompany(Long userId, CompanyApplicationFilterRequest filter) {

//        todo : fetch company by ownerId
        Long companyId = 1L;
        Sort sort = buildSort(filter.getSortBy());
        return applicationRepo.findAll(ApplicationSpecification.forCompanyWithFilters(
                companyId,
                filter.getJobId(),
                filter.getApplicationStatus(),
                filter.getIsStarred(),
                filter.getAiShortListStatus(),
                filter.getMinAiScore()
        ),sort).stream().map(this::buildFullResponse).toList();
    }



    @Override
    public ApplicationResponse updateStatus(Long applicationId, Long employerId, ApplicationStatus status) throws Exception {
        Application application = getApplicationEntity(applicationId);
        assertEmployer(application,employerId);
        if(application.getStatus() == ApplicationStatus.WITHDRAWN)
            throw new Exception("Candidate have already withdrawn");
        application.setStatus(status);
        Application savedApplication = applicationRepo.save(application);
        return buildFullResponse(savedApplication);
    }

    @Override
    public ApplicationResponse withDraw(Long applicationId, Long candidateId, WithDrawnApplicationRequest request) throws Exception {
        Application application = getApplicationEntity(applicationId);
        assertCandidate(application,candidateId);
        application.setStatus(ApplicationStatus.WITHDRAWN);
        application.setWithdrawnReason(request.getReason());
        Application savedApplication = applicationRepo.save(application);
        return buildFullResponse(savedApplication);
    }

    @Override
    public ApplicationResponse toggleStar(Long applicationId, Long employerId) throws Exception {
        Application application = getApplicationEntity(applicationId);
        assertEmployer(application,employerId);

        application.setIsStarred(!application.getIsStarred());
        Application savedApplication = applicationRepo.save(application);
        return buildFullResponse(savedApplication);
    }

    @Override
    public Application getApplicationEntity(Long id) throws Exception {
        return applicationRepo.findById(id).orElseThrow(() -> new Exception("application not found"));
    }

    @Override
    public void deleteApplication(Long applicationId, Long candidateId) throws Exception {
        Application application = getApplicationEntity(applicationId);
        assertCandidate(application,candidateId);
        applicationRepo.delete(application);

    }

    public ApplicationResponse buildFullResponse(Application application){
        JobResponse jobResponse = JobResponse.builder().id(application.getId()).build();
        CompanyResponse companyResponse = CompanyResponse.builder().id(application.getCompanyId()).build();
        UserResponse userResponse = UserResponse.builder().id(application.getCandidateId()).build();

        return ApplicationMapper.toResponse(application,
                jobResponse,
                companyResponse,
                userResponse);
    }

    private Sort buildSort(String sortBy) {
        if("AI_SCORE_DESC".equals(sortBy)){
            return Sort.by(Sort.Order.desc("aiScore").with(Sort.NullHandling.NULLS_LAST));
        }
        else if("AI_SCORE_ASC".equals(sortBy)){
            return Sort.by(Sort.Order.asc("aiScore").with(Sort.NullHandling.NULLS_FIRST));
        }
        return Sort.by(Sort.Direction.DESC,"appliedAt");
    }

    private void assertEmployer(Application application, Long employerId) throws Exception {
        if(!application.getEmployerId().equals(employerId))
            throw new Exception("You are not the employer for this application");
    }
    private void assertCandidate(Application application, Long candidateId) throws Exception {
        if(!application.getCandidateId().equals(candidateId))
            throw new Exception("You are not the owner of the application");
    }

}
