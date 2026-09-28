package com.manoj.job.job_portal_resume_service.service.impl;

import com.manoj.job.dto.response.EducationResponse;
import com.manoj.job.job_portal_resume_service.model.Education;
import com.manoj.job.job_portal_resume_service.model.Resume;
import com.manoj.job.job_portal_resume_service.payload.AddEducationRequest;
import com.manoj.job.job_portal_resume_service.repo.EducationRepo;
import com.manoj.job.job_portal_resume_service.service.EducationService;
import com.manoj.job.job_portal_resume_service.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationRepo educationRepo;

    @Autowired
    private ResumeService resumeService;

    @Override
    public EducationResponse addEducation(Long resumeId, Long candidateId, AddEducationRequest request) throws Exception {
        Resume resume = resumeService.getResumeByEntity(resumeId);
        assertOwner(resume,candidateId);

        Education education = Education.builder()
                .resume(resume)
                .institutionName(request.getInstitutionName())
                .degree(request.getDegree())
                .fieldOfStudy(request.getFieldOfStudy())
                .grade(request.getGrade())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isCurrentlyStudying(request.getIsCurrentlyStudying())
                .description(request.getDescription())
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();


        return null;
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId))
            throw new Exception("resume Not Found");
    }

    @Override
    public List<EducationResponse> getEducations(Long resumeId) {
        return List.of();
    }

    @Override
    public EducationResponse updateEducation(Long educationId, Long resumeId, Long candidateId, AddEducationRequest request) {
        return null;
    }

    @Override
    public void deleteEducation(Long educationId, Long resumeId, Long candidateId) {

    }
}
