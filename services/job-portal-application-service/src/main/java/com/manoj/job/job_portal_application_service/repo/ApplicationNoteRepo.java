package com.manoj.job.job_portal_application_service.repo;

import com.manoj.job.job_portal_application_service.model.ApplicationNote;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationNoteRepo extends JpaRepository<ApplicationNote,Long> {
    List<ApplicationNote> findByApplicationId(Long applicationId);
}
