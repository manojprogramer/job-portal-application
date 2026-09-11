package com.manoj.job.model;

import com.manoj.job.domain.SocialPlatform;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialLink {
    private String url;
    private SocialPlatform socialPlatform;
}
