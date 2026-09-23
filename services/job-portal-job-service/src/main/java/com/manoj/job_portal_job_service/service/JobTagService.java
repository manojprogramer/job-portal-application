package com.manoj.job_portal_job_service.service;

import com.manoj.job.dto.response.JobTagResponse;
import com.manoj.job_portal_job_service.model.JobTag;
import com.manoj.job_portal_job_service.payload.JobTagRequest;

import java.util.List;
import java.util.Set;

public interface JobTagService {
    JobTagResponse createTag(JobTagRequest request) throws Exception;
    List<JobTagResponse> getAllTags();
    JobTagResponse getTagById(Long id) throws Exception;
    JobTagResponse updateTag(Long id, JobTagRequest request) throws Exception;
    void delete(Long id) throws Exception;
    JobTag getTagEntityById(Long id) throws Exception;
    Set<JobTag> getTagsByIds(Set<Long> ids);
}
