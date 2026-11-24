package com.manoj.job.job_portal_ai_service.service.impl;

import com.manoj.job.job_portal_ai_service.client.GeminiClient;
import com.manoj.job.job_portal_ai_service.payload.JobAlertSuggestRequest;
import com.manoj.job.job_portal_ai_service.payload.JobAlertSuggestResponse;
import com.manoj.job.job_portal_ai_service.payload.SearchEnhanceResponse;
import com.manoj.job.job_portal_ai_service.payload.SearchEnhancementRequest;
import com.manoj.job.job_portal_ai_service.service.SearchAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchAiServiceImpl implements SearchAiService {

    @Autowired
    private GeminiClient geminiClient;

    private final String systemPrompt = """
            You are a job search expert and career advisor with deep knowledge of the Indian job Market.
            You extract structured search criteria from natural language and provide data-driven carreer recommendations.
            Always use the exact enum values specified in the prompt - never invent new values.
            When asked for JSON, respond ONLY valid JSON - no explanation, no markdown fences.
            """;

    @Override
    public SearchEnhanceResponse enhanceSearch(SearchEnhancementRequest request) throws Exception {
        String prompt = """
                Extract structured job search criteria from this natural language query.
                
                User Query: "%s"
                
                Analyze the query and extract ALL implied and explicit search criteria.
                
                Valid jobTypes: FULL_TIME, PART_TIME, CONTRACT, INTERNSHIP, FREELANCE
                Valid workModes: REMOTE, HYBRID, ON_SITE
                Valid experienceLevels: ENTRY, MID, SENIOR, LEAD, EXECUTIVE
                
                {
                    "keywords": ["keyword1", "keyword2"],
                    "locations": ["city1", "city2"],
                    "jobTypes":["FULL_TIME"],
                    "workModes": ["REMOTE"],
                    "experienceLevels": ["ENTRY"],
                    "minSalary": null,
                    "skills": ["skill1", "skill2"]
                }
                
                Rules:
                
                - Only include fields that are mentioned or clearly implied
                - Use null for minSalary if not mentioned.
                - Use emtpy arrays [] for fields not mentioned.
                - "freshers" or "entry level" -> ENTRY experience level
                - "senior" or "5+ years" -> SENIOR experience level
                - "wfh" or "work from home" -> REMOTE work mode 
                """.formatted(request.getQuery());
        return geminiClient.generateJson(systemPrompt,prompt, SearchEnhanceResponse.class);
    }

    @Override
    public JobAlertSuggestResponse suggestJobAlertCriteria(JobAlertSuggestRequest request) throws Exception {

        String skills = request.getSkills() != null
                ? String.join(", ",request.getSkills())
                : "Not Provided";

        String jobTitles = request.getPreviousJobTitles() != null
                ? String.join(", ",request.getPreviousJobTitles())
                : "Not Provided";
        String education = request.getEducations() != null
                ? String.join(", ",request.getEducations())
                : "Not Provided";

        String prompt = """
                Based on this candidate's profile, suggest optimal job alert criteria to find the best matching jobs.
                
                Candidate Profile:
                - Skills: %s
                - Experience Level: %s
                - Previous Job Titles: %s
                - Education: %s
                
                Valid jobTypes: FULL_TIME, PART_TIME, CONTRACT, INTERNSHIP, FREELANCE
                Valid workModes: REMOTE, HYBRID, ON_SITE
                Valid experienceLevels: ENTRY, MID, SENIOR, LEAD, EXECUTIVE
                
                {
                    "suggestedKeywords": ["keyword1", "keyword2"],
                    "suggestedLocations": ["city1", "city2"],
                    "suggestedJobTypes": ["FULL_TIME"],
                    "suggestedWorkModes": ["REMOTE", "HYBRID"],
                    "suggestedExperienceLevels": ["MID"],
                    "suggestedIndustries": ["Technology", "Finance"];
                    "reasoning": "Brief explanation of why these criteria were chosen"
                }
                """.formatted(
                        skills,
                request.getExperienceLevel(),
                jobTitles,
                education

        );
        return geminiClient.generateJson(systemPrompt,prompt, JobAlertSuggestResponse.class);
    }
}
