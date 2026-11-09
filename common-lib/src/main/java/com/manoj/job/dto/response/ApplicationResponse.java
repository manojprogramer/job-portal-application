package com.manoj.job.dto.response;

import com.manoj.job.domain.AiShortListStatus;
import com.manoj.job.domain.ApplicationStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponse {

    private Long id;

    private UserResponse userResponse;

    private Long employerId;

    private JobResponse jobResponse;

    private CompanyResponse companyResponse;

    private ApplicationStatus status;

    private Long resumeId;

    private String coverLetter;

    private BigDecimal expectedSalary;

    private LocalDate availableFrom;

    private Boolean isStarred = false;

    private LocalDateTime withdrawnAt;

    private String withdrawnReason;

    private LocalDateTime appliedAt;

    private LocalDateTime updatedAt;


    private List<ApplicationNoteResponse> notes;

//    private ApplicationScreeningResponse screeningResponse;
}
