package com.manoj.job.job_portal_ai_service.payload;

import lombok.Data;

import java.util.List;

@Data
public class CareerFeedBackResponse {
    private int profileStrength;

    private List<String> shortListingIssues;

    private List<Improvement> improvements;

    private List<JobTarget> targetJobs;

    private String overallSummary;

    @Data
    public static class Improvement{
        private String area;
        private String issue;
        private String action;
        private String priority;
    }
    @Data
    public static class JobTarget{
        private String jobTitle;
        private String reason;
        private String skillMatch;
    }



}
