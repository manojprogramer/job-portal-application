package com.manoj.job.job_portal_ai_service.service;

import com.manoj.job.job_portal_ai_service.payload.AiTextResponse;
import com.manoj.job.job_portal_ai_service.payload.JobDescriptionRequest;
import com.manoj.job.job_portal_ai_service.payload.SalaryRangeRequest;
import com.manoj.job.job_portal_ai_service.payload.SalaryRangeResponse;

public interface JobAiService {
    AiTextResponse generateJobDescription(JobDescriptionRequest request) throws Exception;
    AiTextResponse generateJobRequirements(String title, String category) throws Exception;
    SalaryRangeResponse suggestSalaryRange(SalaryRangeRequest request) throws Exception;
    AiTextResponse generateJobResponsibilities(String title, String category) throws Exception;
    AiTextResponse generateJobBenefits(String title, String category, String jobType) throws Exception;
    AiTextResponse generateSkillsForJobs(String jobTitle, String description) throws Exception;
    AiTextResponse generateTagsForJobs(String jobTitle, String description) throws Exception;
}
