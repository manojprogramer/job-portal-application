package com.manoj.job.job_portal_ai_service.service.impl;

import com.manoj.job.job_portal_ai_service.client.GeminiClient;
import com.manoj.job.job_portal_ai_service.payload.*;
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
                        request.getCandidateSummary() != null ? request.getCandidateSummary() : "Not Provided",
                        request.getCandidateSkills() != null ? request.getCandidateSkills() : "Not Provided",
                        request.getCandidateExperience() != null ? request.getCandidateExperience() : "Not Provided"
                );

        return AiTextResponse.builder()
                .content(geminiClient.generateText(systemPrompt,prompt))
                .build();
    }

    @Override
    public ScreeningScoreResponse scoreCandidate(ScreeningScoreRequest request) throws Exception {
        String requiredSkills = request.getRequiredSkills() != null
                ? String.join(", ", request.getRequiredSkills()) : "Not Provided";
        String candidateSkills = request.getRequiredSkills() != null
                ? String.join(", ", request.getRequiredSkills()) : "Not Provided";
        String candidateExperience = request.getCandidateExperience() != null
                ? String.join(", ",request.getCandidateSkills()) : "Not Provided";

        String prompt = """
                Score this job application based on how well the candidate matched the requirements.
                
                Job Requirements:
                - Title : %s,
                - Experience Level Required: %s
                - Required Skills: %s
                - Key Responsibilities: %s
                
                Candidate Profile:
                - Professional Summary: %s
                - Skills: %s
                - Experience History: %S
                
                {
                    "Score": 85,
                    "skillsMatchScore": 90,
                    "experienceMatchScore": 75,
                    "matchedSkills": ["skill1", "skill2"],
                    "missingSkills": ["skill3"],
                    "strengths": ["strength1", "strength2"],
                    "concerns": ["concern1"],
                    "summary": "2-3 sentence honest assessment of th this candidate's fit"
                }
                Score scale: 0-100 where 100 is a perfect match.
                skillMatchScore: how well candidate skills match required skills (0-100).
                experienceMatchScore: how well candidate experience matches required level (0-100).
                educationMatchScore: how well candidate education/background fits the role (0-100).
                score: overall weighted match considering all factors. Be objective and fair.
                
                """.formatted(
                        request.getJobTitle() != null ? request.getJobTitle() : "Not provided",
                request.getExperienceLevel() != null ? request.getExperienceLevel(): "Not provided",
                request.getRequiredSkills(),
                request.getResponsibilities() != null ? request.getResponsibilities(): "Not Provided",
                request.getCandidateSummary() != null ? request.getCandidateSummary(): "Not Provided",
                candidateSkills,
                candidateExperience
        );
        return geminiClient.generateJson(systemPrompt,prompt, ScreeningScoreResponse.class);
    }

    @Override
    public SkillGapResponse analyzeSkillGap(SkillGapRequest request) throws Exception {
        String candidateSkills = request.getCandidateSkills() != null
                ? String.join(", ",request.getCandidateSkills())
                : "Not Provided";
        String requiredSkills = request.getRequiredSkills() != null
                ? String.join(", ",request.getRequiredSkills())
                : "Not Provided";

        String prompt = """
                Analyze the skills gap between a candidate and a job requirement;
                
                Job Title: %s
                Candidate's Current Skills: %s
                Skills Required for the Job: %s
                
                {
                    "matchedSkills": ["skills candidate has that are required"],
                    "missingSkills": ["required skills candidate completely lacks"],
                    "partialMatch": ["skill candidate has partially or related version of"],
                    "prioritySkillsToLearn: ["top 3 skills to learn first, in order of importance"],
                    "learningRecommendations":[
                        { "skill" : "skill name", "why": "why this skill is important for the role",
                          "howToLearn": "specific learning resource or approach"
                        }
                    ],
                    "overallReadiness": "Ready for Partially or Needs Development",
                    "summary": "2-sentence honest assessment"
                }
                """.formatted(
                        request.getJobTitle(),
                candidateSkills,
                requiredSkills
        );
        return geminiClient.generateJson(systemPrompt,prompt, SkillGapResponse.class);
    }
}
