package com.manoj.job.controller;

import com.manoj.job.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "Welcome to My Company"+ UserRole.ROLE_JOB_SEEKER;
    }
}
