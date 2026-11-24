package com.manoj.job.job_portal_ai_service.service;

import com.manoj.job.job_portal_ai_service.payload.*;

public interface ApplicationAiService {
     AiTextResponse generateCoverLetter(CoverLetterRequest request) throws Exception;
     ScreeningScoreResponse scoreCandidate(ScreeningScoreRequest request) throws Exception;
     SkillGapResponse analyzeSkillGap(SkillGapRequest request) throws Exception;

}
