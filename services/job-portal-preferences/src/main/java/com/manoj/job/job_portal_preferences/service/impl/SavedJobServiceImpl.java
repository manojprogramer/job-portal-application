package com.manoj.job.job_portal_preferences.service.impl;

import com.manoj.job.dto.response.SavedJobResponse;
import com.manoj.job.job_portal_preferences.mapper.PreferenceMapper;
import com.manoj.job.job_portal_preferences.model.SavedJob;
import com.manoj.job.job_portal_preferences.payload.SavedJobRequest;
import com.manoj.job.job_portal_preferences.repo.SavedJobRepo;
import com.manoj.job.job_portal_preferences.service.SavedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedJobServiceImpl implements SavedJobService {

    @Autowired
    private SavedJobRepo savedJobRepo;

    @Override
    public SavedJobResponse saveJob(Long candidateId, SavedJobRequest request) throws Exception {
        if(isSaved(candidateId,request.getJobId()))
            throw new Exception("Job Already Exists");
        SavedJob savedJob = SavedJob.builder()
                .candidateId(candidateId)
                .jobId(request.getJobId())
                .build();
        SavedJob save = savedJobRepo.save(savedJob);
        return PreferenceMapper.toSavedJobResponse(save);
    }

    @Override
    public void unSaveJob(Long candidateId, Long saveJobId) throws Exception {
        SavedJob savedJob = savedJobRepo.findByJobId(saveJobId);
        if(!savedJob.getCandidateId().equals(candidateId))
            throw new Exception("Job Not Saved");
        savedJobRepo.delete(savedJob);
    }

    @Override
    public List<SavedJobResponse> getSavedJob(Long candidateId) {
        return savedJobRepo.findByCandidateId(candidateId).stream().map(PreferenceMapper::toSavedJobResponse).toList();
    }

    @Override
    public boolean isSaved(Long candidateId, Long jobId) {
        return savedJobRepo.existsByCandidateIdAndJobId(candidateId,jobId);
    }
}
