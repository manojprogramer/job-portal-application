package com.manoj.job.job_portal_resume_service.repo;

import com.manoj.job.job_portal_resume_service.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepo extends JpaRepository<Resume,Long> {
    List<Resume> findByCandidateIdAndIsActiveTrue(Long candidateId);

    Optional<Resume> findByCandidateIdAndIsDefaultTrue(Long candidateId);
}
