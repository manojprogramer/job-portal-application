package com.manoj.payload;

import com.manoj.job.domain.ExperienceLevel;
import com.manoj.job.domain.JobStatus;
import com.manoj.job.domain.JobType;
import com.manoj.job.domain.WorkMode;

import java.math.BigDecimal;
import java.util.List;

public class JobSearchRequest {
    private String keyword;

    private Long categoryId;

    private List<Long> skillIds;

    private List<Long> tagIds;

    private Long companyId;
    private String location;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;

    private JobType jobType;
    private WorkMode workMode;
    private ExperienceLevel experienceLevel;
    private JobStatus jobStatus;

    private Integer minOpenings;
    private Integer maxOpenings;

}
