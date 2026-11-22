package com.manoj.job.job_portal_ai_service.payload;

import lombok.Data;

import java.util.List;


@Data
public class SalaryRangeRequest {

    private String title;

    private List<String> skills;
    private String experienceLevel;
    private String jobType;
    private String Location;
}
