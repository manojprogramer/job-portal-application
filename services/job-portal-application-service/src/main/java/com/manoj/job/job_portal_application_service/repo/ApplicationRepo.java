package com.manoj.job.job_portal_application_service.repo;

import com.manoj.job.job_portal_application_service.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application,Long>, JpaSpecificationExecutor<Application> {

    List<Application> findByCandidateId(Long candidateId);
    List<Application> findByJobId(Long jobId);
    boolean existsByCandidateIdAndJobId(Long candidateId, Long jobId);
}
