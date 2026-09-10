package com.manoj.job_portal_user_service.service.impl;

import com.manoj.job.domain.UserRole;
import com.manoj.job.domain.UserStatus;
import com.manoj.job.dto.response.UserResponse;
import com.manoj.job_portal_user_service.mapper.UserMapper;
import com.manoj.job_portal_user_service.model.User;
import com.manoj.job_portal_user_service.payload.AuthResponse;
import com.manoj.job_portal_user_service.payload.LoginRequest;
import com.manoj.job_portal_user_service.payload.SignUpRequest;
import com.manoj.job_portal_user_service.repo.UserRepo;
import com.manoj.job_portal_user_service.security.CustomUserDetailsService;
import com.manoj.job_portal_user_service.security.JwtProvider;
import com.manoj.job_portal_user_service.service.AuthService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

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
                .password(passwordEncoder.encode(req.getPassword()))
                .role(req.getRole())
                .status(UserStatus.ACTIVE)
                .LastLogin(LocalDateTime.now())
                .build();
        userRepo.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication,user.getId());

        AuthResponse response = new AuthResponse();
        response.setTitle("Welcome");
        response.setMessage("Registered Successfully");
        response.setJwt(jwt);
        response.setUser(UserMapper.toDTO(user));
        return response;
    }

    @Override
    public AuthResponse login(LoginRequest req) throws Exception {
        Authentication authentication = authenticate(req.getEmail(),req.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepo.findByEmail(req.getEmail());

        String jwt = jwtProvider.generateToken(authentication,user.getId());
        user.setLastLogin(LocalDateTime.now());
        userRepo.save(user);

        AuthResponse response = new AuthResponse();
        response.setTitle("Welcome Back");
        response.setMessage("Login Successfully");
        response.setJwt(jwt);
        response.setUser(UserMapper.toDTO(user));
        return response;

    }

    private Authentication authenticate(String email,String password) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if(userDetails == null)
            throw new Exception("Email Not Found"+email);
        if(!passwordEncoder.matches(password,userDetails.getPassword()))
            throw new Exception("Invalid Password");
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
