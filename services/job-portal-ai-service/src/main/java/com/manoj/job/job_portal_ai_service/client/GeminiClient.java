package com.manoj.job.job_portal_ai_service.client;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import com.manoj.job.job_portal_ai_service.config.GeminiProperties;
import jakarta.annotation.PostConstruct;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class GeminiClient {

    @Autowired
    private Client genAiClient;

    @Autowired
    private GeminiProperties geminiProperties;

    @Autowired
    private ObjectMapper objectMapper;

    public GeminiClient(){
        System.out.println("Gemini Bean is created");
    }

    @PostConstruct
    public void checkApiKey() {
        System.out.println(geminiProperties.getModel());
        String apiKey = geminiProperties.getKey();
        System.out.println("API KEY LOADED: " +
                (apiKey != null && !apiKey.isBlank()));

        System.out.println("API KEY LENGTH: " +
                (apiKey == null ? 0 : apiKey.length()));

    }

    public String generateText(String systemInstruction,String prompt, double temperature, int maxTokens) throws Exception {
        return callText(systemInstruction,
                prompt,
                (float) geminiProperties.getTemperature(),
                geminiProperties.getMaxOutputTokens());
    }
    public String generateText(String systemInstruction,String prompt) throws Exception {
        return generateText(
                systemInstruction,
                prompt,
                geminiProperties.getTemperature(),
                geminiProperties.getMaxOutputTokens());
    }
    public <T> T generateJson(String systemInstruction,String prompt, Class<T> responseType) throws Exception {
        return callJson(systemInstruction,prompt,responseType);
    }


    public String callText(String systemInstruction,
                           String prompt,
                           float temperature,
                           int maxTokens) throws Exception {
        try {
            GenerateContentConfig config =
                    buildConfig(systemInstruction, temperature, maxTokens, false);

            System.out.println("MODEL: " + geminiProperties.getModel());
            System.out.println("PROMPT LENGTH: " + prompt.length());

            GenerateContentResponse response =
                    genAiClient.models.generateContent(
                            geminiProperties.getModel(),
                            prompt,
                            config
                    );

            System.out.println("GEMINI RESPONSE: " + response.text());

            return response.text();

        } catch (Exception e) {
            System.err.println("========== GEMINI ERROR ==========");
            e.printStackTrace();
            throw new RuntimeException("Gemini API failed", e);
        }
    }

    //    public String callText(String systemInstruction,
//                           String prompt,
//                           float temperature,
//                           int maxTokens) throws Exception {
//        try{
//            GenerateContentConfig config = buildConfig(systemInstruction,temperature,maxTokens,false);
//
//            GenerateContentResponse response = genAiClient.models.generateContent(geminiProperties.getModel(),prompt,config);
//            return response.text();
//        }
//        catch (Exception e){
//            throw  new Exception("Failed to get response from gemini"+e.getMessage());
//        }
//    }
    public <T> T callJson(String systemInstruction,
                          String prompt,
                          Class<T> responseType) throws Exception {
        try{
            GenerateContentConfig config = buildConfig(systemInstruction,0.3f,
                    geminiProperties.getMaxOutputTokens(),true);
            GenerateContentResponse response = genAiClient.models.generateContent(geminiProperties.getModel(),prompt,config);
            return objectMapper.readValue(response.text(),responseType);
        }
        catch (Exception e){
            throw  new Exception("Failed to get response from gemini"+e.getMessage());
        }
    }
    private GenerateContentConfig buildConfig(
            String systemInstruction,
            float temperature,
            int maxTokens,
            boolean jsonMode) {

        GenerateContentConfig.Builder builder =
                GenerateContentConfig.builder()
                        .maxOutputTokens(maxTokens);

        if (systemInstruction != null && !systemInstruction.isBlank()) {
            builder.systemInstruction(
                    Content.fromParts(Part.fromText(systemInstruction))
            );
        }

        if (jsonMode) {
            builder.responseMimeType("application/json");
        }

        return builder.build();
    }

//    private GenerateContentConfig buildConfig(String systemInstruction, float temperature, int maxTokens, boolean josnMode) {
//        GenerateContentConfig.Builder builder  = GenerateContentConfig.builder()
//                .temperature((float) temperature).maxOutputTokens(maxTokens);
//
//        if(systemInstruction != null  && !systemInstruction.isBlank()){
//            builder.systemInstruction(Content.fromParts(Part.fromText(systemInstruction)));
//        }
//        if(josnMode){
//            builder.responseMimeType("application/json");
//
//        }
//        return builder.build();
//    }
}
