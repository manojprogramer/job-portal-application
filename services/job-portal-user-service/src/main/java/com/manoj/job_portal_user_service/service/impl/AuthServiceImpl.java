package com.manoj.job_portal_user_service.service.impl;

import com.manoj.job.domain.UserRole;
import com.manoj.job_portal_user_service.model.User;
import com.manoj.job_portal_user_service.payload.AuthResponse;
import com.manoj.job_portal_user_service.payload.LoginRequest;
import com.manoj.job_portal_user_service.payload.SignUpRequest;
import com.manoj.job_portal_user_service.repo.UserRepo;
import com.manoj.job_portal_user_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public AuthResponse signUp(SignUpRequest req)  {
        if(userRepo.existsByEmail(req.getEmail()))
        {
            try {
                throw new Exception("Email Already Registered"+req.getEmail());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(req.getRole() == UserRole.ROLE_ADMIN){
            try {
                throw new Exception("Cannot self-register as role admin");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .phone(req.getPhone())
                .password(req.getPassword())
                .role(req.getRole())
                .LastLogin(LocalDateTime.now())
                .build();
        userRepo.save(user);
        AuthResponse response = new AuthResponse();
        response.setTitle("Welcome");
        response.setMessage("Registered Successfully");
        response.setJwt("jwt");
//        response.setUser();
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest req) {
        return null;
    }
}
