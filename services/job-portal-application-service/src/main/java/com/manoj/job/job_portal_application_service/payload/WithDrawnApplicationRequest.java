package com.manoj.job.job_portal_application_service.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithDrawnApplicationRequest {
    private String reason;
}
