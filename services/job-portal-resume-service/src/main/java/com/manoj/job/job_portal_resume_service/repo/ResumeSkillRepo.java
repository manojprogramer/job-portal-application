package com.manoj.job.job_portal_resume_service.repo;

import com.manoj.job.job_portal_resume_service.model.ResumeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ResumeSkillRepo extends JpaRepository<ResumeSkill,Long> {
    List<ResumeSkill> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);

}
