package com.medical.controller;

import com.medical.exception.ResourceNotFoundException;
import com.medical.model.actors.AbstractUser;
import com.medical.repository.UserRepository;
import com.medical.security.CurrentUser;
import com.medical.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public AbstractUser getCurrentUser(@CurrentUser final UserPrincipal userPrincipal) {
        return this.userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
