package com.manoj.job_portal_user_service.repo;

import com.manoj.job_portal_user_service.repo.UserRepo;
import com.manoj.job_portal_user_service.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(@NotBlank(message = "Email is mandatory") @Email(message = "Email Should be valid") String email);

    boolean existsByEmail(@NotBlank(message = "Email is mandatory") @Email(message = "Email Should be valid") String email);
}
