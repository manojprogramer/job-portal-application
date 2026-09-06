package com.manoj.job_portal_user_service.payload;


import lombok.Data;

@Data
public class UpdateUserRequest {
    private String fullName;

    private String phoneNumber;

    private String profileImage;
}
