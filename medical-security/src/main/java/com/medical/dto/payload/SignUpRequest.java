package com.medical.dto.payload;

import com.medical.model.actors.Role;
import com.medical.validator.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

@Data
@PasswordMatches
public class SignUpRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    @Size(min = 6)
    private String matchingPassword;

    private Role role;

}
