package com.manoj.job_portal_user_service.controller;

import com.manoj.job_portal_user_service.payload.AuthResponse;
import com.manoj.job_portal_user_service.payload.LoginRequest;
import com.manoj.job_portal_user_service.payload.SignUpRequest;
import com.manoj.job_portal_user_service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private  AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody @Valid SignUpRequest req)throws Exception{
        return ResponseEntity.ok(authService.signUp(req));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) throws Exception {
        return ResponseEntity.ok(authService.login(request));
    }

}
