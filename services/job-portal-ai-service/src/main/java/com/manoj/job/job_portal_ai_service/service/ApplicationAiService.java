package com.manoj.job.job_portal_ai_service.service;

import com.manoj.job.job_portal_ai_service.payload.AiTextResponse;
import com.manoj.job.job_portal_ai_service.payload.CoverLetterRequest;

public interface ApplicationAiService {
     AiTextResponse generateCoverLetter(CoverLetterRequest request) throws Exception;
}
