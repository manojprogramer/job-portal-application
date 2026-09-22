package com.manoj.job_portal_job_service.service;

import com.manoj.job_portal_job_service.model.JobSkill;
import com.manoj.job_portal_job_service.payload.JobSkillRequest;
import com.manoj.job.dto.response.JobSkillResponse;

import java.util.List;
import java.util.Set;

public interface JobSkillService {
    JobSkillResponse createSkill(JobSkillRequest request) throws Exception;
    List<JobSkillResponse> getAllSkills();
    JobSkillResponse getSkillById(Long id) throws Exception;
    JobSkillResponse updateSkill(Long id, JobSkillRequest request) throws Exception;
    void deleteSkill(Long id) throws Exception;
    Set<JobSkill> getSkillsById(Set<Long> ids);
}

