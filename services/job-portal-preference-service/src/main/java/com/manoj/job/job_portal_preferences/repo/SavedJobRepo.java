package com.manoj.job.job_portal_preferences.repo;

import com.manoj.job.job_portal_preferences.model.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedJobRepo extends JpaRepository<SavedJob,Long> {
    List<SavedJob> findByCandidateId(Long candidateId);
    boolean existsByCandidateIdAndJobId(Long candidateId, Long jobId);

    SavedJob findByJobId(Long saveJobId);
}
