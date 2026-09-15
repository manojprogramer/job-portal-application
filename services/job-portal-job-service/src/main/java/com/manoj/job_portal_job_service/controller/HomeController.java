package com.manoj.job_portal_job_service.controller;

import com.manoj.job.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public ApiResponse homeController(){
        return new ApiResponse("Entering into the job controller",true);
    }
}
