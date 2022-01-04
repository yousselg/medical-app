package com.medical.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private final String tokenType = "Bearer";
    private String accessToken;
}
