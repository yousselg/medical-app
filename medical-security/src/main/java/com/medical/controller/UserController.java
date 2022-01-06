package com.medical.controller;

import com.medical.dto.UserDto;
import com.medical.exception.ResourceNotFoundException;
import com.medical.mapper.UserMapper;
import com.medical.repository.UserRepository;
import com.medical.security.CurrentUser;
import com.medical.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserDto getCurrentUser(@ApiIgnore @CurrentUser final UserPrincipal userPrincipal) {
        return this.userRepository.findById(userPrincipal.getId()).map(this.userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId().toString()));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getContent() {
        return ResponseEntity.ok("Public content goes here");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUserContent() {
        return ResponseEntity.ok("User content goes here");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdminContent() {
        return ResponseEntity.ok("Admin content goes here");
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<?> getModeratorContent() {
        return ResponseEntity.ok("Moderator content goes here");
    }

}
