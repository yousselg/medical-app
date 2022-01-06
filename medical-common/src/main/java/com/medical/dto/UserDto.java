package com.medical.dto;

import com.medical.model.actors.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class UserDto implements Serializable {
    private Long id;
    private String displayName;
    @Email
    private String email;
    private String imageUrl;
    private Boolean emailVerified = false;
    private Set<Role> roles;
}
