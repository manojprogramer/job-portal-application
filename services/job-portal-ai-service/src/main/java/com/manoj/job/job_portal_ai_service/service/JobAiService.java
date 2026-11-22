package com.manoj.job.job_portal_ai_service.service;

import com.manoj.job.job_portal_ai_service.client.GeminiClient;
import com.manoj.job.job_portal_ai_service.payload.AiTextResponse;
import com.manoj.job.job_portal_ai_service.payload.JobDescriptionRequest;
import com.manoj.job.job_portal_ai_service.payload.SalaryRangeRequest;
import com.manoj.job.job_portal_ai_service.payload.SalaryRangeResponse;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobAiService {
    @Autowired
    private GeminiClient geminiClient;

    String systemPrompt = """
            You are a senior HR professional and technical recruiter with deep knowledge of hte Indian Job Market (2025-2026).
            You specialize in writing job descriptions, compensation benchmarking, and talent acquisition.
            Always write in a professional, engaging, inclusive and bias-free tone.
            When asked for JSON, respond ONLY WITH VALID JSON - no explanation, no markdown fences.
            """;

    public AiTextResponse generateJobDescription(JobDescriptionRequest request) throws Exception {

        String skills = request.getSkills() != null ? String.join(", ", request.getSkills()) : "Not Specified";
        String prompt = """
                Write a comprehensive, engaging, and inclusive job description.
                
                Job Details:
                - Title : %s
                - Required Skills: %s
                - Experience Level: %s
                - Job Type: %s
                - Work Mode: %s
                - Category: %s
                - Additional Context: %s
                
                Format the response in clean markdown with EXACTLY these sections:
                ## About the Role
                [2-3 compelling sentences describing the role and its impact]
                
                ## Requirements
                - [5-6 must-have qualifications and skills]
                
                ## Nice to Have
                - [3-4 bonus qualifications]
                
                ## What we offer
                - [4-5 benefits and perks]
                
                Do Not include placeholder company names.
                """.formatted(request.getTitle(),
                request.getSkills() != null ? request.getSkills() : "Not Specified",
                request.getExperienceLevel() != null ? request.getExperienceLevel() : "Not Specified",
                request.getJobType() != null ? request.getJobType() : "Not Specified",
                request.getWorkMode() != null ? request.getWorkMode() : "Not Specified",
                request.getCategory() != null ? request.getCategory() : "Not Specified",
                request.getAdditionalContext() != null ? request.getAdditionalContext() : "Not Specified");

        return AiTextResponse.builder()
                .content(geminiClient.generateText(systemPrompt,prompt))
                .build();
    }

    public AiTextResponse generateJobRequirements(String title, String category) throws Exception {
        String prompt = """
                Generate professional job requirements and responsibilities for this role.
                
                Job Title : %s
                Category : %s
                
                Format in markdown:
                ## Responsibilities
                - [ 5 specific bullet points]
                
                ## Requirements
                -[5 specific bullet points]
                
                Keep it concise and ATS-friendly.
                """.formatted(
                        title,
                category
        );

        return AiTextResponse.builder()
                .content(geminiClient.generateText(systemPrompt,prompt))
                .build();
    }
    public SalaryRangeResponse suggestSalaryRange(SalaryRangeRequest request) throws Exception {

        String skills = request.getSkills() != null ? String.join(", ",request.getSkills()) : "Not Specified";
        String prompt = """
                Providing a realistic and competitive salary range for this role.
                
                Role Details:
                - Job Title: %s
                - Required Skills: %s
                - Experience Level : %s
                - Job Type: %s
                - Location: %s
                
                {
                  "minSalary": 600000,
                  "maxSalary": 120000,
                  "currency": "INR",
                  "period": "YEARLY",
                  "marketInsight": "Brief 1-2 sentence insight about this role's competition trend in the current Indian Market".
                }
                
                minSalary and maxSalary must be numbers (not strings). Use realistic current Indian Market rates.
                """.formatted(
                        request.getTitle(),
                skills,
                request.getExperienceLevel() != null ? request.getJobType() : "MID",
                request.getJobType() != null ? request.getJobType() : "FULL_TIME",
                request.getLocation() != null ? request.getLocation() : "INDIA"
        );
        return geminiClient.generateJson(systemPrompt,prompt, SalaryRangeResponse.class);

    }
    public AiTextResponse generateJobResponsibilities(String title, String category) throws Exception {
        String prompt = """
                Generate 6 specific, action oriented job responsibilities for this role.
                
                Job Title: %s
                Category: %s
                
                Return ONLY  a plain bullet list (no headings, no markdown headers):
                - [responsibility 1]
                - [responsibility 2]
                ...
                
                Keep each bullet concise (under 15 words), start with a strong action verb.
                """;
        return AiTextResponse.builder()
                .content(geminiClient.generateText(systemPrompt,prompt))
                .build();
    }
    public AiTextResponse generateJobBenefits(String title, String category, String jobType) throws Exception {
        String prompt = """
                Generate6 competitive, attractive job benefits for this role.
                
                Job Title: %s
                Category: %s
                JobType: %s
                
                Return ONLY a plain bullet list (no headings, no markdown headers):
                - [benefit 1]
                - [benefit 2]
                ...
                
                Include a mix of: compensation perks, health/wellness, growth, flexibility and culture benefits.
                Keep each bullet concise and specific.
                """.formatted(
                        title,
                category != null ? category : "General",
                jobType != null ? jobType : "FULL TIME"
        );
        return AiTextResponse.builder()
                .content(geminiClient.generateText(systemPrompt,prompt))
                .build();

    }
    public AiTextResponse generateSkillsForJobs(String jobTitle, String description) throws Exception {
        String prompt = """
                Recommend the most relevant skills for this job posing.
                
                Job Title: %s
                Description: %s
                
                List 8-10 specific, relevant skills that candidate should have.
                Return a comma-separated list of skill names only.
                Example, Java, Spring Boot, PostgreSQL, Docker, Rest APIs, Microservices.
                """.formatted(
                        jobTitle,
                description
        );
        return AiTextResponse.builder()
                .content(geminiClient.generateText(systemPrompt,prompt))
                .build();

    }
}
