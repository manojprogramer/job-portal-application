package com.manoj.job_portal_job_service.repo;

import com.manoj.job_portal_job_service.model.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobCategoryRepo extends JpaRepository<JobCategory,Long> {
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
    List<JobCategory> findByActiveTrue();
}
