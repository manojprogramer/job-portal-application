package com.manoj.job.job_portal_resume_service.repo;

import com.manoj.job.job_portal_resume_service.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepo extends JpaRepository<Education,Long> {
    List<Education> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);
}

