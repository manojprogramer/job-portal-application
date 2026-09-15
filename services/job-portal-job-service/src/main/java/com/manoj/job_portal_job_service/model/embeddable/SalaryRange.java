package com.manoj.job_portal_job_service.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryRange {
    private BigDecimal minRange;
    private BigDecimal maxRange;

}
