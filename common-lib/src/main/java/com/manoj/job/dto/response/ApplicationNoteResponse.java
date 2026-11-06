package com.manoj.job.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationNoteResponse {

    private Long id;

    private Long addedByUserId;

    private String content;

    private LocalDateTime createdAt;
}
