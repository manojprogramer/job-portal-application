package com.manoj.job.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponse {
    private Long id;
    private String title;
    private String description;
    private List<String> technologies;
    private String projectUrl;
    private String sourceCodeUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isOngoing;
    private Integer displayOrder;
}
