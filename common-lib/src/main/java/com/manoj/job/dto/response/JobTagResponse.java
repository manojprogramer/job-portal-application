package com.manoj.job.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobTagResponse {
    private Long id;
    private String name;
    private String slug;
}
