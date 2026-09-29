package com.manoj.job.job_portal_resume_service.repo;

import com.manoj.job.job_portal_resume_service.model.Project;
import com.manoj.job.job_portal_resume_service.service.ProjectService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Long> {
    List<Project> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);
}
