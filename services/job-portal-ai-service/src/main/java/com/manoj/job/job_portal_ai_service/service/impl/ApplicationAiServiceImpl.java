package com.manoj.job.job_portal_ai_service.service.impl;

import com.manoj.job.job_portal_ai_service.client.GeminiClient;
import com.manoj.job.job_portal_ai_service.payload.AiTextResponse;
import com.manoj.job.job_portal_ai_service.payload.CoverLetterRequest;
import com.manoj.job.job_portal_ai_service.service.ApplicationAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationAiServiceImpl implements ApplicationAiService {

    @Autowired
    private GeminiClient geminiClient;

    private static final String systemPrompt = """
            You are a senior technical recruiter and career coach with 15+ years of experience in the Indian tech Industry.
            You specialize in candidate evaluation, cover letter writing, skills gap analysis, and career development.
            Always provide objective, fair, adn actionable assessments based only on the information provided.
            When asked for JSON respond ONLY with JSON - no explanation, no markdown fences.
            
            """;

    @Override
    public AiTextResponse generateCoverLetter(CoverLetterRequest request) throws Exception {
        String skills = request.getCandidateSkills() != null
                ? String.join(", ",request.getCandidateSkills())
                : "Not Provided";
        String experience = request.getCandidateExperience() != null
                ? String.join(", ", request.getCandidateExperience())
                : "Not Provided";
        String prompt = """
                Write a compelling, personalized cover letter.
                
                Position: %s
                Job Description: %s
                Target Company: %s
                
                Candidate Profile:
                Name : %s
                Professional Summary: %s
                Key Skills : %s
                Relevant Experience : %s
                
                Write a 3-paragraph cover letter:
                Paragraph 1 (opening): Express specific enthusiasm for this exact role and company. Mention 1 specific thing about the role that excites you.
                Paragraph 2 (Body): Connect 2-3 of the candidate's strongest experience/skills directly to the job requirements. Be Specific with examples.
                Paragraph 3 (Closing): Confident call to action. Express eagerness to discuss futher.
                
                
                Rules:
                - Write as the candidate (first param)
                - Be specific - avoid generic statements
                - Maximum 300 words
                - Professional but warm tone
                - Do Not use placeholders like (Company Name) - use the actual company name or say "Your team"
                - Do Not include subject line or date.
                """
                .formatted(
                        request.getJobTitle(),
                        request.getDescription() != null ? request.getDescription() : "Not Provided",
                        request.getTargetCompanyName() != null ? request.getTargetCompanyName() : "Not Provided",
                        request.getCandidateName(),
                        request.getCandidateSummary() != null ? request.getCandidateSummary() : "Not Provided");

        return AiTextResponse.builder()
                .content(geminiClient.generateText(systemPrompt,prompt))
                .build();
    }
}
