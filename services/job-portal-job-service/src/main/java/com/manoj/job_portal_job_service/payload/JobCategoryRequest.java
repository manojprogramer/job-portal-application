package com.manoj.job_portal_job_service.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobCategoryRequest {
    @NotBlank(message = "category name is required")
    private String name;
    @Size(max = 500, message = "Message must not be exceeded by 500 characters")
    private String description;
    private String iconUrl;
    private Long parentId;

}
