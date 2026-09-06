package com.manoj.job_portal_user_service.service;

import com.manoj.job.dto.response.UserResponse;
import com.manoj.job_portal_user_service.model.User;
import com.manoj.job_portal_user_service.payload.UpdateUserRequest;

import java.util.List;

public interface UserService {
    User getUserByEmail(String email) throws Exception;

    User getUserById(Long id) throws Exception;

    List<User> getAllUsers();

    UserResponse updateProfile(String email, UpdateUserRequest req) throws Exception;

    UserResponse suspendUser(Long id) throws Exception;

    UserResponse activateUser(Long id) throws Exception;

    UserResponse deleteUser(Long id) throws Exception;

}
