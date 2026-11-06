package com.manoj.job.dto.response;


import com.manoj.job.domain.UserRole;
import com.manoj.job.domain.UserStatus;
import lombok.*;


import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String profileImage;
    private UserRole role;
    private UserStatus status;
    private boolean verified;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
}
