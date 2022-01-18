package com.medical.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String email;
    private String displayName;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private Set<String> roles;

}
