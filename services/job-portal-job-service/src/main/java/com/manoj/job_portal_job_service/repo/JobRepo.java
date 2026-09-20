package com.manoj.job_portal_job_service.repo;

import com.manoj.job_portal_job_service.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Job,Long>, JpaSpecificationExecutor<Job> {
    List<Job> findByCompanyId(Long companyId);
}
