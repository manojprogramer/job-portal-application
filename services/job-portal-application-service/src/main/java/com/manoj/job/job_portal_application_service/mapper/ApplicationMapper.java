package com.manoj.job.job_portal_application_service.mapper;

import com.manoj.job.dto.response.*;
import com.manoj.job.job_portal_application_service.model.Application;
import com.manoj.job.job_portal_application_service.model.ApplicationNote;
import com.manoj.job.job_portal_application_service.payload.CreateApplicationRequest;

import java.util.List;

public class ApplicationMapper {
    public static Application toEntity(CreateApplicationRequest request,
                                       Long candidateId,
                                       Long companyId,
                                       Long employerId){
        if(request == null) return null;
        return Application.builder()
                .candidateId(candidateId)
                .jobId(request.getJobId())
                .companyId(companyId)
                .employerId(employerId)
                .resumeId(request.getResumeId())
                .coverLetter(request.getCoverLetter())
                .expectedSalary(request.getExpectedSalary())
                .availableFrom(request.getAvailableFrom())
                .build();
    }
    public static ApplicationResponse toResponse(Application application,
//                                                 List<ApplicationNote> notes,
                                                 JobResponse jobResponse,
                                                 CompanyResponse companyResponse,
                                                 UserResponse userResponse
//                                                 ApplicationScreening applicationScreening
                                                 ){
        return ApplicationResponse.builder()
                .id(application.getId())
                .userResponse(userResponse)
                .employerId(application.getEmployerId())
                .jobResponse(jobResponse)
                .companyResponse(companyResponse)
                .status(application.getStatus())
                .resumeId(application.getResumeId())
                .coverLetter(application.getCoverLetter())
                .expectedSalary(application.getExpectedSalary())
                .availableFrom(application.getAvailableFrom())
                .isStarred(application.getIsStarred())
//                .notes(notes)
                .withdrawnAt(application.getWithdrawnAt())
                .withdrawnReason(application.getWithdrawnReason())
                .appliedAt(application.getAppliedAt())
                .updatedAt(application.getUpdatedAt())
//                .screening(toScreeningResponse(screening))
                .build();

    }
    public static ApplicationNoteResponse toNoteResponse(ApplicationNote note)
    {
        return ApplicationNoteResponse.builder()
                .id(note.getId())
                .addedByUserId(note.getAddedByUserId())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .build();
    }

}
