package com.manoj.job.job_portal_application_service.service;

import com.manoj.job.dto.response.ApplicationNoteResponse;
import com.manoj.job.dto.response.ApplicationResponse;
import com.manoj.job.job_portal_application_service.payload.AddApplicationNoteRequest;

import java.util.List;

public interface ApplicationNoteService {
    ApplicationNoteResponse addNote(
            Long applicationId, Long employerId, AddApplicationNoteRequest request) throws Exception;
    List<ApplicationNoteResponse> getNotesByApplication(Long applicationId, Long employerId);
    void deleteNote(Long applicationId, Long noteId, Long employerId) throws Exception;


}
