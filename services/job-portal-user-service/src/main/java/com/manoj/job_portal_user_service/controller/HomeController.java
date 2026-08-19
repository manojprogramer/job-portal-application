package com.manoj.job_portal_user_service.controller;

import com.manoj.job.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String homeController(){
        return "Welcome to the job portal"+ UserRole.ROLE_JOB_SEEKER;
    }

}
