package com.manoj.job_portal_job_service.repo;

import com.manoj.job_portal_job_service.model.JobTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTagRepo extends JpaRepository<JobTag, Long> {
    Boolean existsByName(String name);
    Boolean existsBySlug(String slug);
}
