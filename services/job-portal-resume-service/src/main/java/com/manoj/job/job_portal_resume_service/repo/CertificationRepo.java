package com.manoj.job.job_portal_resume_service.repo;

import com.manoj.job.job_portal_resume_service.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationRepo extends JpaRepository<Certification,Long> {
    List<Certification> findByResumeIdOrderByDisplayOrderAsc(Long id);
}
