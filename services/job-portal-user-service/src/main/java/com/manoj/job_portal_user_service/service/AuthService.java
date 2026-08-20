package com.manoj.job_portal_user_service.service;


import com.manoj.job_portal_user_service.payload.AuthResponse;
import com.manoj.job_portal_user_service.payload.LoginRequest;
import com.manoj.job_portal_user_service.payload.SignUpRequest;

public interface AuthService {
    AuthResponse signUp(SignUpRequest req) throws Exception;
    AuthResponse login(LoginRequest req);
}
