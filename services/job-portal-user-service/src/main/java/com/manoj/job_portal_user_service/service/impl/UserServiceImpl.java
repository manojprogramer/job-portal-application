package com.manoj.job_portal_user_service.service.impl;

import com.manoj.job.domain.UserStatus;
import com.manoj.job.dto.response.UserResponse;
import com.manoj.job_portal_user_service.mapper.UserMapper;
import com.manoj.job_portal_user_service.model.User;
import com.manoj.job_portal_user_service.payload.UpdateUserRequest;
import com.manoj.job_portal_user_service.repo.UserRepo;
import com.manoj.job_portal_user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public User getUserByEmail(String email) throws Exception {
        User user = userRepo.findByEmail(email);
        if(user == null) throw new Exception("User Not Found");
        return user;
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return userRepo.findById(id).orElseThrow(() -> new Exception("User Not Found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserResponse updateProfile(String email, UpdateUserRequest req) throws Exception {
        User user = getUserByEmail(email);
        if(req.getFullName() != null){
            user.setFullName(req.getFullName());
        }
        if(req.getProfileImage() != null) {
            user.setProfileImage(req.getProfileImage());
        }
        if(req.getPhoneNumber() != null){
            user.setPhone(req.getPhoneNumber());
        }
        return UserMapper.toDTO(user);
    }

    @Override
    public UserResponse suspendUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.SUSPENDED);
        user.setSuspendedAt(LocalDateTime.now());
        userRepo.save(user);
        return UserMapper.toDTO(user);
    }

    @Override
    public UserResponse activateUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.ACTIVE);
        user.setSuspendedAt(null);
        userRepo.save(user);
        return UserMapper.toDTO(user);
    }

    @Override
    public UserResponse deleteUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.DELETED);
        user.setDeletedAt(LocalDateTime.now());
        userRepo.save(user);
        return UserMapper.toDTO(user);
    }
}
