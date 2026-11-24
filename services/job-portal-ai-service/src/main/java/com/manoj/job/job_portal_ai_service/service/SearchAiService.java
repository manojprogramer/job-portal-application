package com.manoj.job.job_portal_ai_service.service;

import com.manoj.job.job_portal_ai_service.payload.JobAlertSuggestRequest;
import com.manoj.job.job_portal_ai_service.payload.JobAlertSuggestResponse;
import com.manoj.job.job_portal_ai_service.payload.SearchEnhanceResponse;
import com.manoj.job.job_portal_ai_service.payload.SearchEnhancementRequest;

public interface SearchAiService {

    SearchEnhanceResponse enhanceSearch(SearchEnhancementRequest request) throws Exception;
    JobAlertSuggestResponse suggestJobAlertCriteria(JobAlertSuggestRequest request) throws Exception;
}
