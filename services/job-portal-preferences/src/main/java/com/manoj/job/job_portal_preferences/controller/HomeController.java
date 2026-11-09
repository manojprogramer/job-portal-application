package com.manoj.job.job_portal_preferences.controller;

import com.manoj.job.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public ApiResponse homeController(){
        return new ApiResponse("Job portal Preference",true);
    }
}
