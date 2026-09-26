package com.manoj.job.job_portal_resume_service.repo;

import com.manoj.job.job_portal_resume_service.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceRepo extends JpaRepository<WorkExperience, Long> {
    List<WorkExperience> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);
}
