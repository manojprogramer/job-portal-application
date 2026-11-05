package com.manoj.job.job_portal_application_service.controller;


import com.manoj.job.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public ApiResponse homeController(){
        return new ApiResponse("Service Managing job applications tracking application status and maintaining application history",true);
    }
}
