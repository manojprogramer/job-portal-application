package com.manoj.job.job_portal_application_service.service.impl;

import com.manoj.job.dto.response.ApplicationNoteResponse;
import com.manoj.job.dto.response.ApplicationResponse;
import com.manoj.job.job_portal_application_service.mapper.ApplicationMapper;
import com.manoj.job.job_portal_application_service.model.Application;
import com.manoj.job.job_portal_application_service.model.ApplicationNote;
import com.manoj.job.job_portal_application_service.payload.AddApplicationNoteRequest;
import com.manoj.job.job_portal_application_service.repo.ApplicationNoteRepo;
import com.manoj.job.job_portal_application_service.service.ApplicationNoteService;
import com.manoj.job.job_portal_application_service.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationNoteImpl implements ApplicationNoteService {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationNoteRepo applicationNoteRepo;

    @Override
    public ApplicationNoteResponse addNote(Long applicationId, Long employerId, AddApplicationNoteRequest request) throws Exception {
        Application application = applicationService.getApplicationEntity(applicationId);
        assertEmployer(application,employerId);
        ApplicationNote applicationNote = ApplicationNote.builder()
                .application(application)
                .addedByUserId(employerId)
                .content(request.getContent())
                .build();
        ApplicationNote savedNote = applicationNoteRepo.save(applicationNote);
        return ApplicationMapper.toNoteResponse(savedNote);
    }

    private void assertEmployer(Application application, Long employerId) throws Exception {
        if(!application.getEmployerId().equals(employerId))
            throw new Exception("You are not the employer of the application");
    }

    @Override
    public List<ApplicationNoteResponse> getNotesByApplication(Long applicationId, Long employerId) {
        return applicationNoteRepo.findByApplicationId(applicationId).stream().map(ApplicationMapper::toNoteResponse).toList();
    }

    @Override
    public void deleteNote(Long applicationId, Long noteId, Long employerId) throws Exception {
        Application application = applicationService.getApplicationEntity(applicationId);
        assertEmployer(application,employerId);
        ApplicationNote applicationNote = applicationNoteRepo.findById(noteId)
                .orElseThrow(() -> new Exception("Note does not belong to this application"));
        applicationNoteRepo.delete(applicationNote);


    }
}
