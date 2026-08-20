package com.manoj.job_portal_user_service.mapper;

import com.manoj.job.dto.response.UserResponse;
import com.manoj.job_portal_user_service.model.User;

public class UserMapper {
    public static UserResponse toDTO(User user){;
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setFullName(user.getFullName());
        dto.setProfileImage(user.getProfileImage());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setLastLogin(user.getLastLogin());

        return dto;


    }
}
