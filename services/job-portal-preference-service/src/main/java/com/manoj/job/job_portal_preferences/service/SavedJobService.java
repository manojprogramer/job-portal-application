package com.manoj.job.job_portal_preferences.service;

import com.manoj.job.dto.response.SavedJobResponse;
import com.manoj.job.job_portal_preferences.payload.SavedJobRequest;

import java.util.List;

public interface SavedJobService {

    SavedJobResponse saveJob(Long candidateId, SavedJobRequest request) throws Exception;
    void unSaveJob(Long candidateId, Long saveJobId) throws Exception;
    List<SavedJobResponse> getSavedJob(Long candidateId);
    boolean isSaved(Long candidateId, Long jobId);
}
