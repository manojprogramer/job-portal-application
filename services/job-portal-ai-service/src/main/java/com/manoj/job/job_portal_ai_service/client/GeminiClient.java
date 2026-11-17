package com.manoj.job.job_portal_ai_service.client;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.manoj.job.job_portal_ai_service.config.GeminiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeminiClient {

    @Autowired
    private Client genAiClient;

    @Autowired
    private GeminiProperties geminiProperties;

    public String generateText(String prompt, double temperature, int maxTokens) throws Exception {
        try{
            GenerateContentConfig config = GenerateContentConfig.builder()
                    .temperature((float) temperature)
                    .maxOutputTokens(maxTokens)
                    .build();
            GenerateContentResponse response = genAiClient.models.generateContent(geminiProperties.getModel(),prompt,config);
            return response.text();
        }
        catch (Exception e){
            throw new Exception("failed to get response from gemini"+e.getMessage());

        }
    }
    public String generateText(String prompt) throws Exception {
        return generateText(prompt,geminiProperties.getTemperature(),geminiProperties.getMaxOutputTokens());
    }
    public String callText(String systemInstruction,String prompt, float temperature, int maxTokens) throws Exception {
        try{
            GenerateContentConfig config = buildConfig(systemInstruction,temperature,maxTokens,false);
            GenerateContentResponse response = genAiClient.models.generateContent(geminiProperties.getModel(),prompt,config);
            return response.text();
        }
        catch (Exception e){
            throw  new Exception("Failed to get response from gemini"+e.getMessage());
        }
    }

    private GenerateContentConfig buildConfig(String systemInstruction, float temperature, int maxTokens, boolean b) {
        return null;
    }
}
