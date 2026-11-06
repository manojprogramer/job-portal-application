package com.manoj.job.job_portal_application_service.repo;

import com.manoj.job.domain.AiShortListStatus;
import com.manoj.job.domain.ApplicationStatus;
import com.manoj.job.job_portal_application_service.model.Application;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class ApplicationSpecification {
    public static Specification<Application> forCompanyWithFilters(
            Long companyId,
            Long jobId,
            ApplicationStatus status,
            boolean isStarred,
            AiShortListStatus aiShortListStatus,
            Integer minAiScore
    ){
        return (root,query,cb)-> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(cb.equal(root.get("companyId"), companyId));
            if (jobId != null) predicateList.add(cb.equal(root.get("jobId"), jobId));
            if (status != null) predicateList.add(cb.equal(root.get("status"), status));
            if (isStarred) predicateList.add(cb.equal(root.get("isStarred"), isStarred));
            if (aiShortListStatus != null)
                predicateList.add(cb.equal(root.get("aiShortListStatus"), aiShortListStatus));
            if (minAiScore != null) predicateList.add(cb.equal(root.get("aiScore"), minAiScore));
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
