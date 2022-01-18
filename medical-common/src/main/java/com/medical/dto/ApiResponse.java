package com.medical.dto;

import lombok.Value;

@Value
public class ApiResponse<T> {
    private Boolean success;
    private T payload;
}