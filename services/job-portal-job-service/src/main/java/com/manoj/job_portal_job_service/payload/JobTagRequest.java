package com.manoj.job_portal_job_service.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobTagRequest {

    @NotBlank(message = "Tag name is required")
    private String name;
}
