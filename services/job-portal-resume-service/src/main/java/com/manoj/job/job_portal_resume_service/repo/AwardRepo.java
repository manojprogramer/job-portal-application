package com.manoj.job.job_portal_resume_service.repo;

import com.manoj.job.dto.response.AwardResponse;
import com.manoj.job.job_portal_resume_service.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepo extends JpaRepository<Award,Long> {

    List<Award> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);
}
