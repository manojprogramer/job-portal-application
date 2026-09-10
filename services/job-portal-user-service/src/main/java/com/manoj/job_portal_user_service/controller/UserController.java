package com.manoj.job_portal_user_service.controller;


import com.manoj.job.dto.response.UserResponse;
import com.manoj.job_portal_user_service.mapper.UserMapper;
import com.manoj.job_portal_user_service.model.User;
import com.manoj.job_portal_user_service.payload.UpdateUserRequest;
import com.manoj.job_portal_user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile(@RequestHeader("X-User-Email") String email) throws Exception {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserResponse> updateProfile(@RequestHeader("X-User-Email") String email, @RequestBody @Valid UpdateUserRequest req) throws Exception {
        return  ResponseEntity.ok(userService.updateProfile(email,req));
    }
    @GetMapping("profile/{userId}")
    public ResponseEntity<UserResponse> getByUserId(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(UserMapper.toDTO(userService.getUserById(userId)));
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers(){
        return ResponseEntity.ok(UserMapper.toDTOList(userService.getAllUsers()));
    }
    @PatchMapping("users/{userId}/suspend")
    public ResponseEntity<UserResponse> suspendUser(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(userService.suspendUser(userId));
    }
    @PatchMapping("users/{userId}/activate")
    public ResponseEntity<UserResponse> activateUser(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(userService.activateUser(userId));
    }
    @PatchMapping("users/{userId}/delete")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }


}
