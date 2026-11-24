package com.manoj.job.job_portal_ai_service.service;

import com.manoj.job.job_portal_ai_service.payload.*;



public interface ResumeAiService {
     AiTextResponse generateProfessionalSummary(ResumeSummaryRequest request) throws Exception;
     WorkExperienceBulletsResponse generateWorkExperienceBullets(WorkExperienceBulletRequest request) throws Exception;
     CareerFeedBackResponse getCareerFeedBack(CareerFeedBackRequest request) throws Exception;
     ResumeImprovementResponse getResumeImprovementTips(ResumeImprovementRequest request) throws Exception;
}
