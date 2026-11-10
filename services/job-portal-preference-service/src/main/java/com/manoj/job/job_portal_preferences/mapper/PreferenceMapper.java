package com.manoj.job.job_portal_preferences.mapper;

import com.manoj.job.dto.response.SavedJobResponse;
import com.manoj.job.job_portal_preferences.model.SavedJob;

public class PreferenceMapper {
    public static SavedJobResponse toSavedJobResponse(SavedJob savedJob){
        return SavedJobResponse.builder()
                .id(savedJob.getId())
                .candidateId(savedJob.getCandidateId())
                .jobId(savedJob.getJobId())
                .savedAt(savedJob.getSavedAt())
                .build();
    }
}
