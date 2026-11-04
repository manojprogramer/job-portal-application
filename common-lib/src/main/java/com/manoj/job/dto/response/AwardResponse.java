package com.manoj.job.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AwardResponse {

    private Long id;

    private String awardTitle;

    private String issuingOrganization;

    private LocalDate awardDate;

    private String description;

    private String awardUrl;

}