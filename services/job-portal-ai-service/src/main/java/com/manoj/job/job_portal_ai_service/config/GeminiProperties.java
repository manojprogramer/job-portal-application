package com.manoj.job.job_portal_ai_service.config;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gemini.api")
@Getter
@Setter
public class GeminiProperties {
    private String key;
    private String model;
    private int maxOutputTokens = 2048;
    private double temperature =0.7;
}
