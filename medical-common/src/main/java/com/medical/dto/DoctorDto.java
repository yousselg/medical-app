package com.medical.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Getter
@Setter
public class DoctorDto implements Serializable {
    private Long id;
    private String name;
    @Email
    private String email;
    private String imageUrl;
}
