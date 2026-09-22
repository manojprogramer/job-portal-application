package com.manoj.job_portal_job_service.repo;

import com.manoj.job_portal_job_service.model.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSkillRepo extends JpaRepository<JobSkill,Long> {
    List<JobSkill> findByActiveTrue();
    Boolean existsByName(String name);
    Boolean existsBySlug(String slug);
}
