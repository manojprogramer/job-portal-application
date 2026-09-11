package com.manoj.job.dto.response;

import com.manoj.job.domain.SocialPlatform;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialLinkResponse {
    private String url;
    private SocialPlatform socialPlatform;
}
